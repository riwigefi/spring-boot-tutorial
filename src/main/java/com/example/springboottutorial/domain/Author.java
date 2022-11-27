package com.example.springboottutorial.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * JavaPersistentAPI
 * JPA Model
 * Creating POJOs
 * JPA id's
 * JPA Relationships
 * We need to give JPA an identity value that this is assigned to these classes
 * So it knows how to store these into database and retrieve them from the database
 * So this is typically what we call it as a leakage
 * because now we have our pure object model
 */

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    // We can do this with the @JoinTable annotation in the Author Class
    // We provide the name of the join table(author_books) as well as the foreign key with @JoinColumn annotations
    // The joinColumn attribute will connect to the owner side of the relationship
    // and the inverseJoinColumn to other side
    // Target side is Author Class,other side is Book Class
    // author and book relationship is many-to-many
    // the join table is called "author_book"
    @ManyToMany()
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"), // author foreign key
            inverseJoinColumns = @JoinColumn(name = "book_id") // book foreign key
    )
    private Set<Book> books =new HashSet<>();

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : 0;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }
}
