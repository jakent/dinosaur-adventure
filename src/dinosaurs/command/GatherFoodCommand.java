package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.StartFightCommandFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class GatherFoodCommand implements Command{

    private final Console console;
    private final DinosaurRepository dinosaurRepository;
    private final Random generator;

    @Override
    public String getName() {
        return "Gather Food";
    }

    @Override
    public void execute() {
        final Dinosaur dinosaur = dinosaurRepository.getDinosaur();
        if (randomTwentyPercentChance()) {
            getAttacked(dinosaur);
        } else {
            console.print(dinosaur.getName() + " is gathering food.");
            console.pause(1);
            console.print(dinosaur.getName() + " is gathering food..");
            console.pause(1);
            console.print(dinosaur.getName() + " is gathering food...");

            dinosaur.incrementExp();
        }
    }

    private void getAttacked(Dinosaur dinosaur) {
        console.print(dinosaur.getName() + " has been attacked while gathering food!");
        console.pause(1);
        Command startFightCommand = StartFightCommandFactory.createOpponentInitiatedFight(console, dinosaurRepository);
        startFightCommand.execute();
    }

    private boolean randomTwentyPercentChance() {
        return generator.nextInt(4) == 0;
    }

}
