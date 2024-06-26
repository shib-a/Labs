package client;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import client.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is an intermediary between the terminal and the user's input
 */
public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1L;
    public String line = "Lab6>";
    public static Scanner fileReader = null;
    public SocketChannel socket;
    public static Scanner defReader = new Scanner(System.in);
    public void print(Object o){
        System.out.print(o);
    }
    public void printLn(Object o){
        System.out.println(o);
    }
    public void printException(Object o){
        System.err.println(">Unexpected exception: " + o);
    }
    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileReader!=null?fileReader:defReader).nextLine();
    }
    public boolean canReadln() throws IllegalStateException {
        return (fileReader!=null?fileReader:defReader).hasNextLine();
    }
    public CommandLine(){}
    public CommandLine(SocketChannel socket){this.socket=socket;}
    public void printF(Object o,Object o2) {
        System.out.printf(o.toString());
        System.out.printf("\t|\t");
        System.out.printf(o2.toString()+"\n");
    }
    public void printLine() {
        print(line);
    }
    public String getLine() {
        return line;
    }

    /**
     * Selects a file scanner for use in execute_script command
     * @param scanner
     */
    public void selectFileScanner(Scanner scanner) {
        this.fileReader = scanner;
    }

    /**
     * Resets a file scanner for taking input from user
     */
    public void selectConsoleScanner() {
        this.fileReader = null;
    }

}
