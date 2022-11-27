package com.example.springboottutorial.domain;


import javax.persistence.*;

/**
 * Using this composite key class,we can create the entity class,which models the join table
 * This code is very similar to a regular entity implementation
 * However,we have some key difference
 * - We used @EmbeddedId to mark the primary key,which is an instance of the CourseRatingKey class
 * - We marked the student and course field with @MapsId
 * `@MapsId(fieldName)` means that we tie those field to a part of the primary key,
 * @MapsId(fieldName)意味这我们将多对一关系中的实体的名为fieldName的字段与主键联系起来
 * and they're the foreign keys of a many-to-one relationship
 * fieldName字段与主键是多对一欢喜的外键
 * We need it because,as we mentioned,we can not have entities in the composite key
 * 我们需要用到CourseRating，因为我们不能在复合键中拥有实体
 * After this,we can configure the inverse reference in the Student and Course entities as before
 */

@Entity
public class CourseRating {

    @EmbeddedId
    CourseRatingKey id; // primary key, which is an instance of the CourseRatingKey class

    // many-to-one
    // 这里的many是指CourseRating,one是指Student,逻辑上说一个学生可以有多个课程的分数
    // 使用学生id和CourseRating的主键id中的学生id联系起来，这种联系将是课程和学生多对一关系的外键
    @ManyToOne
    @MapsId("studentId") // @MapsId意味我们将studentId与主键id(CourseRatingKey)的一部分(student_id)联系起来，它们是多对一关系的外键
    @JoinColumn(name = "student_id")
    Student student;

    // many-to-one
    // 这里的many是指CourseRating,one是指Course,逻辑上说一个课程可以有多个学生打的分数
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    // store rating
    int rating;

    // Standard constructors,getters,and setters
}
