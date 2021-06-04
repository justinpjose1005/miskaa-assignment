package com.justinpjose.miskaaassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.justinpjose.miskaaassignment.database.RoomDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnCountryListener {
    List<CountryModel> countryList;
    RecyclerView recyclerview;
    LinearLayoutManager linearLayoutManager;
    CountryAdapter adapter;
    ProgressBar progressbar;
    RoomDB database;
    Button erase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryList = new ArrayList<>();
        progressbar = findViewById(R.id.progressbar);
        erase = findViewById(R.id.eraseButton);
        database = RoomDB.getInstance(this);

        if (internetAvailable()) {
            // when we have internet
            // retrieve data from internet
            progressbar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Fetching from the Internet.", Toast.LENGTH_LONG).show();
            String url = "https://restcountries.eu/rest/v2/region/asia";
            fetch_json(url);
        } else {
            // when we don't have internet
            int count = database.mainDao().getCount();
            if (count == 0) {
                // when local database is empty
                Toast.makeText(this, "Local database is empty", Toast.LENGTH_LONG).show();
            } else {
                // when local database is not empty
                // read from local database
                Toast.makeText(this, "We found " + count + " item(s) in the local database", Toast.LENGTH_LONG).show();
                countryList = database.mainDao().getAll();
                initialize_recycler_view();
            }
            progressbar.setVisibility(View.GONE);
        }

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = database.mainDao().getCount();
                if (count == 0) {
                    // when local database is empty
                    Toast.makeText(getApplicationContext(), "Local database is empty", Toast.LENGTH_LONG).show();
                } else {
                    // reset the database
                    database.mainDao().reset();
                    countryList.clear();
                    Toast.makeText(getApplicationContext(), "We have erased the local database.", Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public boolean internetAvailable() {
        try {
            // when we have internet
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            // when we don't have internet
            return false;
        }
    }

    private void fetch_json(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    countryList.clear();
                    // reset the database before inserting new data
                    database.mainDao().reset();
                    JSONArray countryArray = new JSONArray(response);
                    int countryArrayLength = countryArray.length();
                    for (int i = 0; i < countryArrayLength; i++) {
                        // splits wrt each country
                        String languages = "";
                        JSONObject countryObject = countryArray.getJSONObject(i);
                        JSONArray languagesArray = new JSONArray(countryObject.getString("languages"));

                        int languageArrayLength = languagesArray.length();
                        for (int j = 0; j < languageArrayLength; j++) {
                            // splits wrt each language
                            JSONObject languageObject = languagesArray.getJSONObject(j);
                            String lang = languageObject.getString("name");
                            if (j != languageArrayLength - 1) {
                                lang = lang.concat(", ");
                            }
                            languages = languages.concat(lang);
                        }

                        CountryModel country = new CountryModel(countryObject.getString("name"), countryObject.getString("capital"), countryObject.getString("flag"), countryObject.getString("region"), countryObject.getString("subregion"), countryObject.getString("population"), countryObject.getString("borders"), languages);
                        countryList.add(country);
                        // writing into database
                        database.mainDao().insert(country);
                    }
                    initialize_recycler_view();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void initialize_recycler_view() {
        recyclerview = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new CountryAdapter(this, countryList, this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(adapter);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onCountryClick(int position) {
        // click that shows the country details
        CountryModel country = countryList.get(position);
        CountryDialog dialog = new CountryDialog();
        dialog.showDialog(this, country);
    }
}