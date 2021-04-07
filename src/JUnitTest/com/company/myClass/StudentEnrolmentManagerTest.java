package com.company.myClass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentEnrolmentManagerTest {
    dataObjectFactory data = new dataObjectFactory();
    Student student =new Student("s3836387","Ngo Quang khai", LocalDate.parse("2000-12-02"));
    Course course =new Course("COSC2440","Software Architecture Design and Implementation",12);
    StudentEnrolmentManager manager = new StudentEnrolmentManager();
    @Test
    void add() {
        boolean output = manager.add(student,course,"2021A");
        assertTrue(output);
        output = manager.add(student,course,"2021A");
        assertFalse(output);
    }
}