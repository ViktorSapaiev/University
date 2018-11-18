package io.borlandfcsd.university.menu.utils;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.menu.Command;
import io.borlandfcsd.university.menu.Menu;
import io.borlandfcsd.university.menu.commands.BeginVoteCommand;
import io.borlandfcsd.university.menu.commands.GetProfileCommand;
import io.borlandfcsd.university.vote.Voteable;

import java.util.ArrayList;

public abstract class MenuUtils {
    public static void createUserMenu() {
        Menu menu = Menu.getInstance();
        menu.addUsersCommand(new GetProfileCommand());
        Person person = University.getInstance().getUser();
        if (person != null) {
            if (person instanceof Student) {
                studentsCommandList(menu, (Student) person);
            } else {
                professorCommandList(menu, (Professor) person);
            }
        }
    }

    private static void studentsCommandList(Menu menu, Student student) {
        if (student.getRole() == Role.LEADER && student.getGroup().getLeader() != null) {
            menu.addUsersCommand(new BeginVoteCommand());
        }
        menu.addUsersCommand(new Command("Group info") {
            @Override
            public void run() {
                System.out.println(student.getGroup());
            }
        });
        for (Voteable item : student.getGroup().getVoteList()) {
            menu.addUsersCommand(new Command(item.getQuestion()) {
                @Override
                public void run() {
                    item.vote();
                }
            });
        }
    }

    private static void professorCommandList(Menu menu, Professor professor) {
        if (!professor.getFinishVote().isEmpty()) {
            for (Voteable item : professor.getFinishVote()) {
                menu.addUsersCommand(new Command("Finished lectures") {
                    @Override
                    public void run() {
                        item.showResults();
                        System.out.println('\n');
                    }
                });
            }
        }
        if (professor.getOpenVote() == null) {
            menu.addUsersCommand(new BeginVoteCommand());
        } else {
            menu.addUsersCommand(new Command("Show results: " + professor.getOpenVote().getQuestion()) {
                @Override
                public void run() {
                    professor.getOpenVote().vote();
                }
            });
        }
    }

    public static void clearUserMenu() {
        Menu menu = Menu.getInstance();
        menu.setUserCommands(new ArrayList<>());
    }
}
