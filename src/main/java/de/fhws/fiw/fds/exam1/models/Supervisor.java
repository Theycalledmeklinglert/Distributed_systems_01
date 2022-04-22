package de.fhws.fiw.fds.exam1.models;

public class Supervisor {
    private String firstname;
    private String lastname;
    private String title;
    private String emailAddress;

    public Supervisor() {
        super();
    }
    public Supervisor(String firstname, String lastname, String course, String emailAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = course;
        this.emailAddress = emailAddress;
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

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
