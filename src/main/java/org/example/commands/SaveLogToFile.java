package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
/**
 * Executes the "clear" command
 */
public class SaveLogToFile extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public SaveLogToFile(CommandLine cl, CollectionManager cm) {
        super("save", "Saves collection by writing it into a file ");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "clear" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if (!arg[1].isEmpty()) return new Feedbacker(false, "Wrong argument usage. see 'help' for reference");
        cm.saveToFile();
        return new Feedbacker("Collection saved");
    }
}
