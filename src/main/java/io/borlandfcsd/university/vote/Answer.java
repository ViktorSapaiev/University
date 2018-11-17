package io.borlandfcsd.university.vote;

public class Answer <T> {
    private T answer;
    private int voices = 0;

    Answer(T answer) {
        this.answer = answer;
    }

    public void addVoice(){
        voices++;
    }

    public T getAnswer() {
        return answer;
    }

    public void setAnswer(T answer) {
        this.answer = answer;
    }

    public int getVoices() {
        return voices;
    }

    public void setVoices(int voices) {
        this.voices = voices;
    }
}
