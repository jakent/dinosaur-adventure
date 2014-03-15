package dinosaurs.command.state;

import dinosaurs.command.Command;
import dinosaurs.command.EatFoodCommand;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class NeedsFoodState implements State {

    private final Console console;
    private final DinosaurRepository dinoRepo;
    final List<Command> commands;

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
