package com.justinpjose.miskaaassignment;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

public class CountryDialog {
    TextView name, capital, region, subregion, population, borders, languages;
    ImageView flag;
    ImageButton close;
    String text;

    public void showDialog(Activity activity, CountryModel model) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_country);

        name = dialog.findViewById(R.id.nameText);
        text = "Country: "+model.getName();
        name.setText(text);

        capital = dialog.findViewById(R.id.capitalText);
        text = "Capital: "+model.getCapital();
        capital.setText(text);

        flag = dialog.findViewById(R.id.flagImage);
        Uri uri = Uri.parse(model.getFlag());
        GlideToVectorYou.justLoadImage(activity, uri, flag);

        region = dialog.findViewById(R.id.regionText);
        text = "Region: "+model.getRegion();
        region.setText(text);

        subregion = dialog.findViewById(R.id.subregionText);
        text = "Subregion: "+model.getSubregion();
        subregion.setText(text);

        population = dialog.findViewById(R.id.populationText);
        text = "Population: "+model.getPopulation();
        population.setText(text);

        borders = dialog.findViewById(R.id.bordersText);
        text = "Borders: "+model.getBorders();
        borders.setText(text);

        languages = dialog.findViewById(R.id.languagesText);
        text = "Languages: "+model.getLanguages();
        languages.setText(text);

        close = dialog.findViewById(R.id.closeButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
