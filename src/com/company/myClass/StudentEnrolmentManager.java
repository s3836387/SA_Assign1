package com.company.myClass;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class StudentEnrolmentManager implements Manager {
    private static StudentEnrolmentManager single_instance = null;
    private List<StudentEnrolment> studentEnrolmentsList;
    private List<Student> studentList;
    private List<Course> courseList;
    private static final Scanner scanner = new Scanner(System.in);

    public StudentEnrolmentManager() {
        this.studentEnrolmentsList = new ArrayList<StudentEnrolment>();
        this.studentList = new ArrayList<Student>();
        this.courseList = new ArrayList<Course>();
    }
    public static StudentEnrolmentManager getInstance()
    {
        if (single_instance == null)
            single_instance = new StudentEnrolmentManager();

        return single_instance;
    }
    // ------- Initialize file --------
    public static boolean initFile(String filePath) throws IOException{
        try {
            File myObj = new File(filePath);
            return myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public void populateData() throws IOException {
        // Populate student list
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/company/resource/students.csv"));
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            this.studentList.add(new Student(data[0],data[1],LocalDate.parse(data[2])));
        }
        csvReader.close();
        // Pupulate course list
        csvReader = new BufferedReader(new FileReader("src/com/company/resource/courses.csv"));
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            this.courseList.add(new Course(data[0],data[1],Integer.parseInt(data[2])));
        }
        csvReader.close();
    }

    // Get Student from student list
    public Student getStudent(String id){
        for (Student s: this.studentList ) {
            if (s.getId().equalsIgnoreCase(id)){
                return s;
            }
        }
        return null;
    }

    // Get Course from Course list
    public Course getCourse(String id){
        for (Course c: this.courseList ) {
            if (c.getId().equalsIgnoreCase(id)){
                return c;
            }
        }
        return null;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public List<StudentEnrolment> getStudentEnrolmentsList() {
        return studentEnrolmentsList;
    }

    //-------------------CRUD--------------------------
    @Override
    public boolean add(Student student, Course course, String sem) {
        StudentEnrolment enrolment = new StudentEnrolment(student,course,sem);
        for (StudentEnrolment e: this.studentEnrolmentsList) {
            if (e.Equal(enrolment)){
                System.out.println("Enrolment already exist.");
                return false;
            }
        }
        studentEnrolmentsList.add(enrolment);
        return true;
    }
    @Override
    public boolean update(StudentEnrolment enrolment, int choice) {
        boolean isVal = false;
        switch (choice){
            case (1):
                String newId = "";
                while(!isVal){
                    System.out.println("Enter new student ID: ");
                    newId= scanner.next();
                    isVal = (getStudent(newId) != null);
                }
                enrolment.setStudent(getStudent(newId));
                break;
            case (2):
                break;
            case(3):
                break;
        }
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public StudentEnrolment getOne(int index) {
        return studentEnrolmentsList.get(index);
    }

    @Override
    public void getAll() {
        System.out.format("%10s%20s%15s%15s\n","ID","Student ID", "Course ID","Semester");
        for (StudentEnrolment enrolment: studentEnrolmentsList){
            System.out.format("%10d%20s%15s%15s\n",studentEnrolmentsList.indexOf(enrolment),enrolment.getStudentId(), enrolment.getCourseId(),enrolment.getSemester());
        }

    }
}
