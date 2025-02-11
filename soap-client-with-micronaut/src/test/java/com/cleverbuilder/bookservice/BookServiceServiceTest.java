package com.cleverbuilder.bookservice;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
//@Property(name = "bookservice.location.url", value = "file:///project_location_here/src/main/resources/wsdl/BookService.wsdl")
class BookServiceServiceTest {
    @Inject
    BookService_Service bookService;

    @Test
    void testBeanIsLoaded() {
        assertNotNull(bookService);
        assertEquals("BookService", bookService.getServiceName().getLocalPart());
    }
}