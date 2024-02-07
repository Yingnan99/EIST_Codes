package de.tum.in.ase.eist;

public class Student {

    private String forename;
    private String surname;

    public Student(String forename, String surname) {
        setForename(forename);
        setSurname(surname);
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        String name = forename.replaceAll("[^A-Za-z\\-]", "");
        this.forename = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        String name = surname.replaceAll("[^A-Za-z\\-]", "");
        this.surname = name;
    }
}
