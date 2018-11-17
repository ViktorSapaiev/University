package io.borlandfcsd.university.vote;

public interface Voteable {
    void vote();
    String getQuestion();
    int getId();
    void showResults();
}
