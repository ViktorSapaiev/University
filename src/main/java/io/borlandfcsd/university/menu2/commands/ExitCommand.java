package io.borlandfcsd.university.menu2.commands;

import io.borlandfcsd.university.menu2.Command;
import io.borlandfcsd.university.menu2.Menu;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("Exit");
    }

    @Override
    public void run() {
        Menu.getInstance().exit();
    }
}
