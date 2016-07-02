package com.ftn.android.reimagined_tribble.httpclient;

import com.ftn.android.reimagined_tribble.httpclient.model.User;
import com.ftn.android.reimagined_tribble.httpclient.model.Users;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by Jozef on 6/30/2016.
 */
@Rest(rootUrl = "http://ftnazure-andoridbackend20160628103944.azurewebsites.net",converters = {MappingJackson2HttpMessageConverter.class})
public interface IBackEnd {

    @Get("/api/users")
    User[] listUsers();
}
