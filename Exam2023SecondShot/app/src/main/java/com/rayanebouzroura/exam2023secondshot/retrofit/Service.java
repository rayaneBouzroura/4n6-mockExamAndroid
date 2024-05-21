package com.rayanebouzroura.exam2023secondshot.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {


    //TODO SERVICE 1: get api calls
    @GET("Exam2023/{number}")
    Call<ResponseBody> getNm(@Path("number")String number);
}
