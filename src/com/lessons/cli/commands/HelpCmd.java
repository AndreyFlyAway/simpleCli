package com.lessons.cli.commands;

public class HelpCmd implements Command {
    private static String cmdName;
    private static String desc;
    private static final String cmdFormat = "%s %s\n";

    static {
        cmdName = "help";
        desc = "Printing help and allowed commands.";
    }

    @Override
    public void doWork() throws WrongCommandClassUsing {
        if (!checkUsing()) {
            throw new WrongCommandClassUsing();
        }
        System.out.println("Allowed commands:");
        printCmdHelp(HelpCmd.cmdName, HelpCmd.desc);
        printCmdHelp(EchoSimple.getCmdName(), EchoSimple.getDesc());
        printCmdHelp(EchoMultiline.getCmdName(), EchoMultiline.getDesc());
    }

    @Override
    public boolean checkUsing() {
        return true;
    }

    private void printCmdHelp(String aCmd, String aDesc) {
        System.out.printf(cmdFormat, aCmd, aDesc);
    }
}
