package io.borlandfcsd.university.entity;

import java.util.List;
import java.util.Objects;

public class Student extends Person {
    private List<String> traits;
    private Group group;

    public Student(int id, String firstName, String lastName, String username, String password, Role role, List<String> traits) {
        super(id, firstName, lastName, role, username, password);
        this.traits = traits;

    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return role == student.role &&
                Objects.equals(traits, student.traits);
    }

    @Override
    public int hashCode() {

        return Objects.hash(role, traits);
    }

    @Override
    public String toString() {
        return "Student{" +
                "role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group.getName() + '\'' +
                ", traits=" + traits +
                '}';
    }
}
