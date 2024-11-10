package com.ecommerce.repos;

import com.ecommerce.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
   // Book findByName(String name);

}
