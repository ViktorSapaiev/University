package io.borlandfcsd.university.utils;

public class UserInputUtils {

    public static boolean isInRange(int min, int max, int input) {
        if (input >= min && input < max) {
            return true;
        }
        System.out.println("No such command. Try again");
        return false;
    }
}
