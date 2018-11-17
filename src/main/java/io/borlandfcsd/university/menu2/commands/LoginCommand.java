package io.borlandfcsd.university.menu2.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.dao.ProfessorDao;
import io.borlandfcsd.university.dao.StudentDao;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.menu2.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginCommand extends Command {
    public LoginCommand() {
        super("Login");
    }

    public void run() {
        login();
    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        List<Person> personList = getPersons();
        for (Person person : personList){
            if(person.getUsername().equals(username) && person.getPassword().equals(password)){
                University.getInstance().setUser(person);
            }
        }
        if (University.getInstance().getUser() == null){
            System.err.println("Wrong login or password. Try again.");
        }
    }

    private List<Person> getPersons() {
        List<Person> personList = new ArrayList<>();
        personList.addAll(StudentDao.getInstance().getStudents());
        personList.addAll(ProfessorDao.getInstance().getProfessors());

        return personList;
    }
}
