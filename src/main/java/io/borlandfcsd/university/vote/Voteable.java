package io.borlandfcsd.university.vote;

public interface Voteable {
    void vote();
    String getQuestion();
    void showResults();
}
