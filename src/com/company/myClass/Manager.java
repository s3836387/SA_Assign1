package com.company.myClass;

public interface Manager {
    boolean add(Student student, Course course, String sem);
    void update(StudentEnrolment newEnrolment, StudentEnrolment oldEnrolment);
    void delete(StudentEnrolment newEnrolment);
    StudentEnrolment getOne(String studentId, String courseId, String sem);
    void getAll();
}
