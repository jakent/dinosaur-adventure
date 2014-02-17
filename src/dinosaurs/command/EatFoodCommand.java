package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EatFoodCommand implements Command {

    private final DinosaurRepository dinoRepo;
    private final Console console;

    @Override
    public String getName() {
        return "Eat Food";
    }

    @Override
    public void execute() {
        final Dinosaur dinosaur = dinoRepo.getDinosaur();
        console.print(dinosaur.getName() + " is eating. ** munch **");
        console.pause(1);
        console.print(dinosaur.getName() + " is eating.. ** munch ** munch **");
        console.pause(1);
        console.print(dinosaur.getName() + " is eating... ** munch ** munch ** munch **");
        dinosaur.heal();
        console.pause(1);
        console.print(dinosaur.getName() + " is feeling stronger!");
    }
}
