package com.lessons.cli.commands;

public class WrongCommandClassUsing extends RuntimeException {
    public WrongCommandClassUsing() {
        super("incorrect arguments of command passed into command class.");
    }
    public WrongCommandClassUsing(String gripe) {
        super(gripe);
    }
}
