package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Fight;
import dinosaurs.service.FightDeterminer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StartFightCommand implements Command{

    private final Console console;
    private final DinosaurRepository dinosaurRepository;
    private final FightDeterminer fightDeterminer;
    private Dinosaur opponent;

    @Override
    public String getName() {
        return "Start Fight";
    }

    @Override
    public void execute() {
        final Dinosaur dinosaur = dinosaurRepository.getDinosaur();
        Fight fight = fightDeterminer.startFight(dinosaur, opponent);
        final Dinosaur winner = fight.getWinner();
        awardVictory(winner);
        if (dinosaur.isRetreating()) {
            dinosaur.standGround();
        }
    }

    public void updateOpponent() {
        opponent = DinosaurFactory.createEqualOpponent(dinosaurRepository.getDinosaur());
    }

    private void awardVictory(Dinosaur winner) {
        winner.incrementExp(5);
        console.print(winner.getName() + " wins the fight!");
    }
}
