package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DisplayDinosaurStatsCommand implements Command{

    private final Console console;
    private final DinosaurRepository dinosaurRepository;

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
