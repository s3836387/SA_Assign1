package com.company;

import com.company.myClass.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        StudentEnrolmentManager manager = new StudentEnrolmentManager();
        manager.populateData();
        System.out.println(manager.getCourseList().get(4).toString());
    }
}
