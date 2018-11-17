package io.borlandfcsd.university.menu2.commands;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.menu2.Command;
import io.borlandfcsd.university.menu2.Menu;

import java.util.ArrayList;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super("Logout");
    }

    public void run() {
        University.getInstance().setUser(null);
    }
}
