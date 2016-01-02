
package com.zlate87.sample_transport_app.feature.routing.model;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Callabike implements Serializable {

    private String provider_icon_url;
    private String disclaimer;
    private String android_package_name;
    private String display_name;

    /**
     * 
     * @return
     *     The provider_icon_url
     */
    public String getProvider_icon_url() {
        return provider_icon_url;
    }

    /**
     * 
     * @param provider_icon_url
     *     The provider_icon_url
     */
    public void setProvider_icon_url(String provider_icon_url) {
        this.provider_icon_url = provider_icon_url;
    }

    /**
     * 
     * @return
     *     The disclaimer
     */
    public String getDisclaimer() {
        return disclaimer;
    }

    /**
     * 
     * @param disclaimer
     *     The disclaimer
     */
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    /**
     * 
     * @return
     *     The android_package_name
     */
    public String getAndroid_package_name() {
        return android_package_name;
    }

    /**
     * 
     * @param android_package_name
     *     The android_package_name
     */
    public void setAndroid_package_name(String android_package_name) {
        this.android_package_name = android_package_name;
    }

    /**
     * 
     * @return
     *     The display_name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * 
     * @param display_name
     *     The display_name
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

}
