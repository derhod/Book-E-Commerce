package com.ecommerce;
import com.ecommerce.model.Book;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Consumes("application/json,application/xml")
@Produces("application/json,application/xml")
@Path("/bookservice")
public interface IBookService {

    @Path("/books")
    @GET
    List<Book> getBooks();

    @Path("/books/{id}")
    @GET
   Book getBookById(@PathParam("id") Long id);



    @Path("/books")
    @POST
    void addBook(Book book);

    @Path("/books/{id}")
    @PUT
    void updateBook(Long id, Book book);

    @Path("/books/{id}")
    @DELETE
    void deleteBook(Long id);





}
