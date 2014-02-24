package dinosaurs.command.state;

import dinosaurs.command.Command;
import dinosaurs.command.EatFoodCommand;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;

import java.util.List;

public class NeedsFoodState implements State {

    private final Console console;
    private final DinosaurRepository dinoRepo;
    final List<Command> commands;

    public NeedsFoodState(Console console, DinosaurRepository dinoRepo, List<Command> commands) {
        this.console = console;
        this.dinoRepo = dinoRepo;
        this.commands = commands;
    }

    @Override
    public State handle(int lastExecutedCommand) {
        if (lastExecutedCommand == 1) {
            Command eatFoodCommand = new EatFoodCommand(dinoRepo, console);
            commands.add(2, eatFoodCommand);
            return new HasFoodState(console, dinoRepo, commands);
        }
        return this;
    }
}
