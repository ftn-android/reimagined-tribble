package com.ftn.android.reimagined_tribble.io.swagger.client.api;

import com.ftn.android.reimagined_tribble.io.swagger.client.ApiInvoker;
import com.ftn.android.reimagined_tribble.io.swagger.client.ApiException;
import com.ftn.android.reimagined_tribble.io.swagger.client.Pair;

import com.ftn.android.reimagined_tribble.io.swagger.client.model.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.ftn.android.reimagined_tribble.io.swagger.client.model.User;

import org.apache.http.HttpEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class UsersApi {
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
     * @return User
     */
    public User usersDeleteUser(Integer id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling usersDeleteUser",
                    new ApiException(400, "Missing the required parameter 'id' when calling usersDeleteUser"));
        }


        // create path and map variables
        String path = "/api/Users/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


        String[] contentTypes = {

        };
        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

       /* if (contentType.startsWith("multipart/form-data")) {
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
                return (User) ApiInvoker.deserialize(localVarResponse, "", User.class);
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
    public void usersDeleteUser(Integer id, final Response.Listener<User> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling usersDeleteUser",
                    new ApiException(400, "Missing the required parameter 'id' when calling usersDeleteUser"));
        }


        // create path and map variables
        String path = "/api/Users/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();


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
            apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((User) ApiInvoker.deserialize(localVarResponse, "", User.class));
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
            return;
        }
    }

    /**
     * @param id
     * @return User
     */
    public User usersGetUser(Integer id) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling usersGetUser",
                    new ApiException(400, "Missing the required parameter 'id' when calling usersGetUser"));
        }


        // create path and map variables
        String path = "/api/Users/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
        }
*/
        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (User) ApiInvoker.deserialize(localVarResponse, "", User.class);
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
    public void usersGetUser(Integer id, final Response.Listener<User> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling usersGetUser",
                    new ApiException(400, "Missing the required parameter 'id' when calling usersGetUser"));
        }


        // create path and map variables
        String path = "/api/Users/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
        }
*/
        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((User) ApiInvoker.deserialize(localVarResponse, "", User.class));
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
            return;
        }
    }

    /**
     * @param username
     * @param password
     * @return List<User>
     */
    public List<User> usersGetUsers(String username, String password) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = null;


        // create path and map variables
        String path = "/api/Users".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "username", username));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));


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
        }
*/
        String[] authNames = new String[]{};

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (List<User>) ApiInvoker.deserialize(localVarResponse, "array", User.class);
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
     * @param username * @param password
     */
    public void usersGetUsers(String username, String password, final Response.Listener<List<User>> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = null;


        // create path and map variables
        String path = "/api/Users".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        // header params
        Map<String, String> headerParams = new HashMap<String, String>();
        // form params
        Map<String, String> formParams = new HashMap<String, String>();

        queryParams.addAll(ApiInvoker.parameterToPairs("", "username", username));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "password", password));


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
        }
*/
        String[] authNames = new String[]{};

        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((List<User>) ApiInvoker.deserialize(localVarResponse, "array", User.class));
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
            return;
        }
    }

    /**
     * @param user
     * @return User
     */
    public User usersPostUser(User user) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = user;

        // verify the required parameter 'user' is set
        if (user == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'user' when calling usersPostUser",
                    new ApiException(400, "Missing the required parameter 'user' when calling usersPostUser"));
        }


        // create path and map variables
        String path = "/api/Users".replaceAll("\\{format\\}", "json");

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
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType, authNames);
            if (localVarResponse != null) {
                return (User) ApiInvoker.deserialize(localVarResponse, "", User.class);
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
     * @param user
     */
    public void usersPostUser(User user, final Response.Listener<User> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = user;


        // verify the required parameter 'user' is set
        if (user == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'user' when calling usersPostUser",
                    new ApiException(400, "Missing the required parameter 'user' when calling usersPostUser"));
        }


        // create path and map variables
        String path = "/api/Users".replaceAll("\\{format\\}", "json");

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

   /*     if (contentType.startsWith("multipart/form-data")) {
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
                                responseListener.onResponse((User) ApiInvoker.deserialize(localVarResponse, "", User.class));
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
            return;
        }
    }

    /**
     * @param id
     * @param user
     * @return void
     */
    public void usersPutUser(Integer id, User user) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        Object postBody = user;

        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling usersPutUser",
                    new ApiException(400, "Missing the required parameter 'id' when calling usersPutUser"));
        }

        // verify the required parameter 'user' is set
        if (user == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'user' when calling usersPutUser",
                    new ApiException(400, "Missing the required parameter 'user' when calling usersPutUser"));
        }


        // create path and map variables
        String path = "/api/Users/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
            return;
        }
    }

    /**
     * @param id * @param user
     */
    public void usersPutUser(Integer id, User user, final Response.Listener<String> responseListener, final Response.ErrorListener errorListener) {
        Object postBody = user;


        // verify the required parameter 'id' is set
        if (id == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'id' when calling usersPutUser",
                    new ApiException(400, "Missing the required parameter 'id' when calling usersPutUser"));
        }

        // verify the required parameter 'user' is set
        if (user == null) {
            VolleyError error = new VolleyError("Missing the required parameter 'user' when calling usersPutUser",
                    new ApiException(400, "Missing the required parameter 'user' when calling usersPutUser"));
        }


        // create path and map variables
        String path = "/api/Users/{id}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
            return;
        }
    }
}
