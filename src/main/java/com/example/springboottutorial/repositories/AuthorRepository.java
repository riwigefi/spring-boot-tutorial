package com.example.springboottutorial.repositories;

import com.example.springboottutorial.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
