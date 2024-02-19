package view.textInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextInterface {
    private final Map<String, Command> comm = new HashMap<>();

    public void addCommand(Command command) {
        this.comm.put(command.getId(), command);
    }
    private void printMenu() {
        System.out.println("\nMenu:");
        for (Command command : this.comm.values()) {
            System.out.printf("%s: %s%n", command.getId(), command.getDescription());
        }
    }

    public void show() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                this.printMenu();
                System.out.print("Enter an option: ");
                String option = scanner.nextLine();
                Command command = this.comm.get(option);
                if (command == null) {
                    System.out.println("Invalid option!");
                    continue;
                }
                command.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


