package com.lessons.cli.commands;

import java.util.HashMap;
import java.util.Map;

public class CollectionsUse implements Command {
    private static final String cmdName;
    private static final String desc;
    private static Map<String, String> elements;

    static {
        cmdName = "collect";
        desc = "Adding string elements to collection and printing daa of collection.";
    }

    private String[] cmdArgs;

    public static String getCmdName() {
        return cmdName;
    }

    public static String getDesc() {
        return desc;
    }

    public CollectionsUse(String... args) {
        cmdArgs = args.clone();
        if (elements == null) {
            elements = new HashMap<String, String>();
        }
    }

    @Override
    public void doWork() throws WrongCommandClassUsing {
        if (!checkUsing()) {
            throw new WrongCommandClassUsing();
        }
        if (!Operation.isOpertion(cmdArgs[1])) {
            System.out.println("Error: wrong operation name");
            return;
        }
        Operation op = Enum.valueOf(Operation.class, cmdArgs[1].toUpperCase());

        switch (op){
            case ADD:
            case SET:
                elements.put(cmdArgs[2], cmdArgs[3]);
                break;
            case REMOVE:
                elements.remove(cmdArgs[2]);
                break;
            case GET:
                var val = elements.getOrDefault(cmdArgs[2], "No such key in map.");
                System.out.println(val);
                break;
            case SHOWALL:
                if (elements.isEmpty()) {
                    System.out.println("Collection is empty.");
                } else {
                    System.out.println("Elements in collection:");
                    elements.forEach((k, v) ->
                    {System.out.printf("\t %s: %s\n", k, v);});
                }
        }
    }

    @Override
    public boolean checkUsing() {
        if (cmdArgs.length == 0) {
            return false;
        }
        if (Operation.isOpertion(cmdArgs[1])) {
            Operation op = Enum.valueOf(Operation.class, cmdArgs[1].toUpperCase());
            switch (op){
                case ADD:
                case SET:
                    if (cmdArgs.length != 4) return false;
                    break;
                case REMOVE:
                case GET:
                    if (cmdArgs.length != 3) return false;
                    break;
                case SHOWALL:
                    if (cmdArgs.length != 2) return false;
                    break;
            }
        }
        for(String s : cmdArgs) {
            if (s.length() == 0) return false;
        }
        return true;
    }

    private static enum Operation {

        ADD("add"), REMOVE("remove"), GET("get"),
        SET("set"), SHOWALL("showall");

        private Operation(String aOpeartionName) { this.opeartionName = aOpeartionName.toUpperCase(); }

        private final String opeartionName;

        static public boolean isOpertion(String aName) {
            Operation[] aCmd = Operation.values();
            for (Operation c : aCmd) {
                if (c.opeartionName.equals(aName.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
    }
}
