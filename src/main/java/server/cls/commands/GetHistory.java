package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import client.CommandLine;
import common.UserData;

/**
 * Executes the "get_history" command
 */
public class GetHistory extends AbstractCommand {
    private CommandLine cl;
    private CommandManager com;
    public GetHistory(CommandLine cl, CommandManager com) {
        super("get_history", "Shows entered commands.");
        this.cl = cl;
        this.com=com;
    }
    /**
     * Executes the "get_history" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        cl.printLn(com.getCommandHistory());
        return new Feedbacker(">Showed successfully.");
    }
}
