package com.ftn.android.reimagined_tribble.httpclient;

import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Jozef on 6/30/2016.
 */
@Rest(rootUrl = "http://ftnazure-andoridbackend20160628103944.azurewebsites.net",converters = {MappingJackson2HttpMessageConverter.class})
public interface IBackEnd {

    @Get("/api/users")
    User[] listUsers();

    @Get("/api/Users?email={email}&password={password}")
    User[] getUserswithEmailAndPassword(@Path String email, @Path String password);

    @Post("/api/Users")
    User registerUser(@Body User user);
}
