package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;
import server.*;

/**
 * Class for the "exit" command
 */
public class Exit extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Exit(CommandLine cl, CollectionManager cm) {
        super("exit", "Stops the program without saving the collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "exit" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        cm.saveToFile();
        return new Feedbacker("exit");
    }
}
