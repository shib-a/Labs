package client.collection;
import client.classes.Human;
import org.apache.commons.csv.*;
import client.CommandLine;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * This class manages loading and saving the collection from and into a file
 */
public class CollectionLoaderSaver implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileName;
    private CommandLine cl;
    public CollectionLoaderSaver(String fileName, CommandLine cl){this.fileName = fileName;this.cl = cl;}

    /**
     * Turns a collection into an array of CSV-formatted strings
     * @param col
     * @return String[]
     */
    public String[] colToCsvArr(Collection<Human> col){
        String[] arr = new String[col.size()];
        try{
            int i = 0;
            for(Human h:col){
//                arr[i]=h.toCsvStr();
                i++;
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e){}
        return arr;
    }

    /**
     * Writes CSV-formatted strings with human field values into a file
     * @param col
     */
    public void writeToFile(Collection<Human> col){
        BufferedOutputStream wr = null;
        String[] data = colToCsvArr(col);
        if(data==null){return;}
        try {
            FileOutputStream fls = new FileOutputStream(fileName);
            wr = new BufferedOutputStream(fls);
            try{
                for(String el:data) {
                    wr.write(el.getBytes());
                    wr.write("\n".getBytes());
                }
                wr.flush();
                System.out.println(">Collection saved.");
            } catch (NullPointerException | IOException e){return;}
        }catch (IOException e){}
    }

    /**
     * Parses a CSV file, turns extracted values to Human instances and adds them to a collection
     * @param fileName
     * @return ArrayList<Human></>
     */
    public ArrayList<Human> readFromFile(String fileName){
        ArrayList<Human> col = new ArrayList<>();
        if (!(fileName == null) && !fileName.isEmpty()){
            try{
                FileInputStream f = new FileInputStream(fileName);
                BufferedInputStream is = new BufferedInputStream(f);
                CSVParser csvp = CSVParser.parse(is,StandardCharsets.UTF_8,CSVFormat.DEFAULT);
                for(CSVRecord line:csvp){
                    if (!(line.values().length>=11 && line.values().length<=19)) throw new ArrayIndexOutOfBoundsException();
                    try{
                        var st = line.values();
                        String str = "";
                        Human newHuman;
                        for(int i = 0; i<st.length;i++){
                            str+=line.values()[i]+",";
                        }
                        str = str.substring(0, str.lastIndexOf(","));
                        newHuman = Human.fromCsvStr(str);
                        if (newHuman.validate()){col.add(newHuman);} else {cl.printException(">Invalid data in the collection file.");};
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException | NoSuchElementException e){
                        System.out.println(">Deserialization error: "+e.getMessage());
                    }
                }
            } catch (IOException | ArrayIndexOutOfBoundsException e){
                if(e.getClass()==ArrayIndexOutOfBoundsException.class){
                    System.out.println(">Something's wrong with the amount of values. Please check the values in the file.");
                }
            }

        }
        return col;
    }
    public void setFileName(String fn){
        this.fileName = fn;
    }
    public String getFileName(){return fileName;}

}
