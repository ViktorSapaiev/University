package io.borlandfcsd.university;

import io.borlandfcsd.university.dao.GroupDao;
import io.borlandfcsd.university.dao.ProfessorDao;
import io.borlandfcsd.university.dao.SubjectDao;
import io.borlandfcsd.university.entity.*;
import io.borlandfcsd.university.menu2.Menu;
import io.borlandfcsd.university.menu2.Command;
import io.borlandfcsd.university.menu2.commands.BeginVoteCommand;
import io.borlandfcsd.university.menu2.commands.GetProfileCommand;
import io.borlandfcsd.university.vote.Voteable;

import java.util.ArrayList;

public class University {
    private Person user;

    public static void main(String[] args) {
       University university = UniversityHelper.INSTANCE;
       GroupDao groupDao = GroupDao.getInstance();
       ProfessorDao professorDao = ProfessorDao.getInstance();
       SubjectDao subjectDao = SubjectDao.getInstance();

       Menu menu = Menu.getInstance();

       menu.run();
    }

    public static void createUserMenu(){
        Menu menu = Menu.getInstance();
        menu.addUsersCommand(new GetProfileCommand());
        Student student = University.getCurrentStudent();
        if(student != null) {
            if(student.getRole() == Role.LEADER && student.getGroup().getLeader() != null) {
                menu.addUsersCommand(new BeginVoteCommand());
            }
            for (Voteable item : student.getGroup().getVoteList()) {
                menu.addUsersCommand(new Command(item.getQuestion()) {
                    @Override
                    public void run() {
                        item.vote();
                    }
                });
            }
        }
    }

    public static void clearUserMenu(){
        Menu menu = Menu.getInstance();
        menu.setUserCommands(new ArrayList<>());
    }

    public static Student getCurrentStudent(){
        Student student = (Student)University.getInstance().getUser();
        if(student.getRole() == Role.STUDENT || student.getRole() == Role.LEADER){
            return student;
        } else {
            System.err.println("Current user is not student");
            return null;
        }
    }

    private static class UniversityHelper{
        private static final University INSTANCE  = new University();
    }

    public static University getInstance(){
        return UniversityHelper.INSTANCE;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }
}
