package com.company.myClass;

import java.util.*;

public class StudentEnrolmentManager implements Manager {
    private static StudentEnrolmentManager single_instance = null;
    private List<StudentEnrolment> studentEnrolmentsList;
    private List<Student> studentList;
    private List<Course> courseList;
    private static final Scanner scanner = new Scanner(System.in);

    public StudentEnrolmentManager() {
        this.studentEnrolmentsList = new ArrayList<StudentEnrolment>();
        this.studentList = new ArrayList<Student>();
        this.courseList = new ArrayList<Course>();
    }
    public static StudentEnrolmentManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StudentEnrolmentManager();

        return single_instance;
    }

    // Get Student from student list
    public Student getStudent(String id){
        for (Student s: this.studentList ) {
            if (s.getId().equalsIgnoreCase(id)){
                return s;
            }
        }
        return null;
    }
    // Get Course from student list
    public Course getCourse(String id){
        for (Course c: this.courseList ) {
            if (c.getId().equalsIgnoreCase(id)){
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean add(Student student, Course course, String sem) {
        StudentEnrolment enrolment = new StudentEnrolment(student,course,sem);
        for (StudentEnrolment e: this.studentEnrolmentsList) {
            if (e.Equal(enrolment)){
                System.out.println("Enrolment already exist.");
                return false;
            }
        }
        studentEnrolmentsList.add(enrolment);
        return true;
    }

    @Override
    public boolean update(StudentEnrolment enrolment, int choice) {
        boolean isVal = false;
        switch (choice){
            case (1):
                String newId = "";
                while(!isVal){
                    System.out.println("Enter new student ID: ");
                    newId= scanner.next();
                    isVal = (getStudent(newId) != null);
                }
                enrolment.setStudent(getStudent(newId));
                break;
            case (2):
                break;
            case(3):
                break;
        }
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public StudentEnrolment getOne() {
        return null;
    }

    @Override
    public StudentEnrolment[] getAll() {
        return null;
    }
}
