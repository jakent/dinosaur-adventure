package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;

public class DisplayDinosaurStatsCommand implements Command{

    private final DinosaurRepository dinosaurRepository;
    private final Console console;

    public DisplayDinosaurStatsCommand(Console console, DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
        this.console = console;
    }

    @Override
    public String getName() {
        return "Display dinosaur stats.";
    }

    @Override
    public void execute() {
        final Dinosaur dinosaur = dinosaurRepository.getDinosaur();
        console.print(dinosaur.toString());
    }
}
