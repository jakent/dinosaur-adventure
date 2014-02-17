package dinosaurs.command.state;

import dinosaurs.command.Command;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;

import java.util.List;

public class HasFoodState implements State {
    private final Console console;
    private final DinosaurRepository dinoRepo;
    private final List<Command> commands;

    public HasFoodState(Console console, DinosaurRepository dinoRepo, List<Command> commands) {
        this.console = console;
        this.dinoRepo = dinoRepo;
        this.commands = commands;
    }

    @Override
    public State handle(int lastExecutedCommand) {
        if (lastExecutedCommand == 2) {
            commands.remove(2);
            return new NeedsFoodState(console, dinoRepo, commands);
        }
        return this;
    }
}
