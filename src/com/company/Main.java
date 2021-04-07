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
        
//        String id = Console.validateStudentId("Student id: ");
//        while(true){
//            if (manager.getStudent(id) != null){
//                break;
//            }else{
//                id = Console.validateStudentId("Student id does not exist. Please re-enter: ");
//            }
//        }
//        Student s = manager.getStudent(id);
//
//        //Get course input
//        String courseId = Console.validateCourseId("Course id: ");
//        while(true){
//            if (manager.getCourse(courseId) != null){
//                break;
//            }else{
//                courseId = Console.validateCourseId("Course id does not exist! Please re-enter. ");
//            }
//        }
//        Course c = manager.getCourse(courseId);
//        String sem = Console.validateSem("Semester: ");
//        if(manager.add(s,c,sem)){
//            System.out.println("Enrolment successfully added.");
//        }else{
//            System.out.println("An error occur");
//        }

        // --------End add --------
        // --------Begin update --------
//        manager.getAll();
//        int index = Console.validateInt("Enter index number to choose: ", 0, manager.getStudentEnrolmentsList().size()-1);
//        StudentEnrolment enrolment = manager.getOne(index);
//
//        //int selection = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ", 1, 3);
//        int selection;
//        do {
//            System.out.println("----------------");
//            System.out.println("1.Student");
//            System.out.println("2.Course");
//            System.out.println("3.Semester");
//            System.out.println("4.Save & Exit");
//            System.out.println("----------------");
//            System.out.println("NOTE: Remember to \"Save & Exit\" to update your file.");
//
//            selection = Console.validateInt("Type in one of the number to choose the field you want to modify: ", 1, 4);
//            String temp;
//            switch (selection) {
//                case 1 -> {
//                     id = Console.validateStudentId("Enter new student id: ");
//                    while(true){
//                        if (manager.getStudent(id) != null){
//                            break;
//                        }else{
//                            id = Console.validateStudentId("Student id does not exist. Please re-enter.");
//                        }
//                    }
//                    enrolment.setStudent(manager.getStudent(id));
//                }
//                case 2 -> {
//                    courseId = Console.validateCourseId("Enter new course id: ");
//                    while(true){
//                        if (manager.getCourse(courseId) != null){
//                            break;
//                        }else{
//                            courseId = Console.validateCourseId("Course id does not exist! Please re-enter. ");
//                        }
//                    }
//                    enrolment.setCourse(manager.getCourse(courseId));
//                }
//                case 3 -> {
//                    sem = Console.validateSem("Enter new semester: ");
//                    enrolment.setSemester(sem);
//                }
//                default -> {
//                }
//            }
//        } while (selection != 4);
//        manager.update(enrolment,index);
//        manager.getAll();
        //--------End update --------
    }

}
