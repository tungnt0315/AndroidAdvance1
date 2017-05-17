package com.example.tung.lesson6_retrofit_okhttp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.lesson6_retrofit_okhttp.R;
import com.example.tung.lesson6_retrofit_okhttp.model.Country;

import java.util.List;

/**
 * Created by tung on 5/16/17.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Context mContext;
    private List<Country> mcCountries;

    public CountryAdapter(Context context, List<Country> questions){
        mContext = context;
        mcCountries = questions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mcCountries.get(position));
    }

    @Override
    public int getItemCount() {
        return mcCountries == null ? 0 : mcCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvCapital, tvRegion, tvPopulation;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCapital = (TextView) itemView.findViewById(R.id.tv_capital);
            tvRegion = (TextView) itemView.findViewById(R.id.tv_region);
            tvPopulation = (TextView) itemView.findViewById(R.id.tv_population);
        }

        public void bindData(Country country){
            tvName.setText(country.getName());
            tvCapital.setText("Capital: "+country.getCapital());
            tvRegion.setText("Region: "+country.getRegion());
            tvPopulation.setText("Population: "+country.getPopulation());
        }
    }
}
