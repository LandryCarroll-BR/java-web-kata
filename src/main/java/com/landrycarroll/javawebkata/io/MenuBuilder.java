package com.landrycarroll.javawebkata.io;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuBuilder {
    private final UserIO io;
    private final Map<String, Runnable> menuOptions = new LinkedHashMap<>();


    public MenuBuilder(UserIO io) {
        this.io = io;
    }

    public MenuBuilder addOption(String option, Runnable controller) {
        menuOptions.put(option, controller);
        return this;
    }

    public void build() {
        io.writeOutput("\n ====== | Main Menu | ======");

        while (true) {
            int index = 1;

            // Display options
            for (String option : menuOptions.keySet()) {
                io.writeOutput(index + ". " + option);
                index++;
            }

            // Display Exit option
            io.writeOutput(index + ". Exit");

            // Get user input
            String input = io.readInput("Choose an option: 1-" + index + " ");

            // Exit if last option is selected
            if (input.equals(String.valueOf(index))) {
                break;
            }

            // Attempt to handle the user's input
            handleInput(input);
        }
    }

    private void handleInput(String input) {
        try {
            if (input == null || input.isBlank()) {
                throw new IllegalArgumentException("Must provide an option");
            }

            int parsedInput = Integer.parseInt(input);

            if (parsedInput <= 0 || parsedInput > menuOptions.size()) {
                throw new IllegalArgumentException("Please provide a valid option");
            }

            int index = parsedInput - 1;
            String option = menuOptions.keySet().toArray()[index].toString();
            Runnable controller = menuOptions.get(option);

            controller.run();

        } catch (Exception e) {
            io.writeOutput("Invalid option: " + input + ". " + e.getMessage());
        }
    }
}
