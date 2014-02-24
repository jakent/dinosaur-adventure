package dinosaurs.command;

import dinosaurs.factory.DinosaurFactory;
import dinosaurs.factory.PlayerFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Fight;
import dinosaurs.model.Player;
import dinosaurs.service.FightDeterminer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StartFightCommand implements Command{

    private final Console console;
    private final FightDeterminer fightDeterminer;
    private final Player user;
    private Player opponent;

    @Override
    public String getName() {
        return "Start Fight";
    }

    @Override
    public void execute() {
        Dinosaur dinosaur = user.getDinosaur();

        Fight fight = fightDeterminer.startFight(user, opponent);
        final Dinosaur winner = fight.getWinner();
        awardVictory(winner);
        if (dinosaur.isRetreating()) {
            dinosaur.standGround();
        }
    }

    public void updateOpponent() {
        Dinosaur dinosaur = user.getDinosaur();
        opponent = PlayerFactory.createOpponentPlayer(DinosaurFactory.createEqualOpponent(dinosaur), console);
    }

    private void awardVictory(Dinosaur winner) {
        winner.incrementExp(5);
        console.print(winner.getName() + " wins the fight!");
    }
}
