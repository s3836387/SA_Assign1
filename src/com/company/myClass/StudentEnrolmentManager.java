package com.company.myClass;

import java.util.*;

public class StudentEnrolmentManager implements Manager {
    private static StudentEnrolmentManager single_instance = null;
    private List<StudentEnrolment> studentEnrolmentsList;
    private List<Student> studentList;
    private List<Course> courseList;

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

    @Override
    public boolean add() {

        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public Object getOne() {
        return null;
    }

    @Override
    public Object getAll() {
        return null;
    }
}
