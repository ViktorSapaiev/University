package io.borlandfcsd.university.entity;

import io.borlandfcsd.university.University;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lecture {
    private static int idCounter = 1;
    private int id;
    private Subject subject;
    private Group group;
    private LocalDateTime dateTime;
    private Professor professor;

    public Lecture(Subject subject, Group group) {
        this.id = idCounter;
        this.subject = subject;
        this.group = group;
        this.dateTime = LocalDateTime.now();
        Person person = University.getCurrentProfessor();
        if (person != null) {
            this.professor = (Professor) person;
        }
        idCounter++;
    }


    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        return "Lecture #" + id + '\n' +
                "Subject: " + subject.getName() + '\n' +
                "Date: " + dateTime.format(formatter);
    }
}
