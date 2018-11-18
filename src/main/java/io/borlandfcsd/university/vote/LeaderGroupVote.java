package io.borlandfcsd.university.vote;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Group;
import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeaderGroupVote implements Voteable{
    private String question;
    private List<Answer<Student>> answers;
    private Group group;
    private List<Student> votedStudent = new ArrayList<>();
    private int round = 1;



    public LeaderGroupVote(Group group) {
        this.group = group;
        this.question = "Who will be leader of group?(Round " + round +")";

        answers = new ArrayList<>();
        createAnswers(group.getStudents());

    }

    @SuppressWarnings("unchecked")
    private void createAnswers(List<Student> students) {
        for(Student student : students){
            Answer<Student> answer = new Answer(student);
            answers.add(answer);
        }
        if(round == 1) {
            group.getVoteList().add(this);
        }
    }

    public void vote(){
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
            finishVoting();
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

    public void showResults() {
        for (Answer<Student> answer : answers) {
            System.out.println(answer.getAnswer().getLastName() + " " + answer.getVoices());
        }
    }

    private void printAnswers() {
        int count = 0;
        for (Answer answer : answers) {
            Student student = (Student) answer.getAnswer();
            System.out.println(
                    count + " " + student.getFirstName()
                            + " " + student.getLastName()
            );
            for (String trait : student.getTraits()) {
                System.out.println(
                        '\t' + trait
                );
            }
            count++;
        }
    }

    private void addVoice(int userAnswer) {
        if (userAnswer >= 0 && userAnswer < answers.size()) {
            answers.get(userAnswer).addVoice();
            votedStudent.add(University.getCurrentStudent());
            System.out.println("Your answer accept");
            showResults();
        } else {
            System.err.println("Invalid answer, try again");
            vote();
        }
    }


    private void finishVoting() {
        if(group.getStudents().size() == votedStudent.size()){
            Student student = findWinner();
            if(student != null) {
                student.setRole(Role.LEADER);
                group.setLeader(student);
                removeVote();
            }
        }
    }

    private Student findWinner(){
        Answer<Student> leader = answers.get(0);

        for(Answer<Student> answer : answers){
            if(leader.getVoices() < answer.getVoices()){
                leader = answer;
            }
        }

        List<Student> ifDraw = checkDraw(leader);
        if (!ifDraw.isEmpty()){
            draw(ifDraw);
            return null;
        }

        return leader.getAnswer();
    }

    private List<Student> checkDraw(Answer<Student> leader) {
        List<Student> ifDraw = new ArrayList<>();
        for(Answer<Student> answer : answers){
            if(leader.getVoices() == answer.getVoices()) {
                if(!leader.getAnswer().equals(answer.getAnswer())){
                    ifDraw.add(answer.getAnswer());
                }
            }
        }
        if (ifDraw.isEmpty()){
            return ifDraw;
        } else {
            ifDraw.add(leader.getAnswer());
            return ifDraw;
        }
    }

    private void draw(List<Student> students){
        votedStudent = new ArrayList<>();
        answers = new ArrayList<>();
        round++;
        createAnswers(students);
        this.question = "Who will be leader of group?(Round " + round +")";
    }

    private void removeVote() {
        List<Voteable> votes = group.getVoteList();
        for(int i = 0; i< votes.size(); i++){
            Voteable vote = votes.get(i);
            if (this == vote) {
                votes.remove(i);
                break;
            }
        }
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
