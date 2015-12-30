
package com.zlate87.sample_transport_app.feature.routing.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Segment {

    private String name;
    private Integer num_stops;
    private List<Stop> stops = new ArrayList<Stop>();
    private String travel_mode;
    private Object description;
    private String color;
    private String icon_url;
    private String polyline;

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
     *     The num_stops
     */
    public Integer getNum_stops() {
        return num_stops;
    }

    /**
     * 
     * @param num_stops
     *     The num_stops
     */
    public void setNum_stops(Integer num_stops) {
        this.num_stops = num_stops;
    }

    /**
     * 
     * @return
     *     The stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * 
     * @param stops
     *     The stops
     */
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    /**
     * 
     * @return
     *     The travel_mode
     */
    public String getTravel_mode() {
        return travel_mode;
    }

    /**
     * 
     * @param travel_mode
     *     The travel_mode
     */
    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Object getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Object description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The color
     */
    public String getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 
     * @return
     *     The icon_url
     */
    public String getIcon_url() {
        return icon_url;
    }

    /**
     * 
     * @param icon_url
     *     The icon_url
     */
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    /**
     * 
     * @return
     *     The polyline
     */
    public String getPolyline() {
        return polyline;
    }

    /**
     * 
     * @param polyline
     *     The polyline
     */
    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

}
