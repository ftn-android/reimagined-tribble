package net.apispark.webapi.resource;

public interface ApiLocationsIdResource {

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    net.apispark.webapi.representation.Location locations_GetLocation();

    /**
     * 
     *
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Put
    void locations_PutLocation(net.apispark.webapi.representation.Location bean);

    /**
     * 
     *
     * @return  {@link net.apispark.webapi.representation.Location} 
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Delete
    net.apispark.webapi.representation.Location locations_DeleteLocation();

}