package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;
import server.*;

import java.util.stream.Stream;

/**
 * Executes the "filter_by_less_than_number_of_dug_counter" command
 */
public class FilterLessDC extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public FilterLessDC(CommandLine cl, CollectionManager cm) {
        super("filter_by_less_than_number_of_dug_counter (integer_value)", "Shows elements with dugCounter value less than entered.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "filter_by_less_than_number_of_dug_counter" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
        try{
            var val = Integer.parseInt(arg.trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");}else{
                StringBuilder str = new StringBuilder();
                Stream st = cm.getCollection().stream().filter(el -> el.getDugCounter()<val);
                if (st.count()==0) return new Feedbacker(">No elements with lower value.");
                return new Feedbacker(str.append(st.map(el -> el.toString()).reduce((a,b)-> a+"\n"+b).get()).append(">Elements shown successfully.").toString());
            }
        } catch(IllegalArgumentException e){return new Feedbacker(false,">Wrong argument.");}
    }
}
