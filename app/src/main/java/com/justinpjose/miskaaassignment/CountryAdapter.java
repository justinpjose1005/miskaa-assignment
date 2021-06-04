package com.justinpjose.miskaaassignment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    Context context;
    List<CountryModel> modelList;
    final OnCountryListener onCountryListener;

    public CountryAdapter(Context context, List<CountryModel> modelList, OnCountryListener onCountryListener) {
        this.context = context;
        this.modelList = modelList;
        this.onCountryListener = onCountryListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        return new ViewHolder(view, onCountryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        CountryModel model = modelList.get(position);
        Uri uri = Uri.parse(model.getFlag());
        GlideToVectorYou.justLoadImage((Activity) context, uri, holder.flag);
        holder.name.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView flag;
        TextView name;
        OnCountryListener onCountryListener;

        public ViewHolder(@NonNull View itemView, OnCountryListener onCountryListener) {
            super(itemView);
            flag = itemView.findViewById(R.id.flagImage);
            name = itemView.findViewById(R.id.nameText);
            this.onCountryListener = onCountryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCountryListener.onCountryClick(getAdapterPosition());
        }
    }

    public interface OnCountryListener { // onclick interface
        void onCountryClick(int position);
    }
}
