package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String FILE_EXTENSION = ".txt";

    private String directory;

    public Logger(String directory)
    {
        this.directory = directory;
    }

    public static void logMessage(String message)
    {
        File logFile = new File("Users\\Student\\workspace\\module-1-week-4-pair-5\\capstone\\src\\main\\resources\\log.txt");

        try(FileWriter fileWriter = new FileWriter(logFile,true);
            PrintWriter writer = new PrintWriter(fileWriter)
        )
        {
            // open the log file to append and write the message
            // format: <current time> <message>
            String timeStamp = LocalDate.now().toString();
            writer.write(timeStamp + " " + message);
        }
        catch (IOException ex)
        {
            // swallow the exception
        }
    }
}
