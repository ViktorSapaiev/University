package io.borlandfcsd.university.menu;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.vote.VoteForGroupLeader;

import java.util.List;

public class VoteMenu extends MenuEntry {

    public VoteMenu(String title, String message) {
        super(title, message);
        addItem(Menu.getInstance());
    }

    @Override
    public void run() {
        University university = University.getInstance();
        Student student = (Student)university.getUser();
        List<VoteForGroupLeader> voteItems = student.getVoteList();

        if(voteItems.isEmpty()){
            System.out.println("No open vote");
        }

        int count = 0;

        System.out.println("==Action List==");
        for (MenuEntry entry : entries) {
            System.out.println(count + " " + entry.getTitle());
        }
        for (int i = 0; i < voteItems.size(); i++){
            System.out.println(count + " " + voteItems.get(i).getQuestion());
        }
        System.out.println("Enter your command: ");
        int answer = scanner.nextInt();
        if (answer >= 0 && answer < voteItems.size()) {
            voteItems.get(answer).vote();
        } else {
            System.out.println("Invalid answer, try again.");
        }
        run();
    }
}
