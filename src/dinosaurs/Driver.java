package dinosaurs;

import dinosaurs.command.CommandList;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.dal.InMemoryDinosaurRepository;
import dinosaurs.io.Console;

public class Driver {
    public static void main(String[] args){
        final Console console = new Console(System.in, System.out);
        final DinosaurRepository dinosaurRepository = new InMemoryDinosaurRepository();
        final CommandList commandList = new CommandList(console, dinosaurRepository);

        final Game game = new Game(console, commandList);

        console.print("Welcome to Dino Destruction!");

        game.chooseDinosaur();
        final String dinoName = dinosaurRepository.getDinosaur().getName();

        console.print("Now that you have " + dinoName + ".\n" +
                "It is time " + dinoName + " to start gathering food so it will become strong.");

        game.loop();

        console.print(dinoName + " has died.\n" +
                "Game Over");
    }
}
