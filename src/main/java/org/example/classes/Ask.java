package org.example.classes;

import org.example.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.example.classes.Human;

import static org.example.commands.RuntimeEnv.addToLog;

/**
 * This class Asks user/script for arguments to create a Human instance
 */
public class Ask{
    /**
     * This class stops the asking process
     */
    public static class AskBreaker extends Exception{}
    /**
     * This method creates a Human instance using a series of user/scripted inputs using a Human constructor
     * @return Human
     * @throws AskBreaker
     */
    public static Human askHuman(CommandLine cl, int id) throws AskBreaker{
        try{
            String name;
            while (true){
                cl.print("enter name: ");
                name = cl.readln().trim();
                if(name.equals("exit")) throw new AskBreaker();
                addToLog(name);
                if(!name.isEmpty()) break;
            }
            ToolKinds ptt = askPreferredTool(cl);
            ResearcherType rt = askResType(cl);
            Double[] stats = askStats(cl);
            boolean ia = askIsAlive(cl);
            int dc = askDugCounter(cl);
            return new Human(id,name, ptt, rt, ia,stats[0],stats[1],stats[2], stats[3], stats[4],dc);
        } catch (NoSuchElementException e){
            System.out.println(">Failed to read");
            return null;
        }
    }

    /**
     *Asks user/script for a preferredTool value
     * @return preferredTool
     * @throws AskBreaker
     */
    public static ToolKinds askPreferredTool(CommandLine cl) throws AskBreaker {
        try {
            ToolKinds ptt;
            while (true) {
                cl.print("Enter favourite tool: (" + Arrays.toString(ToolKinds.values()).replace("[","").replace("]","") + ")");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty()) {
                    try {
                        ptt = ToolKinds.valueOf(line);
                        addToLog(line);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(">Wrong tool value. Try again or enter 'exit' to stop the process.");
                    }
                } else return null;
            }
            return ptt;
        } catch (NoSuchElementException e) {
            cl.printException(">Failed to read");
            return null;
        }
    }
    /**
     *Asks user/script for a researcherType value
     * @return researcherType
     * @throws AskBreaker
     */
    public static ResearcherType askResType(CommandLine cl) throws AskBreaker {
        try{
            ResearcherType rt;
            while(true){
                cl.print("Enter researcher type: (" + Arrays.toString(ResearcherType.values()).replace("[","").replace("]","") +")");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty() && !line.isBlank()){
                    try{
                        rt = ResearcherType.valueOf(line);
                        addToLog(line);
                        break;
                    } catch (IllegalArgumentException e){System.out.println(">Wrong researcher type value. Try again or enter 'exit' to stop the process.");}
                } else return null;
            }
            return rt;
        } catch (NoSuchElementException e){
            cl.printException(">Failed to read");
            return null;
        }
    }
    /**
     *Asks user/script for a boolean isAlive value
     * @return isAlive
     * @throws AskBreaker
     */
    public static Boolean askIsAlive(CommandLine cl) throws AskBreaker {
        while (true) {
            try {
                cl.print("Enter whether the object is alive: true for alive or false for not alive:");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty() && !line.isBlank()) {
                    try {
                        if (line.trim().equals("true")) {addToLog(line);return true;} else if (line.trim().equals("false")){addToLog(line);return false;} else System.out.println(">Wrong value. Try again (enter true/false) or enter 'exit' to stop the process.");
                    } catch (IllegalArgumentException | NullPointerException e) {System.out.println(">Wrong value. Try again (enter true/false) or enter 'exit' to stop the process.");}
                }else System.out.println(">Wrong value. Try again (enter true/false) or enter 'exit' to stop the process.");
            }catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(">Failed to read. Set default value, false.");
        }
        }
    }
    /**
     *Asks user/script for stat values
     * @return stats[]
     * @throws AskBreaker
     */
    public static Double[] askStats(CommandLine cl) throws AskBreaker {
        while (true){
            Double[] mas = new Double[5];
            try{
                cl.print("Enter the five stats in following format: x.x,y.y,etc... ");
                String line = cl.readln().trim();
                if(line.equals("exit")) throw new AskBreaker();
                if(!line.isBlank() && !line.isEmpty()) {
                    int i = 0;
                    String[] ls = line.split(",");
                    if(ls.length!=0){
                    for (String entry : line.split(",")) {
                        if (!entry.isEmpty() && !entry.isBlank()){
                        try {
                            mas[i] = Double.parseDouble(entry);
                            i++;
                        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                            System.out.println(">Wrong value. Try again (enter e.g 50.0,30.0,10.0,15.0,60.0) or enter 'exit' to stop the process.");
                        }}else throw new NullPointerException();}
                    }else throw new NullPointerException();
                    addToLog(line);
                    return mas;
                } else throw new NullPointerException();
            }catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e){
                System.out.println(">Failed to read");
            }
//            System.out.println(">Wrong value. Try again (enter e.g 50.0,30.0,10.0,15.0,60.0) or enter 'exit' to stop the process.");
        }
    }
    /**
     *Asks user/script for a dugCounter value
     * @return dugCounter
     * @throws AskBreaker
     */
    public static Integer askDugCounter(CommandLine cl) throws AskBreaker {
        while (true) {
            cl.print("Enter the dug_counter value: ");
            String line = cl.readln().trim();
            if (line.equals("exit")) throw new AskBreaker();
            if (!line.isBlank() && !line.isEmpty()) {
                try {
                    var i = Integer.parseInt(line);
                    addToLog(line);
                    return i;
                } catch (NumberFormatException e) {
                    System.out.println(">Wrong argument");
                }
            } else return 0;
        }
    }
}