package fhtw.cartridgeScaping.gameplay.console;

import fhtw.cartridgeScaping.controller.ViewManager;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Objects;

public class CommandText {
    private final String name;
//    private final ArrayList<String> staticArguments;
//    private final boolean hasArguments;
    private final String argument;
    private final boolean hasArgument;
    private HelpText helpText;

//    public CommandText(String name, ArrayList<String> staticArguments, boolean hasArguments) {
//        this.name = name;
//        this.staticArguments = staticArguments;
//        this.hasArguments = hasArguments;
//        this.helpText = null;
//    }
//
//    public CommandText(String name, ArrayList<String> arguments, boolean hasArguments, HelpText helpText) {
//        this(name, arguments, hasArguments);
//        this.helpText = helpText;
//    }

    public CommandText(String name, String argument, boolean hasArgument) {
        this.name = name;
        this.argument = argument;
        this.hasArgument = hasArgument;
        this.helpText = null;
    }

    public CommandText(String name, String argument, boolean hasArgument, HelpText helpText) {
        this(name, argument, hasArgument);
        this.helpText = helpText;
    }

    public String name() {
        return name;
    }

//    public ArrayList<String> arguments() {
//        return staticArguments;
//    }
//
//    public boolean hasArguments() {
//        return hasArguments;
//    }

    public String argument() {
        return argument;
    }
    public boolean hasArgument() {
        return hasArgument;
    }

    public void setHelpText(HelpText helpText) {
        this.helpText = helpText;
    }

    public HelpText getHelpText() {
        return helpText;
    }

    public String getHelp(boolean verbose) {
        if(helpText == null) {
            return "No helpText available.\n";
        }
        StringBuilder strBuilder = new StringBuilder(name);
        strBuilder.append("\t-|-\t");
        strBuilder.append(helpText.shortDescription());
        if(verbose) {
            strBuilder.append(helpText.longDescription());
            strBuilder.append("\nDetails:\n");
            strBuilder.append(helpText.actionExplanation());
            strBuilder.append("\nUsage:\n");
            strBuilder.append(helpText.usage());
        }
        if(ViewManager.isDeveloperMode()) {
            System.out.printf("%s", strBuilder);
        }
        return strBuilder.toString();
    }

//    public static CommandText createCommandText(String command, HelpText helpText) {
//        ArrayList<String> arguments = new ArrayList<>();
//        String name;
//        if (command.contains(" ")) {
//            String[] tmp = command.split(" ", 2); // Separates first word from rest of string
//            name = tmp[0]; // Sets name to first word
//            arguments.addAll(Arrays.asList(tmp[1].split(" "))); // Adds rest of string as seperated arguments
//        } else {
//            name = command;
//        }
//        return new CommandText(name, arguments, !arguments.isEmpty(), helpText);
//    }

    static CommandText createCommandText(String command, HelpText helpText) {
        String argument = "";
        String name;
        if (command.contains(" ")) {
            String[] tmp = command.split(" ", 2); // Separates first word from rest of string
            name = tmp[0]; // Sets name to first word
            if(tmp[1].contains(" ")) { // Check if rest of argument is a single word
                argument = tmp[1].split(" ")[0]; // Accept only first argument, ignoring the rest
            } else {
                argument = tmp[1];
            }
        } else {
            name = command;
        }
        return new CommandText(name, argument, !argument.isEmpty(), helpText);
    }
}
