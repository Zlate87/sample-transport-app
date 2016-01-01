
package com.zlate87.sample_transport_app.feature.routing.model;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Price implements Serializable {

    private String currency;
    private Integer amount;

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
