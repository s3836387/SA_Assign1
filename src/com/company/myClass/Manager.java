package com.company.myClass;

public interface Manager {
    boolean add(Student student, Course course, String sem);
    boolean update(StudentEnrolment enrolment, int choice);
    boolean delete();
    StudentEnrolment getOne();
    StudentEnrolment[] getAll();
}
