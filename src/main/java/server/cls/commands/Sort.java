package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import server.*;
/**
 * Class for the "sort" command
 */
public class Sort extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Sort(CommandLine cl, CollectionManager cm) {
        super("sort", "Sorts collection by default (in this case by DAMAGE stat values).");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "sort" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg) {
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
            cm.getCollection().sort(new HumanComparator());
            return new Feedbacker(">Sorted successfully.");
    }
}
