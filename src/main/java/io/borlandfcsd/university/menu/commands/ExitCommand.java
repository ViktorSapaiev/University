package io.borlandfcsd.university.menu.commands;

import io.borlandfcsd.university.menu.Command;
import io.borlandfcsd.university.menu.Menu;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("Exit");
    }

    @Override
    public void run() {
        Menu.getInstance().exit();
    }
}
