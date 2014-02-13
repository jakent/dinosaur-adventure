package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateDinosaurCommand implements Command {

    private final Console console;
    private final DinosaurRepository dinoRepo;

    @Override
    public String getName() {
        return "Add Dinosaur";
    }

    @Override
    public void execute() {
        String input = console.prompt("Enter the name of your dinosaur:");
        dinoRepo.createDinosaur(DinosaurFactory.create(input));
        console.print("You have created a dinosaur named \"" + input + "\"");

    }
}
