package view.textInterface;

import controller.Controller;
import expression.MyException;

import java.io.IOException;

public class RunExampleCommand extends Command {
    private final Controller controller;

    public RunExampleCommand(String id, String description, Controller controller) {
        super(id, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            this.controller.allStep();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }
}

