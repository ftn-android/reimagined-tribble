package net.apispark.webapi.resource;

public interface ApiLocationsResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.LocationList} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.LocationList locations_GetLocations();

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Post
    net.apispark.webapi.representation.Location locations_PostLocation(net.apispark.webapi.representation.Location bean);

}