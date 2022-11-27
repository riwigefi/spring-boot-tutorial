package com.example.springboottutorial.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Many-to-Many Using a Composite Key
 * Modeling Relationship Attributes
 * Let's say we want to let students rate the course.
 * A student can rate any number of courses,and any number of student can rate the same course.
 * Therefore,it's also a many-to-many relationship.
 * What makes this example a bit more complicated is that
 * there is more to the rating relationship that the fact which it exists.
 * We need to store the rating score the student gave on the course
 * Where can we store this information?
 * We can't put it in the Student entity,since a student can give different rating to different courses.
 * Similarly,storing it in the Course entity wouldn't be a good solution either.
 * This is a situation when the relationship itself has an attribute
 * Using this example,attaching an attribute to a relation look like this in an ER diagram
 * We can model is almost the same way as the simple many-to-many relationship.
 * The only difference is that we attach a new attribute to the join table
 *
 * .32 Creating a Composite Key in JPA
 * The implementation of a simple many-to-many relationship was rather straightforward(直接了当的，清晰的)
 * The only problem is that we cannot add a property to a relationship that way
 * because we connected the entities directly.
 * Therefore,we had no way to add a property to the relationship itself
 * Since we map DB attributes to class fields in JPA,we need to create a new entity class for the relationship
 * Of course,every JPA entity needs a primary key.
 * Because our primary key is composite key,we have to create a new class that will hold the different parts of the key
 *
 * Note the below code
 * a composite key class has to fulfill some key requirements
 * - We have to mark with @Embeddable
 * - It has to implement java.io.Serializable
 * - We need to provide an implementation of the hasCode() and equals() methods
 * - None of the fields can be an entity themselves(没有一个字段本身可以是是一个实体)
 *
 *
 * Further Characteristics(未来的特质)
 * We configured  the relationship to the Student and Course classes as @ManyToOne.
 * // 我们将Student class和Course class的关系设置为`@ManyToOne`
 * We could do this
 * 我们完全可以这样做
 * because with the new entity we structurally decomposed the many-to-many relationship to two many-to-one relationships
 * 因为有了新的实体CourseRating,我们在结构上将多对多的关系分解为两个多对一的关系
 *
 * Why were we able to do this?
 * If we inspect the tables closely in the previous case,we can see that it contained two many-to-one relationships
 * In other words,there isn't any many-to-many relationship in an RDBMS.
 * We call the structure we create with join tables many-to-many relationship because that's what we model.
 *
 * Besides,it's more clear if we talk about many-to-many relationships because that's our intention.
 * Meanwhile,a join table is just an implementation detail;we don't really care about it.
 *
 * Moreover,this solution has an additional feature we have not mentioned yet.
 * The simple many-to-many solution created a relationship between two entities.
 * Therefore,we can not expand the relationship to more entities.
 * But we don't have this limit in this solution;we can model relationships between any number of entity types.
 *
 *
 * 4 Many-to-Many With a New Entity
 * 4.1 Modeling Relationship Attributes
 *
 * Let's say we want to let students register for courses.比方说，我们想让学生注册课程
 * Also,we need to store the point when a student registered for a specific course.同时，我们需要存储一个学生注册特定课程的时间节点
 * On the top of that,we want to store what grade she received in the course.除此之外，我们还想存储她在该课程里获得的成绩
 *
 * In an ideal world,we could solve this with the previous solution,where we had entity which a composite key.
 * However,the world is far from ideal,and students don't always accomplish a course on the first try.
 * 然而真实世界远非理想世界，学生并不是在第一次尝试时就会完成课程
 *
 * In this case,there are **multiple connections between the same student-course pairs**,
 * or multiple rows,with the same student_id-course_id pairs.
 * We can not model it using any of the previous solution because all primary kes must be unique.
 * So we need to use a separate primary key.
 *
 * Therefore,we can introduce an entity,which will hold the attributes of the registration
 * (因此，我们可以引入一个实体，这个实体将持有Registration(注册)的所有属性)
 * In this case,the Registration entity represent the relationship between the other two entities.
 * Since it's an entity,it will have its own primary key.
 * In the previous solution,remember that we had a composite primary key we created from the two foreign keys.
 *
 * Now the two foreign keys won't be part of the primary key
 *
 * 4.2 Implementation in JPA
 * Since the course_registration became a regular table,we can create a plain old JPA entity modeling it
 */


@Embeddable
public class CourseRatingKey implements Serializable {
    @Column(name = "student_id")
    Long studentId;

    @Column(name = "course_id")
    Long courseId;

    // standard constructors,getters,and setters
    // hashcode and equals implementation
}
