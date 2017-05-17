package com.example.tung.lesson6_retrofit;

import android.app.ProgressDialog;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tung.lesson6_retrofit.adapter.CountryAdapter;
import com.example.tung.lesson6_retrofit.model.Country;
import com.example.tung.lesson6_retrofit.restapi.CountryService;
import com.example.tung.lesson6_retrofit.restapi.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcvCountries;
    Button btnLoadAll;
    Button btnSearch;
    EditText etSearch;
    CountryAdapter adapter;
    List<Country> countries = new ArrayList<>();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
    }

    @Override
    public void onClick(View v) {
        countries.clear();
        adapter.notifyDataSetChanged();
        progressDialog.show();
        switch (v.getId()) {
            case R.id.btn_search:
                String keyword = etSearch.getText().toString().trim();
                if ("".equals(keyword)) loadAllCountries();
                else searchCountry(keyword);
                break;
            case R.id.btn_load_all:
                etSearch.setText("");
                loadAllCountries();
        }
    }

    private void searchCountry(String keyword) {
        CountryService service = ServiceGenerator.createService(CountryService.class);
        service.searchCountriesByName(keyword).enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.body() != null) {
                    for (Country country : response.body()) {
                        countries.add(0, country);
                        adapter.notifyItemInserted(0);
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("Error", "Error");
                progressDialog.dismiss();
            }
        });
    }

    private void loadAllCountries() {
        CountryService service = ServiceGenerator.createService(CountryService.class);
        service.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                for (Country country : response.body()) {
                    countries.add(0, country);
                    adapter.notifyItemInserted(0);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("Error", "Error");
                progressDialog.dismiss();
            }
        });
    }

    private void initWidget() {
        etSearch = (EditText) findViewById(R.id.et_search);
        btnLoadAll = (Button) findViewById(R.id.btn_load_all);
        btnLoadAll.setOnClickListener(this);
        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        rcvCountries = (RecyclerView) findViewById(R.id.rcv_countries);
        adapter = new CountryAdapter(this, countries);
        rcvCountries.setAdapter(adapter);
        rcvCountries.setLayoutManager(new LinearLayoutManager(this));
//        rcvCountries.addItemDecoration( new DividerItemDecoration(this, 1));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
    }
}
