package edu.micronaut.recipes.soapclient;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class SoapClientWithMicronautTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {

        Assertions.assertTrue(application.isRunning());
        java.util.HashMap<String, String> map = new java.util.HashMap<>();
        map.put(null, "3");
        System.out.println("map = " + map.get(null));
    }

}
