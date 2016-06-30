package com.ftn.android.reimagined_tribble.httpclient;

import com.ftn.android.reimagined_tribble.httpclient.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jozef on 6/30/2016.
 */
public class BackEnd implements IBackEnd {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ftnazure-andoridbackend20160628103944.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static IBackEnd backEnd = retrofit.create(IBackEnd.class);

    @Override
    public Call<List<User>> listUsers() {
        IBackEnd backEnd = retrofit.create(IBackEnd.class);
        return backEnd.listUsers();
    }
}
