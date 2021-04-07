package com.company.myClass;

import java.time.LocalDate;

public class Course extends dataObject {
    private int numCredit;

    public Course(String id, String name) {
        super(id, name);
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

    @Override
    public String toString() {
        return  this.getId() + ","+ this.getName()+
                "," + numCredit ;
    }
}
