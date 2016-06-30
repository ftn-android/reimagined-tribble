package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ApiUsersIdClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private java.lang.String id;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     * @param id
     *            Attribute "id"
     */
    public ApiUsersIdClientResource(net.apispark.webapi.Config config, java.lang.String id) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.id = id;
        this.absolutePath = config.getBasePath() + "/api/Users/{id}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.User users_GetUser() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiUsersIdResource.class).users_GetUser();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void users_PutUser(net.apispark.webapi.representation.User bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.ApiUsersIdResource.class).users_PutUser(bean);
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.User} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.User users_DeleteUser() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiUsersIdResource.class).users_DeleteUser();
    }

}
