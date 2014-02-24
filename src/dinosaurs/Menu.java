package dinosaurs;

import dinosaurs.command.Command;
import dinosaurs.io.Console;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Menu {
    private Console console;
    private List<? extends Command> options;

    public void displayOptions() {
        console.print("\nPlease choose from the following options:");

        int index = 1;
        for (Command command: options) {
            console.print(index++ + ". " + command.getName());
        }
    }

    //TODO: return command, not int
    public int promptForOptionNumber() {
        int input;
        input = console.ask();
        while(isInvalidOption(input)){
            console.print("Index out of bounds.");
            input = console.ask();
        }
        return input - 1;
    }

    private boolean isInvalidOption(int option) {
        return option < 1 || option > options.size();
    }

    public void execute(int mappedInput) {
        options.get(mappedInput).execute();
    }
}
