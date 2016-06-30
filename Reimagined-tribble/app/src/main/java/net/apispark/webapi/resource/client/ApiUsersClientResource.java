package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ApiUsersClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public ApiUsersClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/api/Users";
    }

    /**
     * 
     * 
     * @param username
     *            Parameter "username"
     * @param password
     *            Parameter "password"
     * @return {@link net.apispark.webapi.representation.UserList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.UserList users_GetUsers(java.lang.String username, java.lang.String password) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "username", username);
        QueryParameterHelper.addQueryParameter(client, "password", password);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiUsersResource.class).users_GetUsers();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.User users_PostUser(net.apispark.webapi.representation.User bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiUsersResource.class).users_PostUser(bean);
    }

}
