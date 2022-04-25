package de.fhws.fiw.fds.exam1.models;

public class ValidityCheck {

    public static boolean checkProject(Project project) {
        if(project.getName() == null || project.getDescription() == null || project.getSemester() == null || project.getType() == null) {
            return false;
        }
        // Project name and description may contain all kinds of letters and signs therefore no check necessary
        // TODO: Crash in getStudents() / getSupervisor if List is not inialized??? --> Alternativ: Project standardmaessig je eine new LinkedList() fuer Studenten und Supervisor geben und unten nach siz() == 0 anstatt auf null checken
        if(project.getSemester().matches("[0-9]{4}[a-zA-Z]{2}")) {
                if(project.getStudents() == null || project.getStudents().stream().allMatch(sdt -> checkStudent(sdt))) {
                    if (project.getSupervisors() == null || project.getSupervisors().stream().allMatch(spv -> checkSupervisor(spv))) {
                        return true;
                    }
                }
        }
        return false;
    }

    // The if Loops are nested for better readability
    public static boolean checkStudent(Student student) {
        if(student.getFirstname() == null || student.getLastname() == null || student.getCourse() == null) {    // Value of uninitialized int is 0, therefore no null check is necessary
            return false;
        }
        if(student.getFirstname().chars().allMatch(Character::isLetter)) {
            if(student.getLastname().chars().allMatch(Character::isLetter)) {
                if(student.getCourse().chars().allMatch(Character::isLetter)) {
                    if(student.getSemester() > 0 && student.getSemester() < 8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkSupervisor(Supervisor supervisor) { // Reminder: Check if check for Email Format works correctly
        if(supervisor.getFirstname() == null || supervisor.getLastname() == null ||supervisor.getTitle() == null ||supervisor.getEmailAddress() == null) {
            return false;
        }
        if(supervisor.getFirstname().chars().allMatch(Character::isLetter)) {
            if(supervisor.getLastname().chars().allMatch(Character::isLetter)) {
                if(!supervisor.getTitle().matches("[0-9]+")) {
                    if(supervisor.getEmailAddress().matches("[a-zA-Z0-9.]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
