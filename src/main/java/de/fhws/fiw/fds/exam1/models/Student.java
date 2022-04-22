package de.fhws.fiw.fds.exam1.models;

public class Student {
    private String firstname;
    private String lastname;
    private String course;
    private int semester;


    public Student() {
        super();
    }
    public Student(String firstname, String lastname, String course, int semester) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.course = course;
        this.semester = semester;
    }

    public String getFirstname() {
        return firstname;
    }

    private void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    private void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCourse() {
        return course;
    }

    private void setCourse(String course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    private void setSemester(int semester) {
        this.semester = semester;
    }
}
