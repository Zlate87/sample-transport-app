
package com.zlate87.sample_transport_app.feature.routing.model;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Stop implements Serializable {

    private Double lat;
    private Double lng;
    private String datetime;
    private String name;
    private Object properties;

    /**
     * 
     * @return
     *     The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * 
     * @param lng
     *     The lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * 
     * @return
     *     The datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * 
     * @param datetime
     *     The datetime
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The properties
     */
    public Object getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     *     The properties
     */
    public void setProperties(Object properties) {
        this.properties = properties;
    }

}
