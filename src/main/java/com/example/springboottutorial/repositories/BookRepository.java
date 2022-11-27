package com.example.springboottutorial.repositories;

import com.example.springboottutorial.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
