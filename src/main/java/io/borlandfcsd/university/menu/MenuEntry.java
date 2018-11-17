package io.borlandfcsd.university.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class MenuEntry {
    protected String title;
    protected String message;
    protected List<MenuEntry> entries;
    protected Scanner scanner;

    MenuEntry(String title, String message) {
        this.title = title;
        this.message = message;
        this.entries = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public abstract void run();

    public void addItem(MenuEntry entry){
        entries.add(entry);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public List<MenuEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MenuEntry> entries) {
        this.entries = entries;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
