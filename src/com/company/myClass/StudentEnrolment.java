package com.company.myClass;

import com.company.myClass.Course;
import com.company.myClass.Student;

public class StudentEnrolment implements Comparable<StudentEnrolment> {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(StudentEnrolment enrolment) {
        this.student = new Student(enrolment.getStudentId(),enrolment.student.getName(),enrolment.student.getBirthdate());
        this.course = new Course(enrolment.getCourseId(),enrolment.course.getName(),enrolment.course.getNumCredit());
        this.semester = enrolment.semester;
    }
    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStudentId(){
        return this.student.getId();
    }
    public String getCourseId(){
        return this.course.getId();
    }
    public boolean Equal(StudentEnrolment enrolment){
        if((enrolment.getCourseId().equalsIgnoreCase(this.getCourseId()))
                &&(enrolment.getStudentId().equalsIgnoreCase(this.getStudentId()))
                &&(this.semester.equalsIgnoreCase(enrolment.getSemester()))){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  this.student.getId()+"," +this.student.getName()+"," +this.student.getBirthdate()+
                "," + this.course.getId() +"," + this.course.getName() +"," + this.course.getNumCredit() +
                "," + this.semester;
    }

    @Override
    public int compareTo(StudentEnrolment o) {
        return this.getStudentId().compareToIgnoreCase(o.getStudentId());
    }
}
