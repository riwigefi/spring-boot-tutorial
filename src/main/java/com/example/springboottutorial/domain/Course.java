package com.example.springboottutorial.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Course {
    @Id
    Long id;

    // On the Student Class, many-to-many field name is "likedCourses"
    // So on the Course Class,we only have to provide the name of the field,which maps the relationship

    // Keep in mind that since "a many-to-many relationship doesn't have an owner side in the database",
    // we could configure the join table in the Course class and reference it from the Student class.
    @ManyToMany(mappedBy = "likedCourses")
    Set<Student> likes;

    // configure the inverse reference in the Course class
    @OneToMany(mappedBy = "course")
    Set<CourseRating> ratings;

    // we also need to configure the relationships in the Course Class
    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;

    // Note that there's an alternative way to use composite keys: the `@IdClass` annotation
}
