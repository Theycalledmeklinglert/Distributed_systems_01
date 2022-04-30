package de.fhws.fiw.fds.exam1.models;

import com.owlike.genson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Objects;

public class Project {
    private long id;
    private String name;
    private String description;
    private ArrayList<Student> students;
    private ArrayList<Supervisor> supervisors;
    private String semester;
    private String type;

    public Project() {

    }

    public Project(String name, String description, String semester, String type) {
        this.name = name;
        this.description = description;
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Supervisor> supervisors = new ArrayList<>();
        this.semester = semester;
        this.type = type;
    }

    // @JsonIgnore // Macht die Put-Request kaputt
    public void setId(long id) {
        this.id = id;
    }

   @JsonIgnore
   public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Supervisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(ArrayList<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addStudent(Student student) {
        if(!this.students.contains(student)) this.students.add(student);
    }

    public void addSupervisor(Supervisor supervisor) {
        if(!this.supervisors.contains(supervisor)) this.supervisors.add(supervisor);
    }

    public void removeStudent(Student student) {
       this.students.remove(student);
    }
    public void removeSupervisor(Supervisor supervisor) {
        this.supervisors.remove(supervisor);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", students=" + students +
                ", supervisors=" + supervisors +
                ", semester='" + semester + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
