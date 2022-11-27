package com.example.springboottutorial.repositories;

import com.example.springboottutorial.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
