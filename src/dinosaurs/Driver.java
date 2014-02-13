package dinosaurs;

import dinosaurs.command.*;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.dal.InMemoryDinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.service.FightDeterminer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Driver {
    public static void main(String[] args){
        Console console = new Console(System.in, System.out);
        DinosaurRepository dinoRepo = new InMemoryDinosaurRepository();
        CreateDinosaurCommand createCommand = new CreateDinosaurCommand(console, dinoRepo);
        ExitGameCommand exitCommand = new ExitGameCommand();
        List<Command> commands = newArrayList(createCommand, exitCommand);

        Menu menu = new Menu(console, commands);

        console.print("Welcome to Dino Destruction!");
        menu.displayOptions();
        menu.execute(menu.promptForOptionNumber());
        commands.remove(createCommand);

        console.print("Now that you have your dinosaur. \n " +
                "It is time for your dinosaur to start gathering food so it will become strong.");
        DisplayDinosaurStatsCommand displayStatsCommand = new DisplayDinosaurStatsCommand(console, dinoRepo);
        GatherFoodCommand gatherFoodCommand = new GatherFoodCommand(console, dinoRepo);
        commands.add(0, displayStatsCommand);
        commands.add(1, gatherFoodCommand);

        Dinosaur opponent = DinosaurFactory.create("opponent", 0);
        final FightDeterminer fightDeterminer = new FightDeterminer(console);
        StartFightCommand startFightCommand = new StartFightCommand(console, dinoRepo, fightDeterminer, opponent);
        commands.add(2, startFightCommand);

        int choice;
        while (true) {
            menu.displayOptions();
            choice = menu.promptForOptionNumber();
            menu.execute(choice);
            opponent.heal();
        }

    }
}
