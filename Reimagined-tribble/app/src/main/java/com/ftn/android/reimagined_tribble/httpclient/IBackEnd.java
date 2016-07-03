package com.ftn.android.reimagined_tribble.httpclient;

import com.ftn.android.reimagined_tribble.httpclient.model.Location;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Jozef on 6/30/2016.
 */
@Rest(rootUrl = "http://ftnazure-andoridbackend20160628103944.azurewebsites.net", converters = {MappingJackson2HttpMessageConverter.class})
public interface IBackEnd {
    // User

    @Get("/api/users")
    User[] listUsers();

    @Get("/api/Users?email={email}&password={password}")
    User[] getUsersWithEmailAndPassword(@Path String email, @Path String password);

    @Post("/api/Users")
    User registerUser(@Body User user);

    // Location

    @Post("/api/Locations")
    Location addNewLocation(@Body Location location);

    @Put("/api/Locations?uid={uid}")
    void updateLocation(@Path String uid, @Body Location location);

    @Get("/api/Locations?typeFilter={typeFilter}")
    Location[] getLocations(@Path TypeFilter typeFilter);

    @Get("/api/Locations?typeFilter={typeFilter}&returnPictureData={returnPictureData}&longitude={longitude}&latitude={latitude}&radius={radius}")
    Location[] getLocationsByRadius(@Path TypeFilter typeFilter,@Path boolean returnPictureData, @Path double longitude, @Path double latitude, @Path double radius);
}