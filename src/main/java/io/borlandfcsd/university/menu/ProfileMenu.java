package io.borlandfcsd.university.menu;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;

import java.util.Scanner;

public class ProfileMenu extends MenuEntry {
    public ProfileMenu(String title, String message) {
        super(title, message);
        addItem(Menu.getInstance());
    }

    @Override
    public void run() {
        Person person = University.getInstance().getUser();

        if(person.getRole() == Role.PROFESSOR){
            System.out.println((Professor)person);
        } else {
            System.out.println((Student)person);
        }
    }

    private void printMenu(){
        int count = 0;
        System.out.println("== List commands ==" + '\n');
        for(MenuEntry entry : entries){
            System.out.println(count + " " + entry.getTitle());
        }
        System.out.println("Enter your command: ");
        int answer = scanner.nextInt();
        if(answer >= 0 && answer < entries.size()){
            entries.get(answer).run();
        } else {
            System.out.println("Invalid answer, try again: ");
            run();
        }
    }
}
