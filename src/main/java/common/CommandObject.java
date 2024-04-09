package common;

import java.io.Serializable;

public class CommandObject implements Serializable {
    private static final long serialVersionUID = 1L;
    String argument;
    AbstractCommand command;
    HumanData hd;
    public CommandObject(AbstractCommand command, String arg, HumanData hd){
        this.argument = arg;
        this.command = command;
        this.hd=hd;
    }
    public String deriveCommand(){
        return command + argument;
    }

    @Override
    public String toString() {
        return "CommandObject{" +
                "argument='" + argument + '\'' +
                ", command=" + command +
                '}';
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public HumanData getHd() {
        return hd;
    }

    public void setHd(HumanData hd) {
        this.hd = hd;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
