package com.company;

import com.company.myClass.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Student student =new Student("s3836387","Ngo Quang khai", LocalDate.parse("2000-12-02"));
        Course course =new Course("COSC2440","Software Architecture Design and Implementation",12);
        StudentEnrolmentManager manager = new StudentEnrolmentManager();


    }
}
