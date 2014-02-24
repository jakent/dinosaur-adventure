package dinosaurs.command;

import dinosaurs.command.state.NeedsFoodState;
import dinosaurs.command.state.State;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.StartFightCommandFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.fight_action.Block;
import dinosaurs.model.fight_action.FightAction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

@RequiredArgsConstructor
public class CommandList {

    private final Console console;
    private final DinosaurRepository dinoRepo;

    private List<Command> commands;
    private Command createCommand;
    private State currentState;
    private StartFightCommand startFightCommand;


    public List<Command> prepareChooseDinosaur() {
        createCommand = new CreateDinosaurCommand(console, dinoRepo);
        Command exitCommand = new ExitGameCommand();

        return commands = newArrayList(createCommand, exitCommand);
    }

    public void prepareLoop() {
        commands.remove(createCommand);

        Command displayStatsCommand = new DisplayDinosaurStatsCommand(console, dinoRepo);
        Command gatherFoodCommand = new GatherFoodCommand(console, dinoRepo, new Random());
        startFightCommand = StartFightCommandFactory.createUserInitiatedFight(console, dinoRepo);

        commands.add(0, displayStatsCommand);
        commands.add(1, gatherFoodCommand);
        commands.add(2, startFightCommand);

        currentState = new NeedsFoodState(console, dinoRepo, commands);
    }

    public void update(int lastExecutedCommand) {
        startFightCommand.updateOpponent();
        currentState = currentState.handle(lastExecutedCommand);
        checkIfLeveled();
    }

    private void checkIfLeveled() {
        final Dinosaur dinosaur = dinoRepo.getDinosaur();
        if (dinosaur.getExp() >= 10 && dinosaur.getLevel() == 1) {
            dinosaur.levelUp();
            console.print("Congratulations! " + dinosaur.getName() + " has leveled to level " + dinosaur.getLevel() + ".");
            console.pause(1);
            FightAction block = new Block(dinosaur);
            dinosaur.addFightAction(block);
            console.print(dinosaur.getName() + " has learned a new attack: " + block.getName());
        }
    }

    public boolean isAlive() {
        return !dinoRepo.getDinosaur().isDead();
    }
}
