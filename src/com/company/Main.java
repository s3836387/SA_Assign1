package com.company;

import com.company.myClass.*;

import java.io.IOException;
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
        boolean isrun = true;
        String id,sem, courseId;
        System.out.println("----------------");
        System.out.println("1.Choose your file");
        System.out.println("2.Use default file");
        System.out.println("3.Exit");
        System.out.println("----------------");
        int selection = Console.validateInt("Type in one of the number to choose the field you want to modify: ", 1, 3);
        switch (selection) {
            case 1:
                String filePath = Console.stringIn("Enter your file content root path (E.g: src/com/company/resource/default.csv): ");
                while (true) {
                    if (StudentEnrolmentManager.checkFile(filePath)) {
                        manager.populateData(filePath);
                        System.out.println("File loaded.");
                        break;
                    } else {
                        System.out.println("File not found");
                        filePath = Console.stringIn("Please re-enter (E.g: src/com/company/resource/default.csv): ");
                    }
                }
                manager.populateData(filePath);
                break;
            case 2:
                manager.populateData();
                break;
            default:
                break;
        }

        System.out.println("----------------");
        System.out.println("1.Add new enrolment.");
        System.out.println("2.Select & Modify/Delete enrolment.");
        System.out.println("3.Get all courses from student in 1 semester.");
        System.out.println("4.Get all students of 1 course in 1 semester.");
        System.out.println("5.Get all courses offered in 1 semester.");
        System.out.println("6.Exit");
        System.out.println("----------------");
        selection = Console.validateInt("Type in one of the number to choose the field you want to modify: ", 1, 6);
        switch (selection) {
            case 1:
                // --------Begin add --------
                System.out.format("%15s%20s%15s\n", "Student ID", "Student name", "DOB");
                for (Student s : manager.getStudentList()) {
                    System.out.format("%15s%20s%15s\n",  s.getId(), s.getName(), s.getBirthdate());
                }
                id = studentInput("Please enter student id: ");
                Student s = manager.getStudent(id);

                //Get course input
                courseId = courseInput("Course id: ");
                Course c = manager.getCourse(courseId);
                sem = Console.validateSem("Semester: ");
                if (manager.add(s, c, sem)) {
                    System.out.println("Enrolment successfully added.");
                } else {
                    System.out.println("An error occur");
                }

                // --------End add --------
                break;
            case 2:
                // --------Begin Select --------
                id = studentInput("Please enter a student id: ");
                sem = Console.validateSem("Please enter the semester: ");
                List<StudentEnrolment> StudentCourses = manager.getStudentEnrolmentBySem(id,sem);
                while (true) {
                    if (!StudentCourses.isEmpty()) {
                        // Print out all courses Student enrolled
                        System.out.format("%10s%15s%20s%15s%12s%35s%10s%10s\n", "Index", "Student ID", "Student name", "DOB", "Course ID", "Course name", "Credit", "Semester");
                        for (StudentEnrolment e : StudentCourses) {
                            System.out.format("%10d%15s%20s%15s%12s%35s%10s%10s\n", StudentCourses.indexOf(e), e.getStudentId(), e.getStudent().getName(), e.getStudent().getBirthdate(), e.getCourseId(), e.getCourse().getName(), e.getCourse().getNumCredit(), e.getSemester());
                        }
                        break;
                    } else {
                        System.out.println("No courses found! Please try a different student or semester.");
                        id = studentInput("Enter student id: ");
                        sem = Console.validateSem("Enter the semester: ");
                        StudentCourses = manager.getStudentEnrolmentBySem(id,sem);
                    }
                }

                selection = Console.validateInt("Type in one of the number to choose the record you want to modify: ", 0, StudentCourses.size() - 1);
                StudentEnrolment enrolment = StudentCourses.get(selection);
                // --------End Select --------
                // -------- Modify menu -------------------
                selection = Console.validateInt("1.UPDATE 2.DELETE 3.DONE: ", 1, 3);
                switch (selection) {
                    case 1:
                        //--------Begin update --------
                        do {
                            System.out.println("----------------");
                            System.out.println("1.Student");
                            System.out.println("2.Course");
                            System.out.println("3.Semester");
                            System.out.println("4.Save & Exit");
                            System.out.println("----------------");
                            System.out.println("NOTE: Remember to \"Save & Exit\" to update your file.");

                            selection = Console.validateInt("Type in one of the number to choose the field you want to modify: ", 1, 4);
                            String temp;
                            switch (selection) {
                                case 1 -> {
                                    id = studentInput("Enter new student id: ");
                                    enrolment.setStudent(manager.getStudent(id));
                                }
                                case 2 -> {
                                    courseId = courseInput("Enter new course id: ");
                                    enrolment.setCourse(manager.getCourse(courseId));
                                }
                                case 3 -> {
                                    sem = Console.validateSem("Enter new semester: ");
                                    enrolment.setSemester(sem);
                                }
                                default -> {
                                }
                            }
                        } while (selection != 4);
                        manager.update(enrolment);
                        break;
                    //--------End update --------
                    case 2:
                        selection = Console.validateInt("Are your sure? 1/Y 2/N", 1, 2);
                        manager.delete(enrolment);
                        break;
                    default:
                        break;
                }
                //-------- End Modify menu ----------------
                break;
            case 3:
                id = studentInput("Please enter a student id: ");
                sem = Console.validateSem("Please enter the semester: ");
                List<StudentEnrolment> Studentcourses = manager.getStudentEnrolmentBySem(id,sem);
                while (true) {
                    if (!Studentcourses.isEmpty()) {
                        // Print out all courses Student enrolled
                        System.out.format("%10s%15s%20s%15s%12s%35s%10s%10s\n", "Index", "Student ID", "Student name", "DOB", "Course ID", "Course name", "Credit", "Semester");
                        for (StudentEnrolment e : Studentcourses) {
                            System.out.format("%10d%15s%20s%15s%12s%35s%10s%10s\n", Studentcourses.indexOf(e), e.getStudentId(), e.getStudent().getName(), e.getStudent().getBirthdate(), e.getCourseId(), e.getCourse().getName(), e.getCourse().getNumCredit(), e.getSemester());
                        }
                        break;
                    } else {
                        System.out.println("No courses found! Please try a different student or semester.");
                        id = studentInput("Enter student id: ");
                        sem = Console.validateSem("Enter the semester: ");
                        Studentcourses = manager.getStudentEnrolmentBySem(id,sem);
                    }
                }
                selection = Console.validateInt("Do you want to save a .csv file? 1:Yes  2:No", 1, 2);
                if (selection == 1){
                    String studentCoursesFilepath = "src/com/company/"+id + "_courses_"+sem+".csv";
                    if (StudentEnrolmentManager.createFile(studentCoursesFilepath)){
                        StudentEnrolmentManager.writeFile(Studentcourses,studentCoursesFilepath);
                    }
                }
                break;

            default:
                break;
        }



//        manager.populateData();
//        manager.add(manager.getStudentList().get(0), manager.getCourseList().get(0), "2020A");
//        manager.add(manager.getStudentList().get(2), manager.getCourseList().get(1), "2020A");
//        manager.add(manager.getStudentList().get(3), manager.getCourseList().get(0), "2020A");
//        manager.add(manager.getStudentList().get(8), manager.getCourseList().get(4), "2020A");
//        manager.add(manager.getStudentList().get(10), manager.getCourseList().get(5), "2020B");
//        manager.add(manager.getStudentList().get(10), manager.getCourseList().get(8), "2020A");
//        manager.add(manager.getStudentList().get(10), manager.getCourseList().get(5), "2020A");
    }


}
