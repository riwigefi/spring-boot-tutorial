package com.example.springboottutorial.bootstrap;

import com.example.springboottutorial.domain.Author;
import com.example.springboottutorial.domain.Book;
import com.example.springboottutorial.repositories.AuthorRepository;
import com.example.springboottutorial.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // let's go ahead and set up a couple authors and books
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        // next add in a book to the author
        ddd.getAuthors().add(eric);
        eric.getBooks().add(ddd);

        // save them into the H2 database
        bookRepository.save(ddd);
        authorRepository.save(eric);

        Author rod = new Author("Rood", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "321321321");

        noEJB.getAuthors().add(rod);
        rod.getBooks().add(noEJB);

        bookRepository.save(noEJB);
        authorRepository.save(rod);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books:" + bookRepository.count());
        System.out.println("Number of Authors:" + authorRepository.count());
    }
}
