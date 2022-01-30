package com.lessons.cli;

import com.lessons.cli.commands.Command;

import java.util.Scanner;
import com.lessons.cli.commands.*;

/**
 * This program implement different CLI commands. Created for practice
 * @version 0.1 2022-01-29
 * @author Andrei Ianikeev
 */
public class LessonTest {
    private static String EXIT;
    static {
        EXIT = "exit";
    }
    public static void main(String... args) {
        System.out.println("Simple command line application. \nEnter command:");
        Scanner scanner = new Scanner(System.in);
        String cmd = "";
        String[] cmdArgs;
        while (!cmd.equals(EXIT)) {
            cmd = scanner.nextLine();
            Command cmdToExecute;
            cmdArgs = cmd.split(" ");
            // TODO: change this else if by using map and reflection?
            if (cmdArgs[0].equals(EchoSimple.getCmdName())) {
                cmdToExecute = new EchoSimple(cmdArgs);
            } else if (cmdArgs[0].equals(EchoMultiline.getCmdName())) {
                cmdToExecute = new EchoMultiline(cmdArgs);
            }else if (cmdArgs[0].equals(CollectionsUse.getCmdName())) {
                cmdToExecute = new CollectionsUse(cmdArgs);
            }
            else {
                System.out.println("Wrong command");
                cmdToExecute = new HelpCmd();
            }
            // TODO: I suppose no need to use exception, I tried it for practice
            try {
                cmdToExecute.doWork();
            } catch (WrongCommandClassUsing e) {
                System.out.println(e.getMessage());
//                e.printStackTrace();
//                throw e;
            }
        }
        System.out.println("End of work.");
    }
}


