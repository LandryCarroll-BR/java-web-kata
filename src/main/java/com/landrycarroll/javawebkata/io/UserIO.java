package com.landrycarroll.javawebkata.io;

public interface UserIO {
    String readInput(String prompt);

    void writeOutput(String output);

    void sendError(String output);
}
