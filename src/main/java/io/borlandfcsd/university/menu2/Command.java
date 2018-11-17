package io.borlandfcsd.university.menu2;

public abstract class Command {
    private String title;

    public Command(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void run();
}
