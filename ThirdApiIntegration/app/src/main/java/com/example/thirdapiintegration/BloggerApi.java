package com.example.thirdapiintegration;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerApi {
    private static final String url="https://api.exchangeratesapi.io/latest/";
    private static final String base="INR";
    public static Getcurrency getcurrency=null;
    public static Getcurrency netcurrency(){
        if (getcurrency==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            getcurrency=retrofit.create(Getcurrency.class);
        }
        return getcurrency;
    }
    public interface Getcurrency{
        @GET("?base="+base)
        Call<Example> data();
    }
}
