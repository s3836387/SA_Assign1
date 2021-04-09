package com.company.myClass;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentEnrolmentManagerTest {
    StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
    @Test
    void createFile() throws IOException {
        assertFalse(StudentEnrolmentManager.createFile("src/com/company/resource/default.csv"));
        assertTrue(StudentEnrolmentManager.createFile("src/com/company/resource/default2.csv"));
    }

    @Test
    void writeFile() throws IOException {
        Student student =new Student("s3836387","Ngo Quang khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2440","Software Architecture Design and Implementation",12);
        List<StudentEnrolment> list = new ArrayList<>();
        list.add( new StudentEnrolment(student,course,"2020A"));
        StudentEnrolmentManager.writeFile(list,"src/com/company/resource/forTest.csv");
    }

    @Test
    void checkFile() throws IOException {
        assertFalse(StudentEnrolmentManager.createFile("src/com/company/resource/default2.csv"));
        assertTrue(StudentEnrolmentManager.createFile("src/com/company/resource/default.csv"));
    }

    @Test
    void populateData() throws IOException {
        manager.populateData();
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        assertEquals(student.getId(),manager.getStudentList().get(0).getId());
        assertEquals(course.getId(),manager.getCourseList().get(0).getId());
        assertEquals("S1011535",manager.getStudentEnrolmentsList().get(0).getStudentId());
        assertEquals("COSC3321",manager.getStudentEnrolmentsList().get(0).getCourseId());
        assertEquals("2021A",manager.getStudentEnrolmentsList().get(0).getSemester());
    }

    @Test
    void testPopulateData() throws IOException {
        manager.populateData("src/com/company/resource/default.csv");
        assertEquals("S1011535",manager.getStudentList().get(0).getId());
        assertEquals("COSC3321",manager.getCourseList().get(0).getId());
        assertEquals("S1011535",manager.getStudentEnrolmentsList().get(0).getStudentId());
        assertEquals("COSC3321",manager.getStudentEnrolmentsList().get(0).getCourseId());
        assertEquals("2021A",manager.getStudentEnrolmentsList().get(0).getSemester());

//        assertThrows(NullPointerException.class, () -> {
//            manager.getStudent("s3836385").getId();
//        });
    }

    @Test
    void getStudent() throws IOException {
        manager.populateData();
        assertEquals("s3836387",manager.getStudent("s3836387").getId());
    }
    @Test
    void getStudentreturnNull() throws IOException {
        manager.populateData();
        assertEquals(null,manager.getStudent("s4836485"));
    }

    @Test
    void getCourse() throws IOException {
        manager.populateData();
        assertEquals("COSC2083",manager.getCourse("COSC2083").getId());
    }
    @Test
    void getCoursereturnNull() throws IOException {
        manager.populateData();
        assertEquals(null,manager.getCourse("COSC2033"));
    }

    @Test
    void add() throws IOException {
        manager.populateData();
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        assertTrue(manager.add(student,course,"2020A"));
    }
    @Test
    void addDuplicate() throws IOException {
        manager.populateData();
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        assertFalse(manager.add(student,course,"2020A"));
    }

    @Test
    void update() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        System.out.println(manager.getStudentEnrolmentsList().get(0));
        StudentEnrolment newEnrol = manager.getStudentEnrolmentsList().get(0);
        newEnrol.setStudent(student2);
        manager.update(newEnrol);
        System.out.println(manager.getStudentEnrolmentsList().get(0));
    }

    @Test
    void delete() {
    }

    @Test
    void getOne() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getStudentEnrolmentByStudentIdNSem() {
    }

    @Test
    void getStudentsEnrolmentByCourseNSem() {
    }

    @Test
    void getStudentsEnrolmentBySem() {
    }
}