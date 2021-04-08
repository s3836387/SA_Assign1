package com.company.myClass;

import java.util.List;

public interface Manager {
    boolean add(Student student, Course course, String sem);
    void update(StudentEnrolment newEnrolment);
    void delete(StudentEnrolment newEnrolment);
    StudentEnrolment getOne(int index);
    void getAll();
}
