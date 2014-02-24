package dinosaurs;

import dinosaurs.command.Command;
import dinosaurs.command.CommandList;
import dinosaurs.io.Console;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Game {

    private final Console console;
    private final CommandList commandList;

    private Menu menu;

    public void chooseDinosaur() {
        List<Command> commands = commandList.prepareChooseDinosaur();

        menu = new Menu(console, commands);
        menu.displayOptions();
        menu.execute(menu.promptForOptionNumber());
    }

    public void loop() {
        commandList.prepareLoop();

        int choice;
        while (commandList.isAlive()) {
            menu.displayOptions();
            choice = menu.promptForOptionNumber();
            menu.execute(choice);
            commandList.update(choice);
        }
    }
}
