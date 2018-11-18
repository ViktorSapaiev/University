package io.borlandfcsd.university.menu.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.dao.GroupDao;
import io.borlandfcsd.university.dao.LectureDao;
import io.borlandfcsd.university.entity.*;
import io.borlandfcsd.university.menu.Command;
import io.borlandfcsd.university.utils.UserInputUtils;
import io.borlandfcsd.university.vote.LeaderGroupVote;
import io.borlandfcsd.university.vote.PresentStudentsVote;

import java.util.List;
import java.util.Scanner;

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

        if (person.getRole() == Role.PROFESSOR) {
            createPresentStudentsVote();
        }
    }

    private void createPresentStudentsVote() {
        LectureDao dao = LectureDao.getInstance();
        Lecture lecture = new Lecture(setSubject(), setGroup());
        dao.addLecture(lecture);
        new PresentStudentsVote(lecture);

    }

    private Group setGroup() {
        List<Group> groups = GroupDao.getInstance().getGroups();
        System.out.println("\nChoose group: ");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(i + " " + groups.get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        if (UserInputUtils.isInRange(0, groups.size(), answer)) {
            return groups.get(answer);
        }
        return setGroup();
    }

    private Subject setSubject() {
        Professor professor = University.getCurrentProfessor();
        if (professor != null) {
            List<Subject> subjects = professor.getSubjects();
            System.out.println("\nChoose subject: ");
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println(i + " " + subjects.get(i).getName());
            }
            Scanner scanner = new Scanner(System.in);
            int answer = scanner.nextInt();
            if (UserInputUtils.isInRange(0, subjects.size(), answer)) {
                return subjects.get(answer);
            }
            return setSubject();
        }
        return null;
    }
}
