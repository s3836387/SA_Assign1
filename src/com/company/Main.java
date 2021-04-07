package com.company;

import com.company.myClass.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public void Add(){

    }
    public static void main(String[] args) throws IOException {
        StudentEnrolmentManager manager = new StudentEnrolmentManager();
        manager.populateData();
        manager.add(manager.getStudentList().get(0), manager.getCourseList().get(0),"2020A" );
        manager.add(manager.getStudentList().get(2), manager.getCourseList().get(1),"2020A" );
        manager.add(manager.getStudentList().get(3), manager.getCourseList().get(0),"2020A" );
        manager.add(manager.getStudentList().get(8), manager.getCourseList().get(4),"2020A" );
        manager.add(manager.getStudentList().get(5), manager.getCourseList().get(5),"2020B" );
        // --------Begin add --------
//        System.out.print("Student id: ");
//        String id = Console.validateStudentId(manager.getStudentList());
//        while(true){
//            if (manager.getStudent(id) != null){
//                break;
//            }else{
//                id = Console.validateStudentId(manager.getStudentList());
//            }
//        }
//        Student s = manager.getStudent(id);
//
//        //Get course input
//        System.out.print("Course id: ");
//        String courseId = Console.validateCourseId();
//        while(true){
//            if (manager.getCourse(courseId) != null){
//                break;
//            }else{
//                System.out.print("Course id does not exist! Please re-enter. ");
//                courseId = Console.validateCourseId();
//            }
//        }
//        Course c = manager.getCourse(courseId);
//        System.out.print("Semester: ");
//        String sem = Console.validateSem();
//        if(manager.add(s,c,sem)){
//            System.out.println("Enrolment successfully added.");
//        }else{
//            System.out.println("An error occur");
//        }
//
        // --------End add --------
        manager.getAll();
        int selectEnrol = Console.validateInt("Enter index number to choose: ", 0, manager.getStudentEnrolmentsList().size()-1);
        StudentEnrolment en = manager.getOne(selectEnrol);

    }
}
