package io.borlandfcsd.university.entity;


import java.util.List;

public class Group {
    private int id;
    private String name;
    private List<Student> students;
    private List<Subject> subjects;
    private Student leader;

    public Group(int id, String name, List<Student> students, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name=" + name +
                ", students=" + students +
                ", subjects=" + subjects +
                ", leader=" + leader +
                '}';
    }
}
