package io.borlandfcsd.university.entity;

public class Subject {
    private int id;
    private String name;
    private Professor professor;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", professor= "
                    + professor.getFirstName() + " "
                    + professor.getLastName() + " "
                +'}';
    }
}
