package io.borlandfcsd.university.entity;
import io.borlandfcsd.university.vote.Voteable;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;
    private List<Student> students;
    private List<Subject> subjects;
    private List<Voteable> voteList;
    private Student leader;

    public Group(int id, String name, List<Student> students, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.subjects = subjects;
        this.voteList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Voteable> getVoteList() {
        return voteList;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nGroup info:\n");
        builder.append("\n\tName: ").append(name);
        builder.append("\n\tleader: ");
        if (leader != null) {
            builder.append(leader.getFullName());
        } else {
            builder.append("[No leader]");
        }
        builder.append("\n\tStudents:\n");
        for (int i = 0; i < students.size(); i++) {
            builder.append("\t\t")
                    .append(i + 1)
                    .append(" ")
                    .append(students.get(i).getFullName())
                    .append('\n');
        }
        return builder.toString();
    }
}
