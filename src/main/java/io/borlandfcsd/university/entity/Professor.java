package io.borlandfcsd.university.entity;

import io.borlandfcsd.university.vote.Voteable;

import java.util.ArrayList;
import java.util.List;

public class Professor  extends Person{
    private List<Subject> subjects;
    private Voteable openVote;
    private List<Voteable> finishVote;

    public Professor(int id, String firstName, String lastName,Role role, String username, String password, List<Subject> subjects) {
        super(id, firstName, lastName, role, username, password);
        this.subjects = subjects;
        this.finishVote = new ArrayList<>();
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Voteable getOpenVote() {
        return openVote;
    }

    public void setOpenVote(Voteable openVote) {
        this.openVote = openVote;
    }

    public List<Voteable> getFinishVote() {
        return finishVote;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + super.getId() +
                ", full name='" + super.getFullName() + '\'' +
                ", lastName='" + lastName + '\'' +
                "subjects=" + subjects +
                '}';
    }
}
