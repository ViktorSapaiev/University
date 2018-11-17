package io.borlandfcsd.university.menu2.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.menu2.Command;
import io.borlandfcsd.university.menu2.Menu;
import io.borlandfcsd.university.vote.VoteForGroupLeader;
import io.borlandfcsd.university.vote.Voteable;

public class CreateVoteCommand extends Command {
    public CreateVoteCommand() {
        super("Create vote");
    }

    @Override
    public void run() {
        University university = University.getInstance();
        Student student = (Student) university.getUser();
        new VoteForGroupLeader(student.getGroup());
        addVoteToMenu();
    }

    private void addVoteToMenu(){
        Menu menu = Menu.getInstance();
        Student student = (Student)University.getInstance().getUser();
        menu.addUsersCommand(new CreateVoteCommand());
        for (Voteable item : student.getVoteList()) {
            menu.addUsersCommand(new Command(item.getQuestion()) {
                @Override
                public void run() {
                    item.vote();
                }
            });
        }
    }
}
