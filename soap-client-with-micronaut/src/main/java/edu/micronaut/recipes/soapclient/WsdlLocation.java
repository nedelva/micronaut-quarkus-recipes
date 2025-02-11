package edu.micronaut.recipes.soapclient;

import io.micronaut.context.annotation.AliasFor;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A qualifier annotation to represent the WSDL file location. This is used by the SOAP client to help injecting URL values.
 * @see WsdlLocationProvider
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface WsdlLocation {
    @AliasFor(annotation = Qualifier.class, member = "value")
    String value() default "";
}
