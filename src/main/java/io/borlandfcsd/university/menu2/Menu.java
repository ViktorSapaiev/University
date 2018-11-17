package io.borlandfcsd.university.menu2;

import io.borlandfcsd.university.University;
import io.borlandfcsd.university.menu2.commands.ExitCommand;
import io.borlandfcsd.university.menu2.commands.LoginCommand;
import io.borlandfcsd.university.menu2.commands.LogoutCommand;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<Command> systemCommands = new ArrayList<Command>();
    private List<Command> userCommands = new ArrayList<Command>();
    private boolean isExit = false;

    private Menu() {
        systemCommands.add(new LoginCommand());
        systemCommands.add(new ExitCommand());
    }

    public void run() {
        while (!isExit) {
            List<Command> commandList = defineCommandList();
            printMenu(commandList);
            getCommandFromUser(commandList);
        }
    }

    private List<Command> defineCommandList(){
        if(University.getInstance().getUser() != null){
            switchSystemCommand(true);
            List<Command> commands = new ArrayList<>();
            commands.addAll(systemCommands);
            commands.addAll(userCommands);
            return commands;
        } else {
            switchSystemCommand(false);
            return systemCommands;
        }
    }

    private void switchSystemCommand(boolean switcher) {
        if(switcher){
            systemCommands.set(0,new LogoutCommand());
        } else {
            systemCommands.set(0,new LoginCommand());
        }
    }

    private void printMenu(List<Command> commands) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\nMenu:\n");
        for (int i = 0; i < commands.size(); i++) {
            Command entry = commands.get(i);
            String entryFormatted = String.format(MENU_PATTERN, (i + 1), entry.getTitle());
            buffer.append(entryFormatted);
        }
        System.out.print(buffer.toString());
    }

    private void getCommandFromUser(List<Command> commandList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose command: ");
        try {
            int command = scanner.nextInt();
            if(checkUserCommand(command, commandList)){
                commandList.get(command-1).run();
            }
        } catch (Exception e) {
            System.err.println("Command must be integer");
            scanner.next();
        }
    }

    private boolean checkUserCommand(int userAnswer, List<Command> commands) {
        if (userAnswer < 1 || userAnswer > commands.size()){
            System.err.println("This command does not  exist. Try again");
            return false;
        }
        return true;
    }

    public void addUsersCommand(Command entry) {
        userCommands.add(entry);
    }


    private static class MenuHelper{
        private final static Menu INSTANCE = new Menu();
    }

    public static Menu getInstance(){
        return MenuHelper.INSTANCE;
    }

    public void setUserCommands(List<Command> userCommands) {
        this.userCommands = userCommands;
    }

    public void exit() {
        isExit = true;
    }
}
