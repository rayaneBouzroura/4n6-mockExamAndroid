package com.rayanebouzroura.exam2023secondshot.retrofit;


import retrofit2.converter.gson.GsonConverterFactory;

//TODO RETROFIT 1 : follow along
public class Retrofit {

    //TODO RETROFIT 2 :  save up the baseurl de tes calls
    private static final String BASE_URL = "https://examen-final-h23.azurewebsites.net/";

    //TODO RETROFIT 3 : create generic service getter
    public static Service get(){
        retrofit2.Retrofit rf = new retrofit2.Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //TODO RETROFIT 4 : RETURN THE SERVICE
        return rf.create(Service.class);
    }
}
