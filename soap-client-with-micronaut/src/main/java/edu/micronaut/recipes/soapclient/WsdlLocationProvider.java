package edu.micronaut.recipes.soapclient;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceLoader;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.net.URL;

@Factory
public class WsdlLocationProvider {
    private static final String BOOKING_WSDL = "wsdl/BookService.wsdl";
    private static final String CUSTOMER_WSDL = "wsdl/CustomerService.wsdl";

    @Inject
    @Named("classpathResourceLoader")
    ResourceLoader resourceLoader;

    @Bean
    @Singleton
    @WsdlLocation("BookService.wsdl")
    public URL provideBookingWsdlLocation() {
        return resourceLoader.getResource(BOOKING_WSDL)
                .orElseThrow(
                        () -> new IllegalStateException(BOOKING_WSDL + " could not be found")
                );
    }

    @Bean
    @Singleton
    @WsdlLocation("CustomerService.wsdl")
    public URL provideCustomerServiceWsdlLocation() {
        return resourceLoader.getResource(CUSTOMER_WSDL)
                .orElseThrow(
                        () -> new IllegalStateException(CUSTOMER_WSDL + " could not be found")
                );
    }
}
