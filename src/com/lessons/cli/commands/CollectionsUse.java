package com.lessons.cli.commands;

import java.util.*;

public class CollectionsUse implements Command {
    private static final String cmdName;
    private static final String desc;
    private static Map<String, String> elements;

    // names of maps type to create
    private static final String mHashMap;
    private static final String mTreeMap;
    private static final String mLinkedMap;

    static {
        cmdName = "collect";
        desc = "Adding string elements to collection and printing daa of collection.";
        mHashMap = "hashmap";
        mTreeMap = "treemap";
        mLinkedMap = "linkedmap";
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
            case SETTYPE:
                recreateMap();
                break;
            case ADD:
            case SET:
                elements.put(cmdArgs[2], cmdArgs[3]);
                break;
            case REMOVE:
                elements.remove(cmdArgs[2]);
                break;
            case CLEAR:
                elements.clear();
                break;
            case GET:
                var val = elements.getOrDefault(cmdArgs[2], "No such key in map.");
                System.out.println(val);
                break;
            case SHOWALL:
                showAll();
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
                case SETTYPE:
                case REMOVE:
                case GET:
                    if (cmdArgs.length != 3) return false;
                    break;
                case SHOWALL:
                case CLEAR:
                    if (cmdArgs.length != 2) return false;
                    break;
            }
        }
        for(String s : cmdArgs) {
            if (s.length() == 0) return false;
        }
        return true;
    }

    private void showAll() {
        if (elements.isEmpty()) {
            System.out.println("Collection is empty.");
        } else {
            System.out.println("Elements in collection:");
            elements.forEach((k, v) ->
            {System.out.printf("\t %s: %s\n", k, v);});
        }
    }

    private void recreateMap() {
        var m = elements;
        if (cmdArgs[2].equals(mHashMap)) {
            if (elements.getClass() != HashMap.class) {
                System.out.println("Recreated map into hashMap");
                elements = new HashMap<String, String>();
                elements.putAll(m);
            }
        } else if (cmdArgs[2].equals(mTreeMap)) {
            if (elements.getClass() != TreeMap.class) {
                System.out.println("Recreated map into TreeMap");
                elements = new TreeMap<String, String>();
                elements.putAll(m);
            }
        } else if (cmdArgs[2].equals(mLinkedMap)) {
            if (elements.getClass() != LinkedHashMap.class) {
                System.out.println("Recreated map into LinkedListMap");
                elements = new LinkedHashMap<String, String>();
                elements.putAll(m);
            }
        } else {
            System.out.println("No such type of map available!");
        }

        System.out.println("Now type: " + elements.getClass());
    }

    private static enum Operation {

        SETTYPE("settype"),
        ADD("add"),
        REMOVE("remove"),
        CLEAR("clear"),
        GET("get"),
        SET("set"),
        SHOWALL("showall");

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
