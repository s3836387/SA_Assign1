package com.company.myClass;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentEnrolmentManagerTest {
    StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();

    @AfterEach
    void After() {
        manager.getStudentList().clear();
        manager.getCourseList().clear();
        manager.getStudentEnrolmentsList().clear();
    }

    @Test
    void createFile() throws IOException {
        assertFalse(StudentEnrolmentManager.createFile("src/com/company/resource/default.csv"));
        assertTrue(StudentEnrolmentManager.createFile("src/com/company/resource/default2.csv"));
        // REMEMBER: Delete created default2.csv file in the src/com/company/resource/default2.csv
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
        assertFalse(StudentEnrolmentManager.checkFile("src/com/company/resource/something.csv"));
        assertTrue(StudentEnrolmentManager.checkFile("src/com/company/resource/default.csv"));
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
        assertEquals(13,manager.getCourseList().size());
        assertEquals(21,manager.getStudentList().size());
        assertEquals(15,manager.getStudentEnrolmentsList().size());
    }

    @Test
    void testPopulateData() throws IOException {
        manager.populateData("src/com/company/resource/default.csv");
        assertEquals("S1011535",manager.getStudentList().get(0).getId());
        assertEquals("COSC3321",manager.getCourseList().get(0).getId());
        assertEquals("S1011535",manager.getStudentEnrolmentsList().get(0).getStudentId());
        assertEquals("COSC3321",manager.getStudentEnrolmentsList().get(0).getCourseId());
        assertEquals("2021A",manager.getStudentEnrolmentsList().get(0).getSemester());
        assertEquals(4,manager.getCourseList().size());
        assertEquals(10,manager.getStudentList().size());
        assertEquals(15,manager.getStudentEnrolmentsList().size());

    }
    @Test
    void testPopulateDataInvalid() throws IOException {
        assertThrows(FileNotFoundException.class, () -> {
            manager.populateData("src/com/company/resource/default3.csv");
        });
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

        StudentEnrolment newEnrol = new StudentEnrolment(manager.getStudentEnrolmentsList().get(0));
        newEnrol.setStudent(student2);
        manager.update(newEnrol, manager.getStudentEnrolmentsList().get(0));

        assertEquals("s3836387", manager.getStudentEnrolmentsList().get(0).getStudentId());
        assertEquals("COSC2083", manager.getStudentEnrolmentsList().get(0).getCourseId());
        assertEquals("2020A", manager.getStudentEnrolmentsList().get(0).getSemester());
    }
    @Test
    void updateDuplication() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        manager.add(student,course,"2020B");

        StudentEnrolment newEnrol = new StudentEnrolment(manager.getStudentEnrolmentsList().get(1));
        newEnrol.setSemester("2020A");
        manager.update(newEnrol,manager.getStudentEnrolmentsList().get(0));

        assertEquals("s1817775", manager.getStudentEnrolmentsList().get(1).getStudentId());
        assertEquals("COSC2083", manager.getStudentEnrolmentsList().get(1).getCourseId());
        assertEquals("2020B", manager.getStudentEnrolmentsList().get(1).getSemester());
    }
    @Test
    void updateInvalidOldenrolment() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020B");

        StudentEnrolment newEnrol = new StudentEnrolment(manager.getStudentEnrolmentsList().get(0));
        newEnrol.setSemester("2020A");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            manager.update(newEnrol,newEnrol);
        });

    }

    @Test
    void delete() {
        // Use the enrolment from the list
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        manager.add(student,course,"2020B");
        manager.add(student,course,"2020C");
        for(StudentEnrolment e: manager.getStudentEnrolmentsList()){
            System.out.println(e.toString());
        }
        System.out.println("After delete");
        manager.delete(manager.getStudentEnrolmentsList().get(1));
        for(StudentEnrolment e: manager.getStudentEnrolmentsList()){
            System.out.println(e.toString());
        }
    }
    @Test
    void deleteInvalid() {
        // Create a new enrollment and use it in the method
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        manager.add(student,course,"2020B");
        manager.add(student,course,"2020C");
        for(StudentEnrolment e: manager.getStudentEnrolmentsList()){
            System.out.println(e.toString());
        }
        System.out.println("After delete");
        manager.delete(new StudentEnrolment(student,course,"2020A"));
        for(StudentEnrolment e: manager.getStudentEnrolmentsList()){
            System.out.println(e.toString());
        }
    }

    @Test
    void getOne() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        manager.add(student,course,"2020B");
        manager.add(student,course,"2020C");
        StudentEnrolment newEnrol = manager.getOne("s1817775","COSC2083","2020A");
        assertEquals("s1817775",newEnrol.getStudentId());
        assertEquals("COSC2083",newEnrol.getCourseId());
        assertEquals("2020A",newEnrol.getSemester());
    }
    @Test
    void getOneNoObjectFound() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        manager.add(student,course,"2020A");
        manager.add(student,course,"2020B");
        manager.add(student,course,"2020C");

        assertEquals(null,manager.getOne("s3836387","COSC2083","2020A"));
    }


    @Test
    void getStudentEnrolmentByStudentIdNSem() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        Course course2 =new Course("COSC2429","Introduction To Programming",12);
        manager.add(student,course,"2020A");
        manager.add(student,course2,"2020A");
        manager.add(student,course,"2020C");
        List<StudentEnrolment> newList = manager.getStudentEnrolmentByStudentIdNSem("s1817775","2020A");
        for(StudentEnrolment e: newList){
            System.out.println(e.toString());
        }
    }
    @Test
    void getStudentEnrolmentByStudentIdNSemEmpty() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        Course course2 =new Course("COSC2429","Introduction To Programming",12);
        manager.add(student,course,"2020A");
        manager.add(student,course2,"2020A");
        manager.add(student,course,"2020C");
        List<StudentEnrolment> newList = manager.getStudentEnrolmentByStudentIdNSem("s3836387","2020A");
        assertTrue(newList.isEmpty());
    }

    @Test
    void getStudentsEnrolmentByCourseNSem() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        Course course2 =new Course("COSC2429","Introduction To Programming",12);
        manager.add(student,course,"2020A");
        manager.add(student,course2,"2020A");
        manager.add(student2,course,"2020A");
        List<StudentEnrolment> newList = manager.getStudentsEnrolmentByCourseNSem("COSC2083","2020A");
        for(StudentEnrolment e: newList){
            System.out.println(e.toString());
        }
        System.out.println("All students in COSC2429 in semester 2020A");
        newList = manager.getStudentsEnrolmentByCourseNSem("COSC2429","2020A");
        for(StudentEnrolment e: newList){
            System.out.println(e.toString());
        }
    }
    @Test
    void getStudentsEnrolmentByCourseNSemEmpty() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Student student2 =new Student("s3836387","Ngo Quang Khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        Course course2 =new Course("COSC2429","Introduction To Programming",12);
        manager.add(student,course,"2020A");
        manager.add(student,course2,"2020A");
        manager.add(student2,course,"2020A");
        List<StudentEnrolment> newList = manager.getStudentsEnrolmentByCourseNSem("COSC2083","2020B");

        assertTrue(newList.isEmpty());
    }

    @Test
    void getStudentsEnrolmentBySem() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        Course course2 =new Course("COSC2429","Introduction To Programming",12);
        Course course3 =new Course("ISYS3414","Practical Database Concepts",12);
        manager.add(student,course,"2020A");
        manager.add(student,course2,"2020A");
        manager.add(student,course3,"2020A");
        manager.add(student,course3,"2020B");
        manager.add(student,course3,"2020C");
        List<StudentEnrolment> newList = manager.getStudentsEnrolmentBySem("2020A");
        for(StudentEnrolment e: newList){
            System.out.println(e.toString());
        }
        System.out.println("Courses in 2020B");
        newList = manager.getStudentsEnrolmentBySem("2020B");
        for(StudentEnrolment e: newList){
            System.out.println(e.toString());
        }
    }
    @Test
    void getStudentsEnrolmentBySemEmpty() {
        Student student =new Student("s1817775","Willard Muddimer", LocalDate.parse("1997-10-13"));
        Course course =new Course("COSC2083","Introduction to Information Technology",12);
        Course course2 =new Course("COSC2429","Introduction To Programming",12);
        Course course3 =new Course("ISYS3414","Practical Database Concepts",12);
        manager.add(student,course,"2020A");
        manager.add(student,course2,"2020A");
        manager.add(student,course3,"2020A");
        manager.add(student,course3,"2020B");
        manager.add(student,course3,"2020C");
        List<StudentEnrolment> newList = manager.getStudentsEnrolmentBySem("2021A");
        assertTrue(newList.isEmpty());
    }
}