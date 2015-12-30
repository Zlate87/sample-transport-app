
package com.zlate87.sample_transport_app.feature.routing.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Properties {

    private List<Company> companies = new ArrayList<Company>();

    /**
     * 
     * @return
     *     The companies
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * 
     * @param companies
     *     The companies
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

}
