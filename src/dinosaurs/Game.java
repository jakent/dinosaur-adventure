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

public class Game {

    private Console console;
    private Menu menu;
    private List<Command> commands;
    private DinosaurRepository dinoRepo;

    public Game(Console console) {
        this.console = console;
    }

    public void chooseDinosaur() {
        dinoRepo = new InMemoryDinosaurRepository();
        Command createCommand = new CreateDinosaurCommand(console, dinoRepo);
        Command exitCommand = new ExitGameCommand();

        commands = newArrayList(createCommand, exitCommand);

        menu = new Menu(console, commands);
        menu.displayOptions();
        menu.execute(menu.promptForOptionNumber());

        commands.remove(createCommand);
    }


    public void loop() {
        Command displayStatsCommand = new DisplayDinosaurStatsCommand(console, dinoRepo);
        Command gatherFoodCommand = new GatherFoodCommand(console, dinoRepo);
        StartFightCommand startFightCommand = setupFightCommand();

        commands.add(0, displayStatsCommand);
        commands.add(1, gatherFoodCommand);
        commands.add(2, startFightCommand);

        int choice;
        while (true) {
            menu.displayOptions();
            choice = menu.promptForOptionNumber();
            menu.execute(choice);
            startFightCommand.update();
        }
    }

    private StartFightCommand setupFightCommand(){
        Dinosaur opponent = DinosaurFactory.createWithExp("opponent", 0);
        final FightDeterminer fightDeterminer = new FightDeterminer(console);
        return new StartFightCommand(console, dinoRepo, fightDeterminer, opponent);
    }
}
