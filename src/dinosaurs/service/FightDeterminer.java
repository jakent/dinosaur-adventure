package dinosaurs.service;

import dinosaurs.Menu;
import dinosaurs.OpponentMenu;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Fight;
import dinosaurs.model.fight_action.FightAction;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FightDeterminer {

    public static final int SMARTLY_CHOSEN_ATTACK = 0;
    private Console console;

    public Fight startFight(Dinosaur dinosaur, Dinosaur opponent) {

        final Fight fight = new Fight(dinosaur, opponent);
        final List<FightAction> fightActions = dinosaur.getFightActions();
        final List<FightAction> opponentFightActions = opponent.getFightActions();

        Menu menu;
        while (!fight.isOver()) {
            menu = new Menu(console, fightActions);
            attackSequence(dinosaur, opponent, fightActions, menu);
            if (fight.isOver()) {
                break;
            }
            menu = new OpponentMenu(console, opponentFightActions);
            attackSequence(opponent, dinosaur, opponentFightActions, menu);
        }

        return fight;
    }

    private void attackSequence(Dinosaur dinosaur, Dinosaur opponent, List<FightAction> fightActions, Menu menu) {
        menu.displayOptions();
        final int prompt = menu.promptForOptionNumber();
        final FightAction fightAction = fightActions.get(prompt);
        fightAction.setFocus(opponent);
        menu.execute(prompt);
        printAttackString(dinosaur, fightAction);
    }

    private void printAttackString(Dinosaur dinosaur, FightAction fightAction) {
        console.print(dinosaur.getName() + fightAction.onSuccessMessage());
        console.pause(1);
    }
}
