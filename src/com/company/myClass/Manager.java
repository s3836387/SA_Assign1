package com.company.myClass;

import java.util.List;

public interface Manager {
    boolean add(Student student, Course course, String sem);
    boolean update(StudentEnrolment enrolment, int choice);
    boolean delete();
    StudentEnrolment getOne(int index);
    void getAll();
}
