package io.borlandfcsd.university.vote;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Lecture;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PresentStudentsVote implements Voteable {
    private String question;
    private List<Answer<String>> answers;
    private List<Student> votedStudent;
    private Lecture lecture;

    public PresentStudentsVote(Lecture lecture) {
        this.question = "Are you present on this lecture?\n\n" + lecture.toString();
        this.answers = new ArrayList<>();
        this.lecture = lecture;

        lecture.getGroup().getVoteList().add(this);
        Professor professor = University.getCurrentProfessor();

        if (professor != null) {
            professor.setOpenVote(this);
        }

        this.votedStudent = new ArrayList<>();
        createAnswers();
    }

    private void createAnswers() {
        answers.add(new Answer<>("Yes"));
        answers.add(new Answer<>("No"));
    }

    @Override
    public void vote() {
        Person person = University.getInstance().getUser();
        if (person instanceof Professor) {
            finishVoting();
        } else {
            if (checkVotedStudents()) {
                System.out.println("You already voted. Current results: ");
                showResults();
            } else {
                System.out.println(question);
                printAnswers();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter you answer: ");
                int userAnswer = scanner.nextInt();
                addVoice(userAnswer);
            }
        }
    }

    private boolean checkVotedStudents() {
        Student currentStudent = University.getCurrentStudent();
        for (Student student : votedStudent) {
            if (currentStudent != null) {
                if (currentStudent.getUsername().equals(student.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addVoice(int userAnswer) {
        if (userAnswer >= 0 && userAnswer < answers.size()) {
            System.out.println("Your answer accept");
            if (userAnswer == 0) {
                votedStudent.add(University.getCurrentStudent());
            }
            showResults();
        } else {
            System.err.println("Invalid answer, try again");
            vote();
        }
    }

    private void printAnswers() {
        int count = 0;
        for (Answer answer : answers) {
            System.out.println(count + " " + answer.getAnswer());
            count++;
        }
    }

    private void finishVoting() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter \"yes\" for finish");
        String answer = scanner.nextLine();
        if (answer.equals("yes")) {
            List<Voteable> votes = lecture.getGroup().getVoteList();
            for (int i = 0; i < votes.size(); i++) {
                Voteable vote = votes.get(i);
                if (this == vote) {
                    votes.remove(i);
                    break;
                }
            }
            this.question = question + "(finished)";
            Professor professor = University.getCurrentProfessor();
            if (professor != null) {
                professor.setOpenVote(null);
                professor.getFinishVote().add(this);
            }
        }
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public void showResults() {
        System.out.println(question);
        System.out.println("Present on lecture: ");
        for (Student student : votedStudent) {
            System.out.println("-" + student.getFullName());
        }
    }
}
