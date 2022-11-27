package com.example.springboottutorial.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Modeling a many-to-many relationship with POJOs is easy.
 * We should include a Collection in both classes,which contains the elements of the others.
 * After that,we need to mark the class with @Entity and the primary key with @Id to make them proper JPA entities.
 * Also,we should configure the relationship type.
 * So,we mark the collections with @ManyToMany annotations.
 *
 * Additionally,we have to configure how to model the relationship in the RDBMS.
 * The owner side is where we configure the relationship.We'll use the Student class.
 */

@Entity
public class Student {
    @Id
    Long id;

    // collection which contains the elements of the others
    // We can do this with the @JoinTable annotation in the Student class.
    // We provide the name of the join table(course_like) as well as the foreign key with the `@JoinColumn` annotations
    // The joinColumn attribute will connect to the owner side of the relationship,
    // and the inverseJoinColumn to other side
    // Note that using @JoinTable or even @JoinColumn isn't required.
    // JPA weill generate the table and column names for us
    // However,the strategy JPA use won't always match the naming conventions we use
    // So,we need the possibility to configure table and column names

    // On the target side,we only have to provide the name of the field,which maps the relationship

    @ManyToMany
    @JoinTable(
            name = "course_like",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn( name = "course_id")
    )
    Set<Course> likedCourses;


    // we can not have entities in the composite key
    // configure the inverse reference in the Student
    @OneToMany(mappedBy = "student")
    Set<CourseRating> ratings;


    // we also need to configure the relationships in the Student class
    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;


    // additional properties
    // standard constructors,getters,and setters
}
