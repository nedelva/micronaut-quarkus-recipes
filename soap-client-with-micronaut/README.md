## Micronaut 4.6.3 Documentation

- [User Guide](https://docs.micronaut.io/4.6.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.6.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.6.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

### How to 

1. Add cxf-codegen-plugin to the POM file to generate Java code from WSDL file(s). The generated sources are
  located in `target/generated-sources/cxf`. Here we use two services, BookService and CustomerService. The plugin will generate /
  two interfaces for each service, one annotated with `@WebService` and the other with `@WebServiceClient`. Do a `mvn verify` to 
  make sure it compiles. 

2. Move the generated code to the regular place `src/java` (keep the packages). We need to modify the interfaces annotated with @WebServiceClient. /
  Remove the plugin cxf-codegen-plugin from POM and do a `mvn clean compile` (it should normally compile).   
3. We can proceed further now: modification of the generated classes annotated with `@WebServiceClient`.
4. Solving problem 1: we want to transform these classes into managed beans so that they can be instantiated by the framework
   when they are needed.
   * the answer should be simple enough, right?  Annotate them with `@jakarta.inject.Singleton`:
   ```java
   import jakarta.inject.Singleton;    
   @Singleton public class BookService_Service extends Service { //etc 
        }
   ```
   * Let's write an integration test (here a `@MicronautTest`) to see if the managed bean is loaded:
   
   ```java
     @MicronautTest
     class BookServiceServiceTest {
        @Inject
        BookService_Service bookService;
  
        @Test
        void testBeanIsLoaded() {
          assertNotNull(bookService);
        }
     }
   ```

   Unfortunately when we run it we get an exception: 
  >io.micronaut.context.exceptions.DependencyInjectionException: Failed to inject value for parameter [wsdlLocation] of class: com.cleverbuilder.bookservice.BookService_Service
  Message: No bean of type [java.net.URL] exists.
  
6. Here comes problem 2: how do we pass the WSDL location so that the URL is also provided by the Micronaut ? 
   We have a couple of options.
 
    1. Use @Value annotation to inject either a hard-coded value or a property from the environment. Constructor signature changes to
    ```java
   public BookService_Service(@Value("${bookservice.location.url}") URL wsdlLocation)
    ``` 
   We are not done yet. The test needs to provide this property, so in the `BookServiceClientTest` we add `@Property` annotation, like so:
    ```java
   @MicronautTest
   @Property(name = "bookservice.location.url", value = "file:///some_drive/some_path/BookService.wsdl")
   class BookServiceServiceTest { //...
    ``` 
   The trouble with this approach is that you are forced to produce an absolute path as the value for @Property; not usable when the values vary per environment.
    2. Use `@ConfigurationProperties` annotation. Similar to Spring, Micronaut allows you to bind configuration properties to a POJO using the @ConfigurationProperties annotation.
    3. Use `@Factory` to produce the needed value(s) in conjunction with a custom qualifier annotation. The annotation is needed to distinguish between different URL values.
   ```java
   
    ```
8. Finally,