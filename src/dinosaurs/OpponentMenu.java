package dinosaurs;

import dinosaurs.io.Console;
import dinosaurs.model.fight_action.FightAction;

import java.util.List;
import java.util.Random;

public class OpponentMenu extends Menu {
    private final List<FightAction> fightActions;

    public OpponentMenu(Console console, List<FightAction> fightActions) {
        super(console, fightActions);
        this.fightActions = fightActions;
    }

    @Override
    public int promptForOptionNumber() {
        Random generator = new Random();
        return generator.nextInt(fightActions.size());
    }

    @Override
    public void displayOptions() {
        //Do nothing
    }
}
