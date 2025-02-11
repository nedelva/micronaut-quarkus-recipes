
package xyz.tomd.customerservice.types;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xyz.tomd.customerservice.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CustomerItem_QNAME = new QName("http://customerservice.tomd.xyz/types/", "CustomerItem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xyz.tomd.customerservice.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomersElement }
     * 
     */
    public GetCustomersElement createGetCustomersElement() {
        return new GetCustomersElement();
    }

    /**
     * Create an instance of {@link CustomerItem }
     * 
     */
    public CustomerItem createCustomerItem() {
        return new CustomerItem();
    }

    /**
     * Create an instance of {@link GetCustomersResponseElement }
     * 
     */
    public GetCustomersResponseElement createGetCustomersResponseElement() {
        return new GetCustomersResponseElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerItem }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomerItem }{@code >}
     */
    @XmlElementDecl(namespace = "http://customerservice.tomd.xyz/types/", name = "CustomerItem")
    public JAXBElement<CustomerItem> createCustomerItem(CustomerItem value) {
        return new JAXBElement<CustomerItem>(_CustomerItem_QNAME, CustomerItem.class, null, value);
    }

}
