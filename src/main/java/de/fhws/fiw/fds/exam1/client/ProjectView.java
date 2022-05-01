package de.fhws.fiw.fds.exam1.client;

import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.fiw.fds.exam1.models.Project;

import java.util.ArrayList;

public class ProjectView {
    private long id;
    private String name;
    private String description;
    private ArrayList<StudentView> students;
    private ArrayList<SupervisorView> supervisors;
    private String semester;
    private String type;

    public ProjectView() {

    }

    public ProjectView(String name, String description, String semester, String type) {
        this.name = name;
        this.description = description;
        ArrayList<StudentView> students = new ArrayList<>();
        ArrayList<SupervisorView> supervisors = new ArrayList<>();
        this.semester = semester;
        this.type = type;
    }

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

    public ArrayList<StudentView> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<StudentView> students) {
        this.students = students;
    }

    public ArrayList<SupervisorView> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(ArrayList<SupervisorView> supervisors) {
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

    public void addStudent(StudentView student) {
        if(!this.students.contains(student)) this.students.add(student);
    }

    public void addSupervisor(SupervisorView supervisor) {
        if(!this.supervisors.contains(supervisor)) this.supervisors.add(supervisor);
    }

    public void removeStudent(StudentView student) {
       this.students.remove(student);
    }
    public void removeSupervisor(SupervisorView supervisor) {
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
