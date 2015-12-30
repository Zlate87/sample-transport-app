
package com.zlate87.sample_transport_app.feature.routing.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RouteResponse {

    private List<Route> routes = new ArrayList<Route>();
    private Provider_attributes provider_attributes;

    /**
     *
     * @return
     *     The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     *
     * @param routes
     *     The routes
     */
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    /**
     *
     * @return
     *     The provider_attributes
     */
    public Provider_attributes getProvider_attributes() {
        return provider_attributes;
    }

    /**
     *
     * @param provider_attributes
     *     The provider_attributes
     */
    public void setProvider_attributes(Provider_attributes provider_attributes) {
        this.provider_attributes = provider_attributes;
    }

}
