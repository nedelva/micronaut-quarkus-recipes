package edu.micronaut.recipes.soapclient;

import com.cleverbuilder.bookservice.Book;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
@Requires(property = "micronaut.http.services.book-service.urls")
@Property(name = "micronaut.http.services.book-service.urls", value = "http://localhost:4444/book")
class BookServiceClientTest {
    @RegisterExtension
    static WireMockExtension wmExtension = WireMockExtension.newInstance().options(wireMockConfig().port(4444)).build();

    @Inject
    BookServiceClient bookServiceClient;
    private String aBookWasFoundResponse = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:ns="http://www.cleverbuilder.com/BookService/">
               <soapenv:Header/>
               <soapenv:Body>
                  <ns:GetBookResponse>
                     <ns:Book>
                        <ns:ID>123</ns:ID>
                        <ns:Title>Effective Java</ns:Title>
                        <ns:Author>Joshua Bloch</ns:Author>
                     </ns:Book>
                  </ns:GetBookResponse>
               </soapenv:Body>
            </soapenv:Envelope>
            """;
    private String aListOfBooksResponse = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:ns="http://www.cleverbuilder.com/BookService/">
               <soapenv:Header/>
               <soapenv:Body>
                  <ns:GetAllBooksResponse>
                     <ns:Book>
                        <ns:ID>123</ns:ID>
                        <ns:Title>Effective Java</ns:Title>
                        <ns:Author>Joshua Bloch</ns:Author>
                     </ns:Book>
                     <ns:Book>
                        <ns:ID>456</ns:ID>
                        <ns:Title>Clean Code</ns:Title>
                        <ns:Author>Robert C. Martin</ns:Author>
                     </ns:Book>
                  </ns:GetAllBooksResponse>
               </soapenv:Body>
            </soapenv:Envelope>
            """;

    @Test
    void getBook() {
        wmExtension.stubFor(post(urlMatching("/book"))
                        .withHeader("Accept", equalTo("text/xml, multipart/related"))
                        .withHeader("SOAPAction", equalTo("\"http://www.cleverbuilder.com/BookService/GetBook\""))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/xml")
                        .withStatus(200).withBody(aBookWasFoundResponse))
        );
        Book book = bookServiceClient.getBook("123").block();
        assertNotNull(book);
        assertEquals("Effective Java", book.getTitle());
    }

    @Test
    void getAllBooks() {
        wmExtension.stubFor(post(urlMatching("/book"))
                .withHeader("Accept", equalTo("text/xml, multipart/related"))
                .withHeader("SOAPAction", equalTo("\"http://www.cleverbuilder.com/BookService/GetAllBooks\""))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/xml")
                        .withStatus(200).withBody(aListOfBooksResponse))
        );
        List<Book> books = bookServiceClient.getAllBooks().block();
        assertNotNull(books);
        assertEquals(2, books.size());
        assertEquals("Effective Java", books.get(0).getTitle());
        assertEquals("Clean Code", books.get(1).getTitle());
    }

    @Test
    void addBook() {
    }
}