package io.borlandfcsd.university.menu;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.dao.ProfessorDao;
import io.borlandfcsd.university.dao.StudentDao;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.entity.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu extends MenuEntry {
    private Menu(String title, String message) {
        super(title, message);
    }

    @Override
    public void run() {
        System.out.println(super.getTitle());

        if(University.getInstance().getUser() == null) {
            login();
            message = message + " " + University.getInstance().getUser().getUsername();
            entries.add(new MenuEntry("Logout"," ") {
                @Override
                public void run() {
                    University.getInstance().setUser(null);
                }
            });
        }

        System.out.println(super.getMessage());
        List<MenuEntry> items = super.getEntries();
        System.out.println("== List commands ==" + '\n');
        for (int i = 0; i < items.size(); i++){
            System.out.println(i + " "+ items.get(i).getTitle());
        }
        System.out.println("Enter your command: ");
        int answer = scanner.nextInt();
        if (answer >= 0 && answer < items.size()) {
            items.get(answer).run();
        } else {
            System.out.println("Invalid answer, try again.");
        }
        run();
    }

    private  static class MenuHelper{
        private static final Menu INSTANCE = new Menu("Menu","Welcome");
    }

    public static Menu getInstance(){
        return MenuHelper.INSTANCE;
    }

    private void login(){
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
    }

    private List<Person> getPersons() {
        List<Person> personList = new ArrayList<>();
        personList.addAll(StudentDao.getInstance().getStudents());
        personList.addAll(ProfessorDao.getInstance().getProfessors());

        return personList;
    }
}
