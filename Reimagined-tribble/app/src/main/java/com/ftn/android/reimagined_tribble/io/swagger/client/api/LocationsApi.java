package com.ftn.android.reimagined_tribble.io.swagger.client.api;

import com.ftn.android.reimagined_tribble.io.swagger.client.ApiInvoker;
import com.ftn.android.reimagined_tribble.io.swagger.client.ApiException;
import com.ftn.android.reimagined_tribble.io.swagger.client.Pair;

import com.ftn.android.reimagined_tribble.io.swagger.client.model.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.ftn.android.reimagined_tribble.io.swagger.client.model.Location;

import org.apache.http.HttpEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class LocationsApi {
    String basePath = "http://ftnazure-andoridbackend20160628103944.azurewebsites.net";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    public void addHeader(String key, String value) {
        getInvoker().addDefaultHeader(key, value);
    }

    public ApiInvoker getInvoker() {
        return apiInvoker;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    /**
     * @param id
     * @return Location
     */
    public Location locationsDeleteLocation(Integer id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling locationsDeleteLocation",
                    new ApiException(400, "Missing the required parameter 'id' when calling locationsDeleteLocation"));
        }


        // create path and map variables
        String path = "/api/Locations/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

     /*   if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }*/

        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Location) ApiInvoker.deserialize(localVarResponse, "", Location.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        } catch (InterruptedException ex) {
            throw ex;
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw ex;
        } catch (TimeoutException ex) {
            throw ex;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @param id
     */
    public void locationsDeleteLocation(Integer id, final Response.Listener<Location> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling locationsDeleteLocation",
                    new ApiException(400, "Missing the required parameter 'id' when calling locationsDeleteLocation"));
        }


        // create path and map variables
        String path = "/api/Locations/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

     /*   if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }*/

        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Location) ApiInvoker.deserialize(localVarResponse, "", Location.class));
                            } catch (ApiException exception) {
                                errorListener.onErrorResponse(new VolleyError(exception));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return Location
     */
    public Location locationsGetLocation(Integer id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling locationsGetLocation",
                    new ApiException(400, "Missing the required parameter 'id' when calling locationsGetLocation"));
        }


        // create path and map variables
        String path = "/api/Locations/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

      /*  if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }
*/
        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Location) ApiInvoker.deserialize(localVarResponse, "", Location.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        } catch (InterruptedException ex) {
            throw ex;
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw ex;
        } catch (TimeoutException ex) {
            throw ex;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @param id
     */
    public void locationsGetLocation(Integer id, final Response.Listener<Location> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling locationsGetLocation",
                    new ApiException(400, "Missing the required parameter 'id' when calling locationsGetLocation"));
        }


        // create path and map variables
        String path = "/api/Locations/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    /*    if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }*/

        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Location) ApiInvoker.deserialize(localVarResponse, "", Location.class));
                            } catch (ApiException exception) {
                                errorListener.onErrorResponse(new VolleyError(exception));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param incident
     * @param longitude
     * @param latidude
     * @param radius
     * @return List<Location>
     */
    public List<Location> locationsGetLocations(Boolean incident, Double longitude, Double latidude, Double radius) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'incident' is set
        if (incident == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'incident' when calling locationsGetLocations",
                    new ApiException(400, "Missing the required parameter 'incident' when calling locationsGetLocations"));
        }


        // create path and map variables
        String path = "/api/Locations".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "incident", incident));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "longitude", longitude));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "latidude", latidude));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "radius", radius));


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

     /*   if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }*/

        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (List<Location>) ApiInvoker.deserialize(localVarResponse, "array", Location.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        } catch (InterruptedException ex) {
            throw ex;
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw ex;
        } catch (TimeoutException ex) {
            throw ex;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @param incident * @param longitude    * @param latidude    * @param radius
     */
    public void locationsGetLocations(Boolean incident, Double longitude, Double latidude, Double radius, final Response.Listener<List<Location>> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // verify the required parameter 'incident' is set
        if (incident == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'incident' when calling locationsGetLocations",
                    new ApiException(400, "Missing the required parameter 'incident' when calling locationsGetLocations"));
        }


        // create path and map variables
        String path = "/api/Locations".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "incident", incident));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "longitude", longitude));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "latidude", latidude));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "radius", radius));


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

   /*     if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }*/

        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((List<Location>) ApiInvoker.deserialize(localVarResponse, "array", Location.class));
                            } catch (ApiException exception) {
                                errorListener.onErrorResponse(new VolleyError(exception));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param location
     * @return Location
     */
    public Location locationsPostLocation(Location location) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = location;

        // verify the required parameter 'location' is set
        if (location == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'location' when calling locationsPostLocation",
                    new ApiException(400, "Missing the required parameter 'location' when calling locationsPostLocation"));
        }


        // create path and map variables
        String path = "/api/Locations".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    /*    if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }*/

        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Location) ApiInvoker.deserialize(localVarResponse, "", Location.class);
            } else {
                return null;
            }
        } catch (ApiException ex) {
            throw ex;
        } catch (InterruptedException ex) {
            throw ex;
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw ex;
        } catch (TimeoutException ex) {
            throw ex;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @param location
     */
    public void locationsPostLocation(Location location, final Response.Listener<Location> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = location;


        // verify the required parameter 'location' is set
        if (location == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'location' when calling locationsPostLocation",
                    new ApiException(400, "Missing the required parameter 'location' when calling locationsPostLocation"));
        }


        // create path and map variables
        String path = "/api/Locations".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

     /*   if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }
*/
        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Location) ApiInvoker.deserialize(localVarResponse, "", Location.class));
                            } catch (ApiException exception) {
                                errorListener.onErrorResponse(new VolleyError(exception));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @param location
     * @return void
     */
    public void locationsPutLocation(Integer id, Location location) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = location;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling locationsPutLocation",
                    new ApiException(400, "Missing the required parameter 'id' when calling locationsPutLocation"));
        }

        // verify the required parameter 'location' is set
        if (location == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'location' when calling locationsPutLocation",
                    new ApiException(400, "Missing the required parameter 'location' when calling locationsPutLocation"));
        }


        // create path and map variables
        String path = "/api/Locations/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

     /*   if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }
*/
        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return;
            } else {
                return;
            }
        } catch (ApiException ex) {
            throw ex;
        } catch (InterruptedException ex) {
            throw ex;
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw ex;
        } catch (TimeoutException ex) {
            throw ex;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id * @param location
     */
    public void locationsPutLocation(Integer id, Location location, final Response.Listener<String> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = location;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling locationsPutLocation",
                    new ApiException(400, "Missing the required parameter 'id' when calling locationsPutLocation"));
        }

        // verify the required parameter 'location' is set
        if (location == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'location' when calling locationsPutLocation",
                    new ApiException(400, "Missing the required parameter 'location' when calling locationsPutLocation"));
        }


        // create path and map variables
        String path = "/api/Locations/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {
                "application/json", "text/json", "application/xml", "text/xml", "application/x-www-form-urlencoded"
        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    /*    if (contentType.startsWith("multipart/form-data")) {
            // file uploading
            MultipartEntityBuilder localVarBuilder = MultipartEntityBuilder.create();


            HttpEntity httpEntity = localVarBuilder.build();
            postBody = httpEntity;
        } else {
            // normal form params
        }
*/
        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            responseListener.onResponse(localVarResponse);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
