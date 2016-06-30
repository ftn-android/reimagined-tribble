package com.ftn.android.reimagined_tribble.httpclient;

import com.ftn.android.reimagined_tribble.httpclient.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jozef on 6/30/2016.
 */
public interface IBackEnd {
    @GET("api/users")
    Call<List<User>> listUsers();
}
