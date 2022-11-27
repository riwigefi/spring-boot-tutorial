package com.example.springboottutorial;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Create a JPA entity that will be stored in the database
 * To define the data models for your Spring Application,create JPA entity(实体)
 * The `@Entity` annotation indicates(表明) that the `Consumer class` is JPA entity that
 * should translate into the corresponding table in the database(实体应该转换为数据库中相应的表格)
 * The `@Id` annotation indicates that the `id` field is the object's ID
 * The `@GeneratedValue` tells JPA that the ID should be generated automatically
 */

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
