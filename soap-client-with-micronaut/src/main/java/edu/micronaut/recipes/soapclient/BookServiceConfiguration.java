package edu.micronaut.recipes.soapclient;

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * This might be used as an alternative to using @Value to inject simple values, if there is a need to pack several
 * injectable properties.
 */
@ConfigurationProperties("soap.clients.booking")
public class BookServiceConfiguration {
    private String wsdlLocationUrl;
    //other properties

    public String getWsdlLocationUrl() {
        return wsdlLocationUrl;
    }

    public void setWsdlLocationUrl(String wsdlLocationUrl) {
        this.wsdlLocationUrl = wsdlLocationUrl;
    }
}
