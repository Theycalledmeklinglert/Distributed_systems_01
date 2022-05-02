package de.fhws.fiw.fds.exam1;

public class SupervisorView {
    private String firstname;
    private String lastname;
    private String title;
    private String emailAddress;

    public SupervisorView() {

    }

    public SupervisorView(String firstname, String lastname, String course, String emailAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = course;
        this.emailAddress = emailAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
