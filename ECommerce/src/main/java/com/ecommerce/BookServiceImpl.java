package com.ecommerce;

import com.ecommerce.model.Book;
import com.ecommerce.repos.BookRepository;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.util.Cast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;
;
    long currentId = 0;

    @PostConstruct
    private void init() {
        Book book = new Book();
        addBook(new Book(book.getId(), "new", "new", 20.99));
    }

    @Override
    public List<Book> getBooks() {
        Optional<Book> bookOptional = bookRepository.findById(48L);

        Book book = bookOptional.get();
        System.out.println(book.getName());
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            return bookOptional.get();  // Return the book if found
        } else {
            throw new NoSuchElementException("Book not found for ID: " + id); // Throw a custom exception or handle as needed
        }
    }

    @Override
    public void addBook(Book book) {
        if (book != null) {
            bookRepository.save(book);
        }
    }


    @Override
    public void updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            bookRepository.delete(book);
        }
    }
}
