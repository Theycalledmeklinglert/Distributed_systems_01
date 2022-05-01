package de.fhws.fiw.fds.exam1.models;

public class ValidityCheck {

    public static boolean checkProject(Project project)
    {
        if(isMissingAttributes(project))
        {
            return false;
        }
        // Project name and description may contain all kinds of letters and signs therefore no check necessary
        if(isValidSemester(project))
        {
                if(validStudents(project))
                {
                    if (validSupervisors(project))
                    {
                        return true;
                    }
                }
        }
        return false;
    }

    // if Loops are nested for better readability
    public static boolean checkStudent(Student student) {
        if(isMissingAttributes(student))
        {
            return false;
        }
        if(isLetter(student.getFirstname()))
        {
            if(isLetter(student.getLastname()))
            {
                if(isLetter(student.getCourse()))
                {
                    if(student.getSemester() > 0 && student.getSemester() < 8)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkSupervisor(Supervisor supervisor) { // Reminder: Check if check for Email Format works correctly
        if(isMissingAttributes(supervisor))
        {
            return false;
        }
        if(isLetter(supervisor.getFirstname()))
        {
            if(isLetter(supervisor.getLastname()))
            {
                if(isValidTitle(supervisor.getTitle()))
                {
                    if(isValidEMail(supervisor.getEmailAddress()))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isMissingAttributes(Project project)
    {
        return project.getName() == null || project.getDescription() == null || project.getSemester() == null || project.getType() == null;
    }
    private static boolean isMissingAttributes(Student student)
    {
        return student.getFirstname() == null || student.getLastname() == null || student.getCourse() == null;
    }
    private static boolean isMissingAttributes(Supervisor supervisor)
    {
        return supervisor.getFirstname() == null || supervisor.getLastname() == null ||supervisor.getTitle() == null ||supervisor.getEmailAddress() == null;
    }

    private static boolean isValidSemester(Project project)
    {
        return project.getSemester().matches("[0-9]{4}[a-zA-Z]{2}");
    }

    private static boolean validStudents(Project project)
    {
       return project.getStudents() == null || project.getStudents().stream().allMatch(student -> checkStudent(student));
    }
    private static boolean validSupervisors(Project project)
    {
        return project.getSupervisors() == null || project.getSupervisors().stream().allMatch(supervisor -> checkSupervisor(supervisor));
    }

    private static boolean isLetter(String string)
    {
        return string.chars().allMatch(Character::isLetter);
    }

    private static boolean isValidTitle(String title)
    {
        return !title.matches("[0-9]+");
    }

    private static boolean isValidEMail(String eMail)
    {
        return eMail.matches("[a-zA-Z0-9.]+[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+");
    }

}
