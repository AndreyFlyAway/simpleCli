package com.lessons.cli.commands;

public interface Command {
    void doWork() throws WrongCommandClassUsing;

    // checking validation of arguments
    boolean checkUsing();
}
