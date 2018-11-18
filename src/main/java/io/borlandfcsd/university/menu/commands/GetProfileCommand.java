package io.borlandfcsd.university.menu.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.*;
import io.borlandfcsd.university.menu.Command;

public class GetProfileCommand extends Command {
    public GetProfileCommand() {
        super("Profile");
    }

    @Override
    public void run() {
        Person person = University.getInstance().getUser();
        if(person.getRole() == Role.PROFESSOR){
            printProfessorInfo((Professor)person);
        } else {
            printStudentInfo((Student)person);
        }
    }

    private void printStudentInfo(Student student){
        StringBuilder builder = new StringBuilder();
        builder.append("\nProfile:\n");
        builder.append("\n\tFull name: ").append(student.getFullName());
        builder.append("\n\tGroup: ").append(student.getGroup().getName());
        builder.append("\n\tLeader of group: ");
        if(student.getGroup().getLeader() != null) {
            builder.append(student.getGroup().getLeader().getFullName());
        } else {
            builder.append("Leader is not define. You can vote for leader");
        }
        builder.append("\n\tTraits: ");
        for (String trait : student.getTraits()){
            builder.append("\n\t\t-").append(trait);
        }
        System.out.println(builder.toString());
    }

    private void printProfessorInfo(Professor professor){
        StringBuilder builder = new StringBuilder();
        builder.append("\nProfile:\n");
        builder.append("\n\tFull name: ").append(professor.getFullName());
        builder.append("\n\tSubjects: ");
        for (Subject subject : professor.getSubjects()){
            builder.append("\n\t\t-").append(subject.getName());
        }
        System.out.println(builder.toString());
    }



}
