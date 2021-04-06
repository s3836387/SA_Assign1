package com.company.myClass;

import java.util.*;

public class StudentEnrolmentManager implements Manager {
    private static StudentEnrolmentManager single_instance = null;
    private List<StudentEnrolment> studentEnrolmentsList;

    public StudentEnrolmentManager() {
        this.studentEnrolmentsList = new ArrayList<StudentEnrolment>();
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
