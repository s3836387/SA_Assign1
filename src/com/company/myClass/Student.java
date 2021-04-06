package com.company.myClass;
import java.time.LocalDate;

public class Student extends dataObject {
    private LocalDate birthdate;

    public Student() {
        super();
    }

    public Student(String id, String name, LocalDate birthdate) {
        super(id,name);
        this.birthdate = birthdate;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
