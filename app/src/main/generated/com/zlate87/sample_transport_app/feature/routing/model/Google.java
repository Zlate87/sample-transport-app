
package com.zlate87.sample_transport_app.feature.routing.model;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Google implements Serializable {

    private String provider_icon_url;
    private String disclaimer;

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

}
