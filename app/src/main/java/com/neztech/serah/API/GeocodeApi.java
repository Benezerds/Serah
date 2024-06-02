package com.neztech.serah.API;

import com.neztech.serah.API.response.GeocodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodeApi {
    @GET("maps/api/geocode/json")
    Call<GeocodeResponse> getGeocode(
            @Query("latlng") String latlng,
            @Query("result_type") String resultType,
            @Query("key") String key
    );
}

