package com.company.myClass;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentEnrolmentManager implements Manager {
    private static StudentEnrolmentManager single_instance;
    private List<StudentEnrolment> studentEnrolmentsList;
    private List<Student> studentList;
    private List<Course> courseList;


    private StudentEnrolmentManager() {
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
    // ------- File Process Methods --------
    public static boolean createFile(String filePath) throws IOException{
        try {
            File myObj = new File(filePath);
            return myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public static void writeFile(List<StudentEnrolment> list, String filePath) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(filePath, false));
        for (StudentEnrolment row : list) {
            pw.print(row.toString());
            pw.println();
        }
        pw.close();
    }

    public static boolean checkFile(String filePath){
        File myObj = new File(filePath);
        return myObj.exists();
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
        // Populate course list
        csvReader = new BufferedReader(new FileReader("src/com/company/resource/courses.csv"));
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            this.courseList.add(new Course(data[0],data[1],Integer.parseInt(data[2])));
        }
        csvReader.close();
        // Populate course list
        csvReader = new BufferedReader(new FileReader("src/com/company/resource/default.csv"));
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            this.studentEnrolmentsList.add(new StudentEnrolment((new Student(data[0],data[1],LocalDate.parse(data[2]))),(new Course(data[3],data[4],Integer.parseInt(data[5]))),data[6]));
        }
        csvReader.close();
    }

    public void populateData(String filePath) throws IOException {
        // Populate student list
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            this.studentEnrolmentsList.add(new StudentEnrolment((new Student(data[0],data[1],LocalDate.parse(data[2]))),(new Course(data[3],data[4],Integer.parseInt(data[5]))),data[6]));
        }
        csvReader.close();
        Collections.sort(this.studentEnrolmentsList);
        for (StudentEnrolment e: this.studentEnrolmentsList) {
            if(this.getStudent(e.getStudentId()) == null){
                this.studentList.add(e.getStudent());
            }
            if(this.getCourse(e.getCourseId()) == null){
                this.courseList.add(e.getCourse());
            }
        }
    }

    // ------- File Process Methods --------

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
    public void update(StudentEnrolment newEnrolment) {
    //        for (StudentEnrolment e: this.studentEnrolmentsList) {
    //            if (e.equals(newEnrolment)){
    //                studentEnrolmentsList.set(studentEnrolmentsList.indexOf(e),newEnrolment);
    //            }
    //        }
        studentEnrolmentsList.set(studentEnrolmentsList.indexOf(newEnrolment),newEnrolment);
    }

    @Override
    public void delete(StudentEnrolment newEnrolment) {
        studentEnrolmentsList.remove(newEnrolment);
    }

    @Override
    public StudentEnrolment getOne(int index) {
        return studentEnrolmentsList.get(index);
    }

    @Override
    public void getAll() {
        System.out.format("%10s%15s%20s%15s%12s%35s%10s%10s\n","Index","Student ID","Student name","DOB", "Course ID","Course name","Credit","Semester");
        for (StudentEnrolment enrolment: studentEnrolmentsList){
            System.out.format("%10s%15s%20s%15s%12s%35s%10s%10s\n",studentEnrolmentsList.indexOf(enrolment),enrolment.getStudentId(),enrolment.getStudent().getName(),enrolment.getStudent().getBirthdate(), enrolment.getCourseId(),enrolment.getCourse().getName(),enrolment.getCourse().getNumCredit(),enrolment.getSemester());
        }

    }

    public List<StudentEnrolment> getStudentEnrolmentBySem(String id, String sem){
        List<StudentEnrolment> newList = new ArrayList<>();
        for (StudentEnrolment en: this.studentEnrolmentsList) {
            if((en.getStudentId().equalsIgnoreCase(id))&&(en.getSemester().equalsIgnoreCase(sem))){
                newList.add(en);
            }
        }
        return newList;
    }
}
