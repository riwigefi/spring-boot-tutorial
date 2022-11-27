package com.example.springboottutorial.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Again,we configured the relationship earlier,so we only need to tell JPA where can it find that configuration
 * We could also use this solution to address the previous problem of students rating courses.
 * However,it feels weird to create a dedicated primary key unless we have to.
 * 然而，除非有必要，否则创建一个专门的主键会令人奇怪
 * Moreover,from an RDBMS perspective,此外，从RDBMS的角度来看，
 * it doesn't make much sense since combing the two foreign keys made a perfect composite key.
 * 这并没有什么意义，因为将两个外键结合起来就形成了一个完美的复合键
 * Besides,that composite key had a clear meaning:which entities we connect in the relationship
 * 此外，该复合键还有一个清晰的逻辑意义：在定义的实体关系中，我们连接了哪些实体
 * Otherwise,the choice between these two implementations is often simply personal preference
 * 否则，在这两种实现方式之间的选择，往往只是个人的偏好
 *
 * 5 Conclusion
 * In this article,we saw what a many-to-many relationship is and how can we model it in an RDBMS using JPA
 * We saw three ways to model it in JPA.All three have different advantages and disadvantages when it comes to these aspects:
 * - code clarity
 * - DB clarity
 * - ability to assign attributes to the relationship
 * - how many entities we can link with the relationship
 * - support for multiple connections between the same entities.
 * As usual,the examples are available over on GitHub
 */

@Entity
public class CourseRegistration {
    @Id
    Long id;

    // many-to-one
    // many指得是注册信息，逻辑上，一个学生能够拥有多个注册信息（一个学生可以注册多个课程）
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;


    // many-to-one
    // many指得是注册信息，逻辑上，一个课程能够拥有多个注册信息（一个课程可以被注册多次）
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    LocalDate registeredAt;

    int grade;

    // additional properties
    // standard constructors,getters,and setters
}
