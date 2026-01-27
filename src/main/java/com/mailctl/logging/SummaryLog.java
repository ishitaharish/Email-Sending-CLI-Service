package com.mailctl.logging;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SummaryLog {

    private final String logFile;

    public SummaryLog(String logFile) {
        this.logFile = logFile;
    }

    public void log(String email, boolean success) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.println(email + " | " + (success ? "SENT" : "FAILED") + " | " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSummary(int sentCount, int total) {
        System.out.println("Finished sending emails. Total sent: " + sentCount + "/" + total);
    }
}
