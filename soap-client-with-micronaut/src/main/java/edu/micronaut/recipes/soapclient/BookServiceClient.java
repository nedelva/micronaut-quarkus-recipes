package edu.micronaut.recipes.soapclient;

import com.cleverbuilder.bookservice.*;
import io.micronaut.http.client.ServiceHttpClientConfiguration;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.xml.ws.BindingProvider;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.List;

@Singleton
@Slf4j
public class BookServiceClient {
    private final BookService bookServiceSOAP;

    public BookServiceClient(BookService_Service soapClient,
                             @Named("book-service") ServiceHttpClientConfiguration httpClientConfiguration) {

        bookServiceSOAP = soapClient.getBookServiceSOAP();
        URI bookServiceUri = httpClientConfiguration.getUrls().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Missing configuration 'micronaut.http.services.book-service.urls'"));
        ((BindingProvider) bookServiceSOAP).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, bookServiceUri.toString());
    }

    public Mono<Book> getBook(String bookId) {
        var parameters = new GetBook();
        parameters.setID(bookId);
        return Mono.fromCallable( () -> bookServiceSOAP.getBook(parameters) )
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap( getBookResponse -> Mono.just(getBookResponse.getBook()))
                .onErrorResume(throwable -> {
                    log.error("Failed to get the book with id {}", bookId);
                    return Mono.error(throwable);
                });
    }

    public Mono<List<Book>> getAllBooks() {
        var parameters = new GetAllBooks();
        return Mono.fromCallable(() -> bookServiceSOAP.getAllBooks(parameters))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(getAllBooksResponse -> Mono.just(getAllBooksResponse.getBook()))
                .onErrorResume(throwable -> {
                    log.error("Failed to get the books");
                    return Mono.error(throwable);
                });
    }

    public Mono<Book> addBook(Book book) {
        var parameters = new AddBook();
        parameters.setBook(book);
        return Mono.fromCallable(() -> bookServiceSOAP.addBook(parameters))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(addBookResponse -> Mono.just(addBookResponse.getBook()))
                .onErrorResume(throwable -> {
                    log.error("Failed to add the book {}", book);
                    return Mono.error(throwable);
                });
    }
}
