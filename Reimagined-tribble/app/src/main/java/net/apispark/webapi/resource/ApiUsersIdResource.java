package net.apispark.webapi.resource;

public interface ApiUsersIdResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.User users_GetUser();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Put
    void users_PutUser(net.apispark.webapi.representation.User bean);

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    net.apispark.webapi.representation.User users_DeleteUser();

}