package net.apispark.webapi.resource;

public interface ApiUsersResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.UserList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.UserList users_GetUsers();

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.User users_PostUser(net.apispark.webapi.representation.User bean);

}