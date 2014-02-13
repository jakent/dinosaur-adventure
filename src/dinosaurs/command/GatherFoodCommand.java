package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GatherFoodCommand implements Command{

    private final Console console;
    private final DinosaurRepository dinosaurRepository;

    @Override
    public String getName() {
        return "Gather Food";
    }

    @Override
    public void execute() {
        final Dinosaur dinosaur = dinosaurRepository.getDinosaur();
        console.print(dinosaur.getName() + " is gathering food.");
        console.pause(1);
        console.print(dinosaur.getName() + " is gathering food..");
        console.pause(1);
        console.print(dinosaur.getName() + " is gathering food...");
        console.pause(1);
        console.print(dinosaur.getName() + " is feeling stronger!");

        dinosaur.incrementExp();
    }
}
