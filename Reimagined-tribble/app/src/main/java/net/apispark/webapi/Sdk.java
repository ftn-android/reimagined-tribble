package net.apispark.webapi;

/**
 * Entry-point for API calls.
 */
public class Sdk {

    private final net.apispark.webapi.Config config = new net.apispark.webapi.Config();

    public Sdk() {
        org.restlet.engine.Engine.getInstance().getRegisteredConverters().add(new org.restlet.ext.jackson.JacksonConverter());
    }

    /**
     * Returns the SDK configuration.
     */
    public net.apispark.webapi.Config getConfig() {
        return config;
    }

    public net.apispark.webapi.resource.client.ApiLocationsClientResource apiLocations() {
        return new net.apispark.webapi.resource.client.ApiLocationsClientResource(config);
    }

    public net.apispark.webapi.resource.client.ApiLocationsIdClientResource apiLocationsId(java.lang.String id) {
        return new net.apispark.webapi.resource.client.ApiLocationsIdClientResource(config, id);
    }

    public net.apispark.webapi.resource.client.ApiUsersClientResource apiUsers() {
        return new net.apispark.webapi.resource.client.ApiUsersClientResource(config);
    }

    public net.apispark.webapi.resource.client.ApiUsersIdClientResource apiUsersId(java.lang.String id) {
        return new net.apispark.webapi.resource.client.ApiUsersIdClientResource(config, id);
    }

}
