package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    File log = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\src\\main\\resources\\Log.txt");
    boolean append = log.exists();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    String dateText = LocalDateTime.now().format(formatter);

    public void log(String loggedProcess) {
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(log, append))) {
            logWriter.println(dateText + " " + loggedProcess);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
