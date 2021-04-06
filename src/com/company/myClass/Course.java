package com.company.myClass;

import java.time.LocalDate;

public class Course extends dataObject {
    private int numCredit;

    public Course() {
        super();
    }

    public Course(String id, String name, int numCredit) {
        super(id,name);
        this.numCredit = numCredit;
    }

    public int getNumCredit() {
        return numCredit;
    }

    public void setNumCredit(int numCredit) {
        this.numCredit = numCredit;
    }
}
