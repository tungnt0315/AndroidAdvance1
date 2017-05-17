package com.example.tung.lesson6_retrofit.restapi;

import com.example.tung.lesson6_retrofit.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tung on 5/16/17.
 */

public interface CountryService {

    @GET("rest/v2/all?fields=name;capital;region;population")
    Call<List<Country>> getAllCountries();

    @GET("rest/v2/name/{keyword}?fields=name;capital;region;population")
    Call<List<Country>> searchCountriesByName(@Path("keyword") String keyword);
}
