package com.ecommerce.controller;

import com.ecommerce.BookServiceImpl;
import com.ecommerce.model.Book;
import com.ecommerce.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/bookservice/")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping(value ="/books", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping(value ="/books", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        try{
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + " no book found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value ="/books/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id){
        try{
            Book existingBook = bookService.getBookById(id);
            bookService.addBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id){
        try {
            bookService.deleteBook(id);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " no book found");
        }
    }
}
