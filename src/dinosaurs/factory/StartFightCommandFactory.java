package dinosaurs.factory;

import dinosaurs.command.Command;
import dinosaurs.command.StartFightCommand;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Player;
import dinosaurs.service.FightDeterminer;

public class StartFightCommandFactory {

    public StartFightCommandFactory() {
    }

    public static StartFightCommand createFight(Console console, FightDeterminer fightDeterminer, Player user, Player opponent) {
        return new StartFightCommand(console, fightDeterminer, user, opponent);
    }

    public static StartFightCommand createUserInitiatedFight(Console console, DinosaurRepository dinoRepo) {
        final Dinosaur opponentDino = DinosaurFactory.createEqualOpponent(dinoRepo.getDinosaur());
        final FightDeterminer fightDeterminer = new FightDeterminer(console);
        final Player user = PlayerFactory.createUserPlayer(dinoRepo.getDinosaur(), console);
        final Player opponent = PlayerFactory.createOpponentPlayer(opponentDino, console);
        return createFight(console, fightDeterminer, user, opponent);
    }

    public static Command createOpponentInitiatedFight(Console console, DinosaurRepository dinoRepo) {
        final Dinosaur opponentDino = DinosaurFactory.createEqualOpponent(dinoRepo.getDinosaur());
        final FightDeterminer fightDeterminer = new FightDeterminer(console);
        final Player user = PlayerFactory.createUserPlayer(dinoRepo.getDinosaur(), console);
        final Player opponent = PlayerFactory.createOpponentPlayer(opponentDino, console);
        return createFight(console, fightDeterminer, opponent, user);
    }
}
