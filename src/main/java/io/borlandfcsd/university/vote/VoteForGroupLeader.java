package io.borlandfcsd.university.vote;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.entity.Group;
import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.menu2.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VoteForGroupLeader implements Voteable{
    private String question;
    private List<Answer<Student>> answers;
    private Group group;
    private List<Student> votedStudent = new ArrayList<>();
    private int round = 1;
    private boolean voteStatus = true;


    public VoteForGroupLeader(Group group) {
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
            student.getVoteList().add(this);
        }
    }


    private void finishVoting() {
        if(group.getStudents().size() == votedStudent.size()){
            Student student = findWinner();
            if(student != null) {
                student.setRole(Role.LEADER);
                group.setLeader(student);
                voteStatus = false;
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
        createAnswers(students);
        round++;
        this.question = "Who will be leader of group?(Round " + round +")";
        University.clearUserMenu();
        University.createUserMenu();
    }

    public void vote(){
        finishVoting();
        if(voteStatus) {
            if(checkVotedStudents()){
                System.out.println("You already voted. Current results: ");
                showResults();
            } else {
                System.out.println(question);

                printAnswers();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter you answer: ");
                int userAnswer = scanner.nextInt();
                checkUserAnswer(userAnswer);
                votedStudent.add(University.getCurrentStudent());
            }
        } else {
            System.out.println("VoteForGroupLeader is closed. results:");
            showResults();
        }
    }

    private boolean checkVotedStudents(){
        Student currentStudent = University.getCurrentStudent();
        for(Student student : votedStudent) {
            if(currentStudent != null){
                if (currentStudent.getUsername().equals(student.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printAnswers() {
        int count = 0;
        for (Answer answer : answers){
            Student student = (Student)answer.getAnswer();
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

    private void checkUserAnswer(int userAnswer) {
        if (userAnswer >= 0 && userAnswer < answers.size()){
            answers.get(userAnswer).addVoice();
            System.out.println("Your answer accept");
            showResults();
        } else {
            System.out.println("Invalid answer, try again");
            vote();
        }
    }

    public void  showResults(){
        for (Answer<Student> answer : answers){
            System.out.println(answer.getAnswer().getLastName() + " " + answer.getVoices());
        }
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
