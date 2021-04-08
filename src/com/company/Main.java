package com.company;

import com.company.myClass.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static String studentInput(String prompt) {
        StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
        String id = Console.validateStudentId(prompt);
        manager.getStudentList().get(0).toString();
        while (true) {
            if (manager.getStudent(id) != null) {
                break;
            } else {
                id = Console.validateStudentId("Student id does not exist. Please re-enter.");
            }
        }
        return id;
    }
    public static String courseInput(String prompt) {
        StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
        String courseId = Console.validateCourseId(prompt);
        while (true) {
            if (manager.getCourse(courseId) != null) {
                break;
            } else {
                courseId = Console.validateCourseId("Course id does not exist! Please re-enter. ");
            }
        }
        return courseId;
    }

    public static void main(String[] args) throws IOException {
        StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
        manager.populateData();
        manager.add(manager.getStudentList().get(0), manager.getCourseList().get(0), "2020A");
        manager.add(manager.getStudentList().get(2), manager.getCourseList().get(1), "2020A");
        manager.add(manager.getStudentList().get(3), manager.getCourseList().get(0), "2020A");
        manager.add(manager.getStudentList().get(8), manager.getCourseList().get(4), "2020A");
        manager.add(manager.getStudentList().get(10), manager.getCourseList().get(5), "2020B");
        manager.add(manager.getStudentList().get(10), manager.getCourseList().get(8), "2020A");
        manager.add(manager.getStudentList().get(10), manager.getCourseList().get(5), "2020A");
        // --------Begin add --------

//        String id = studentInput("Please enter student id: ");
//        Student s = manager.getStudent(id);
//
//        //Get course input
//        String courseId = courseInput("Course id: ");
//        Course c = manager.getCourse(courseId);

//        String sem = Console.validateSem("Semester: ");
//        if(manager.add(s,c,sem)){
//            System.out.println("Enrolment successfully added.");
//        }else{
//            System.out.println("An error occur");
//        }

        // --------End add --------
        // --------Begin update --------
        String studentid = studentInput("Please enter a student id: ");
        List<StudentEnrolment> StudentCourses = manager.getEnrolmentbyStudent(studentid);
        while(true){
            if(!StudentCourses.isEmpty()){
                // Print out all courses Student enrolled
                System.out.format("%10s%15s%20s%15s%12s%35s%10s%10s\n","Index","Student ID","Student name","DOB", "Course ID","Course name","Credit","Semester");
                for (StudentEnrolment e: StudentCourses) {
                    System.out.format("%10d%15s%20s%15s%12s%35s%10s%10s\n",StudentCourses.indexOf(e) , e.getStudentId(), e.getStudent().getName(),e.getStudent().getBirthdate() ,e.getCourseId(), e.getCourse().getName(), e.getCourse().getNumCredit(), e.getSemester());
                }
                break;
            }else{
                studentid = studentInput("No courses found! Please choose a different student: ");
                StudentCourses = manager.getEnrolmentbyStudent(studentid);
            }
        }
        
        int selection = Console.validateInt("Type in one of the number to choose the field you want to modify: ", 0, StudentCourses.size()-1);
        StudentEnrolment enrolment = StudentCourses.get(selection);
//        //int selection = Console.validateInt("1.UPDATE 2.DELETE 3.CANCEL: ", 1, 3);
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
//                    String id = studentInput("Enter new student id: ");
//                    enrolment.setStudent(manager.getStudent(id));
//                }
//                case 2 -> {
//                    String courseId = courseInput("Enter new course id: ");
//                    enrolment.setCourse(manager.getCourse(courseId));
//                }
//                case 3 -> {
//                    String sem = Console.validateSem("Enter new semester: ");
//                    enrolment.setSemester(sem);
//                }
//                default -> {
//                }
//            }
//        } while (selection != 4);
//        manager.update(enrolment);
//        manager.getAll();
        //--------End update --------
    }


}
