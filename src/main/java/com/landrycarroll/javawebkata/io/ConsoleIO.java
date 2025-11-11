package com.landrycarroll.javawebkata.io;


import java.util.Scanner;

public class ConsoleIO implements UserIO {
    private final Scanner console;

    public ConsoleIO() {
        this.console = new Scanner(System.in);
    }

    @Override
    public String readInput(String prompt) {
        writeOutput(prompt);
        return console.nextLine();
    }

    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }

    @Override
    public void sendError(String output) {

    }
}
