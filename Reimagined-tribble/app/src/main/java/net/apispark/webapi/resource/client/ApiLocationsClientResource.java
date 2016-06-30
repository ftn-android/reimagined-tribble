package net.apispark.webapi.resource.client;

import net.apispark.webapi.resource.client.QueryParameterHelper;


public class ApiLocationsClientResource {

    private final net.apispark.webapi.security.SecurityRuntimeConfigurator securityRuntimeConfigurator;

    private final java.lang.String absolutePath;

    /**
     * Constructor.
     * 
     * @param config
     *            Gathers configuration of the resource URI and security. 
     */
    public ApiLocationsClientResource(net.apispark.webapi.Config config) {
        this.securityRuntimeConfigurator = config.getSecurityConfig().getSecurityRuntimeConfigurator();
        this.absolutePath = config.getBasePath() + "/api/Locations";
    }

    /**
     * 
     * 
     * @param incident
     *            Parameter "incident"
     *            Required parameter.
     * @param longitude
     *            Parameter "longitude"
     * @param latidude
     *            Parameter "latidude"
     * @param radius
     *            Parameter "radius"
     * @return {@link net.apispark.webapi.representation.LocationList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.LocationList locations_GetLocations(java.lang.Boolean incident, java.lang.Double longitude, java.lang.Double latidude, java.lang.Double radius) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        QueryParameterHelper.addQueryParameter(client, "incident", incident);
        QueryParameterHelper.addQueryParameter(client, "longitude", longitude);
        QueryParameterHelper.addQueryParameter(client, "latidude", latidude);
        QueryParameterHelper.addQueryParameter(client, "radius", radius);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiLocationsResource.class).locations_GetLocations();
    }

    /**
     * 
     * 
     * @param bean
     *            Parameter "bean"
     * @return {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    public net.apispark.webapi.representation.Location locations_PostLocation(net.apispark.webapi.representation.Location bean) {
        org.restlet.resource.ClientResource client = new org.restlet.resource.ClientResource(absolutePath);
        securityRuntimeConfigurator.configure(client);

        return client.wrap(net.apispark.webapi.resource.ApiLocationsResource.class).locations_PostLocation(bean);
    }

}
