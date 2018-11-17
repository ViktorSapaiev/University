package io.borlandfcsd.university.menu2.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.menu2.Command;
import io.borlandfcsd.university.vote.LeaderGroupVote;
import io.borlandfcsd.university.vote.PresentStudensVote;

public class BeginVoteCommand extends Command {
    public BeginVoteCommand() {
        super("Create vote");
    }

    @Override
    public void run() {
        Person person = University.getInstance().getUser();

        if (person.getRole() == Role.LEADER) {
            Student student = (Student) person;
            new LeaderGroupVote(student.getGroup());
        }

        if (person.getRole() == Role.PROFESSOR){
            Professor professor = (Professor) person;
            new PresentStudensVote();
        }
    }

}
