package com.example.springboottutorial.jpaCascadeTypeSituation;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String street;

    private int houseNumber;

    private String city;

    private int zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
