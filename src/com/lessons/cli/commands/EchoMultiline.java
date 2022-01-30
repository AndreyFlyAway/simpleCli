package com.lessons.cli.commands;

public class EchoMultiline extends EchoSimple
        implements Command {
    private static final String cmdName;
    private static final String desc;

    static {
        cmdName = "echom";
        desc = "printing argument of passed in command on each line";
    }

    public EchoMultiline(String... aArgs) {
        super(aArgs);
    }

    public static String getCmdName() {
        return cmdName;
    }

    public static String getDesc() {
        return desc;
    }

    @Override
    public void doWork() {
        cmdArgs.forEach(name -> {
            System.out.println(name);
        });
    }

}
