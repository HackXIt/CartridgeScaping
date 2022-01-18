package fhtw.cartridgeScaping.util;

import java.util.HashMap;
import java.util.function.Consumer;

// TODO Implement ExceptionHandler as singleton for globally handling available errors and their response
public class ExceptionHandler {
    private static ExceptionHandler singleton_instance = null;
    private final HashMap<String, ExceptionResponse> possibleResponses;
    private ExceptionHandler() {
        possibleResponses = new HashMap<>();
        possibleResponses.put("Invalid Port",
                new ExceptionResponse("The given port is invalid and couldn't be parsed."));
        // TODO Add more possibleResponses to ExceptionHandler - keep 'em coming, let's handle it
    }

    public static ExceptionHandler getInstance() {
        if(singleton_instance == null) {
            singleton_instance = new ExceptionHandler();
        }
        return singleton_instance;
    }

    // TODO Implement ErrorHandler methods for different types of source (input, network, gameplay, etc.)
    public void handleInputException(
            Exception e,
            String errorType,
            String inputType,
            boolean developerMode,
            Consumer<String> statusAction) {
        if(developerMode) {
            e.printStackTrace();
        } else {
            System.err.println(possibleResponses.get(errorType));
            switch (e.getClass().getName()) {
                case "UnknownHostException" -> statusAction.accept("IP address is invalid.");
                case "NumberFormatException" -> statusAction.accept("Port is invalid.");
                default -> System.out.printf("Obj: %s\nName: %s\nClass: %s",
                        e, e.getClass().getName(), e.getClass());
            }
        }
    }
}
