package view.textInterface;

import expression.MyException;

public abstract class Command {
    private final String id;
    private final String description;
    public Command(String id, String description) {
        this.id = id;
        this.description = description;
    }


    public String getId() {
        return this.id;
    }

    public Object getDescription() {
        return this.description;
    }

    public abstract void execute() throws MyException;
}
