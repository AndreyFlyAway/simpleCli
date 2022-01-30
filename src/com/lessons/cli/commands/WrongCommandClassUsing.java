package com.lessons.cli.commands;

public class WrongCommandClassUsing extends RuntimeException {
    public WrongCommandClassUsing() {
        super("incorrect arguments of command.");
    }
    public WrongCommandClassUsing(String gripe) {
        super(gripe);
    }
}
