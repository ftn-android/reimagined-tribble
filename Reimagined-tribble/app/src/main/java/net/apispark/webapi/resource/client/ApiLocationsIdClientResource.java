package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ApiLocationsIdClientResource {

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
    public ApiLocationsIdClientResource(net.apispark.webapi.Config config, java.lang.String id) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.id = id;
        this.absolutePath = config.getBasePath() + "/api/Locations/{id}";
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Location locations_GetLocation() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiLocationsIdResource.class).locations_GetLocation();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public void locations_PutLocation(net.apispark.webapi.representation.Location bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        client.wrap(net.apispark.webapi.resource.ApiLocationsIdResource.class).locations_PutLocation(bean);
    }

    /**
     * 
     * 
     * @return {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Location locations_DeleteLocation() {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        client.setAttribute("id", this.id);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiLocationsIdResource.class).locations_DeleteLocation();
    }

}
