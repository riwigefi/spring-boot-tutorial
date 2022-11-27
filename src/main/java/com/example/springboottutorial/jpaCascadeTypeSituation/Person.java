package com.example.springboottutorial.jpaCascadeTypeSituation;

import javax.persistence.*;
import java.util.List;

/**
 * In this tutorial,we'll discuss what cascading is in JPA/Hibernate.
 * Then we'll cover the various cascade types that are available,along with their semantic.
 *
 * 2. What is Cascading?
 * Entity relationships often depend on the existence of another entity.
 * For example the Person-Address relationship.
 * Without the Person,the Address entity doesn't have any meaning of its own.
 * When we delete the Person entity,our Address entity should also get deleted.
 *
 * Cascading is the way to achieve this.When we perform some action on the target entity,
 * the same action will be applied to the associated entity.
 *
 * 2.1 JPA Cascade Type
 * All JPA-specific cascade operations are represented by the javax.persistence.CascadeType enum containing entries:
 * - ALL
 * - PERSIST
 * - MERGE
 * - REMOVE
 * - REFRESH
 * - DETACH
 *
 * 2.2 Hibernate Cascade Type
 * Hibernate supports three additional Cascade Types along with those specified by JPA.
 * These Hibernate-specific Cascade Types are available in org.hibernate.annotations.CascadeType
 * - REPLICATE(replicate--复制)
 * - SAVE_UPDATE
 * - LOCK
 *
 * 3 Difference Between the Cascade Types
 * 3.1 `CascadeType.ALL`
 * `CascadeType.ALL` propagates all operations -- including Hibernate-specific ones -- from a parent to a child entity
 * Let's see it in an example
 *
 * Note that in OneToMany associations,we've mentioned casacde type in the annotation
 *
 * 3.2 `CascadeType.PERSIST`
 * the persist operation makes a transient instance persistent.
 * Cascade Type PERSIST propagates the persist operation from a parent to a child entity
 * the address entity will also get saved
 *
 * Let's see the test case for a persist operation
 */

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
