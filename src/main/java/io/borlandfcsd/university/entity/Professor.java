package io.borlandfcsd.university.entity;

import java.util.List;

public class Professor  extends Person{
    private List<Subject> subjects;

    public Professor(int id, String firstName, String lastName,Role role, String username, String password, List<Subject> subjects) {
        super(id, firstName, lastName, role, username, password);
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + super.getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "subjects=" + subjects +
                '}';
    }
}
