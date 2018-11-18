package io.borlandfcsd.university.menu.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.menu.Command;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super("Logout");
    }

    public void run() {
        University.getInstance().setUser(null);
    }
}
