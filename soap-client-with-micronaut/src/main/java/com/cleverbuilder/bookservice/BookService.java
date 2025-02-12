package com.cleverbuilder.bookservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 4.0.5
 * 2024-10-14T09:28:07.493+02:00
 * Generated source version: 4.0.5
 *
 */
@WebService(targetNamespace = "http://www.cleverbuilder.com/BookService/", name = "BookService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface BookService {

    @WebMethod(operationName = "GetBook", action = "http://www.cleverbuilder.com/BookService/GetBook")
    @WebResult(name = "GetBookResponse", targetNamespace = "http://www.cleverbuilder.com/BookService/", partName = "parameters")
    public GetBookResponse getBook(

        @WebParam(partName = "parameters", name = "GetBook", targetNamespace = "http://www.cleverbuilder.com/BookService/")
        GetBook parameters
    );

    @WebMethod(operationName = "AddBook", action = "http://www.cleverbuilder.com/BookService/AddBook")
    @WebResult(name = "AddBookResponse", targetNamespace = "http://www.cleverbuilder.com/BookService/", partName = "parameters")
    public AddBookResponse addBook(

        @WebParam(partName = "parameters", name = "AddBook", targetNamespace = "http://www.cleverbuilder.com/BookService/")
        AddBook parameters
    );

    @WebMethod(operationName = "GetAllBooks", action = "http://www.cleverbuilder.com/BookService/GetAllBooks")
    @WebResult(name = "GetAllBooksResponse", targetNamespace = "http://www.cleverbuilder.com/BookService/", partName = "parameters")
    public GetAllBooksResponse getAllBooks(

        @WebParam(partName = "parameters", name = "GetAllBooks", targetNamespace = "http://www.cleverbuilder.com/BookService/")
        GetAllBooks parameters
    );
}
