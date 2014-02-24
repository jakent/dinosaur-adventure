package dinosaurs.factory;

import dinosaurs.Menu;
import dinosaurs.OpponentMenu;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Player;

public class PlayerFactory {

    public PlayerFactory() {
    }

    public static Player createUserPlayer(Dinosaur dinosaur, Console console) {
        return new Player(dinosaur, new Menu(console, dinosaur.getFightActions()));
    }

    public static Player createOpponentPlayer(Dinosaur dinosaur, Console console) {
        return new Player(dinosaur, new OpponentMenu(console, dinosaur.getFightActions()));
    }
}
