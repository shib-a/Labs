package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;
import server.CommandLine;
import server.CollectionManager;

/**
 * Executes the "remove_by_id" command
 */
public class Remove extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Remove(CommandLine cl, CollectionManager cm) {
        super("remove_by_id (id)", "Removes an element from collection by its id.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "remove_by_id" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
            if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        try{
            var id = Integer.parseInt(arg.trim());
            try{cm.removeById(id, userData);}catch (NullPointerException e){return new Feedbacker(">No element with such id.");}
            return new Feedbacker(">Element removed successfully.");
        } catch(NumberFormatException e){ return new Feedbacker(false,">Wrong argument.");}
    }
}
