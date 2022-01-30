package com.lessons.cli.commands;

import java.util.ArrayList;

public class EchoSimple implements Command {
    // TODO: Make using ArrayList just for practice, figure out
    //       should I use simple array instead of ArrayList.
    protected ArrayList<String> cmdArgs;

    private static final String cmdName;
    private static final String desc;

    static {
        cmdName = "echo";
        desc = "printing passed in command ";
    }

    {
        cmdArgs = new ArrayList<String>();
    }

    public EchoSimple(String... aArgs) {
        for (int i = 1; i < aArgs.length; i++) {
            cmdArgs.add(aArgs[i]);
        }
    }

    public static String getCmdName() {
        return cmdName;
    }

    public static String getDesc() {
        return desc;
    }

    @Override
    public void doWork() throws WrongCommandClassUsing {
        if (!checkUsing()) {
            throw new WrongCommandClassUsing();
        }
        cmdArgs.forEach(name -> {
            System.out.printf("%s ", name);
        });
        System.out.println("");
    }

    @Override
    public boolean checkUsing(){
        return false;
    }
}
