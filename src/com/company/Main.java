package com.company;

import com.company.myClass.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static void printAllStudent(){
        StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
        System.out.format("%15s%20s%15s\n", "Student ID", "Student name", "DOB");
        for (Student s : manager.getStudentList()) {
            System.out.format("%15s%20s%15s\n", s.getId(), s.getName(), s.getBirthdate());
        }
    }
    public static void printAllCourse(){
        StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
        System.out.format("%15s%35s%15s\n", "Course ID", "Course name", "Credit");
        for (Course c : manager.getCourseList()) {
            System.out.format("%15s%35s%15d\n", c.getId(), c.getName(), c.getNumCredit());
        }
    }

    public static void main(String[] args) throws IOException {
        StudentEnrolmentManager manager = StudentEnrolmentManager.getInstance();
        boolean isrun = true;
        String id, sem, courseId;
        String filePath = "src/com/company/resource/default.csv";

        System.out.println("---------WELCOME TO STUDENT ENROLMENT MANAGEMENT SYSTEM-------");
        System.out.println("1.Choose your file");
        System.out.println("2.Use default file");
        System.out.println("3.Exit");
        System.out.println("--------------------------------------------------------------");
        int selection = Console.validateInt("Please choose an option: ", 1, 3);
        switch (selection) {
            case 1:
                filePath = Console.stringIn("Enter your file content root path (E.g: src/com/company/resource/default.csv): ");
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
                break;
            case 2:
                manager.populateData();
                break;
            default:
                System.exit(0);
                break;
        }
        do {
            System.out.println("----------------");
            System.out.println("1.Add new enrolment.");
            System.out.println("2.Select & Modify/Delete enrolment.");
            System.out.println("3.Get all courses from a student in 1 semester.");
            System.out.println("4.Get all students of 1 course in 1 semester.");
            System.out.println("5.Get all courses offered in 1 semester.");
            System.out.println("6.Print All.");
            System.out.println("7.Exit");
            System.out.println("----------------");
            selection = Console.validateInt("Type in one of the number to choose what you want to do: ", 1, 7);
            switch (selection) {
                case 1:
                    // --------Begin add --------
                    printAllStudent();
                    id = studentInput("Please enter student id: ");
                    Student s = manager.getStudent(id);

                    //Get course input
                    printAllCourse();
                    courseId = courseInput("Course id: ");
                    Course c = manager.getCourse(courseId);
                    sem = Console.validateSem("Semester: ");
                    if (manager.add(s, c, sem)) {
                        System.out.println("Enrolment successfully added.");
                        StudentEnrolmentManager.writeFile(manager.getStudentEnrolmentsList(), filePath);
                    } else {
                        System.out.println("An error occur");
                    }

                    // --------End add --------
                    break;
                case 2:
                    // --------Begin Select --------
                    manager.getAll();
                    id = studentInput("Please enter a student id: ");
                    sem = Console.validateSem("Please enter the semester: ");
                    List<StudentEnrolment> StudentCourses = manager.getStudentEnrolmentByStudentIdNSem(id, sem);
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
                            StudentCourses = manager.getStudentEnrolmentByStudentIdNSem(id, sem);
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
                    StudentEnrolmentManager.writeFile(manager.getStudentEnrolmentsList(), filePath);
                    //-------- End Modify menu ----------------
                    break;
                case 3:
                    printAllStudent();
                    id = studentInput("Please enter a student id: ");
                    sem = Console.validateSem("Please enter the semester: ");
                    List<StudentEnrolment> Studentcourses = manager.getStudentEnrolmentByStudentIdNSem(id, sem);
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
                            Studentcourses = manager.getStudentEnrolmentByStudentIdNSem(id, sem);
                        }
                    }
                    selection = Console.validateInt("Do you want to save a .csv file? 1:Yes  2:No ", 1, 2);
                    if (selection == 1) {
                        String studentCoursesFilepath = "src/com/company/" + id + "_courses_" + sem + ".csv";
                        if (StudentEnrolmentManager.createFile(studentCoursesFilepath)) {
                            StudentEnrolmentManager.writeFile(Studentcourses, studentCoursesFilepath);
                        }
                    }
                    break;
                case 4:
                    printAllCourse();
                    courseId = courseInput("Please enter a course id: ");
                    sem = Console.validateSem("Please enter the semester: ");
                    List<StudentEnrolment> StudentsinCourse = manager.getStudentsEnrolmentByCourseNSem(courseId, sem);
                    while (true) {
                        if (!StudentsinCourse.isEmpty()) {
                            // Print out all Students enrolled in course
                            System.out.println("List of students in course " + StudentsinCourse.get(0).getCourseId() + " in " + StudentsinCourse.get(0).getSemester());
                            System.out.format("%10s%15s%20s%15s\n", "Index", "Student ID", "Student name", "DOB");
                            for (StudentEnrolment e : StudentsinCourse) {
                                System.out.format("%10s%15s%20s%15s\n", StudentsinCourse.indexOf(e), e.getStudentId(), e.getStudent().getName(), e.getStudent().getBirthdate());
                            }
                            break;
                        } else {
                            System.out.println("No courses found! Please try a different course or semester.");
                            courseId = courseInput("Enter a course id: ");
                            sem = Console.validateSem("Enter the semester: ");
                            StudentsinCourse = manager.getStudentsEnrolmentByCourseNSem(courseId, sem);
                        }
                    }
                    selection = Console.validateInt("Do you want to save a .csv file? 1:Yes  2:No ", 1, 2);
                    if (selection == 1) {
                        String studentCoursesFilepath = "src/com/company/" + "students_in_" + courseId + "_" + sem + ".csv";
                        if (StudentEnrolmentManager.createFile(studentCoursesFilepath)) {
                            PrintWriter pw = new PrintWriter(new FileWriter(studentCoursesFilepath, false));
                            for (StudentEnrolment e : StudentsinCourse) {
                                pw.print(e.getStudentId() + "," + e.getStudent().getName() + "," + e.getStudent().getBirthdate());
                                pw.println();
                            }
                            pw.close();
                        }
                    }
                    break;
                case 5:
                    sem = Console.validateSem("Please enter the semester: ");
                    List<StudentEnrolment> CoursesinSem = manager.getStudentsEnrolmentBySem(sem);
                    while (true) {
                        if (!CoursesinSem.isEmpty()) {
                            // Print out all courses Student enrolled
                            System.out.format("%12s%35s%10s%10s\n", "Course ID", "Course name", "Credit", "Semester");
                            for (StudentEnrolment e : CoursesinSem) {
                                System.out.format("%12s%35s%10s%10s\n", e.getCourseId(), e.getCourse().getName(), e.getCourse().getNumCredit(), e.getSemester());
                            }
                            break;
                        } else {
                            System.out.println("No courses found! Please try a different semester.");
                            sem = Console.validateSem("Enter the semester: ");
                            CoursesinSem = manager.getStudentsEnrolmentBySem(sem);
                        }
                    }
                    selection = Console.validateInt("Do you want to save a .csv file? 1:Yes  2:No ", 1, 2);
                    if (selection == 1) {
                        String CoursesSem = "src/com/company/" + "courses_offer_in_" + sem + ".csv";
                        if (StudentEnrolmentManager.createFile(CoursesSem)) {
                            PrintWriter pw = new PrintWriter(new FileWriter(CoursesSem, false));
                            for (StudentEnrolment e : CoursesinSem) {
                                pw.print(e.getCourseId() + "," + e.getCourse().getName() + "," + e.getCourse().getNumCredit() + "," + e.getSemester());
                                pw.println();
                            }
                            pw.close();
                        }
                    }
                    break;
                case 6:
                    manager.getAll();
                    break;
                default:
                    break;
            }

        } while (selection != 7);

    }


}
