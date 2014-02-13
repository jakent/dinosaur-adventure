package dinosaurs.service;

import dinosaurs.Menu;
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

        Fight fight = new Fight(dinosaur, opponent);
        List<FightAction> fightActions = dinosaur.getFightActions();

        List<FightAction> opponentFightActions = opponent.getFightActions();

        while (!fight.isOver()) {
            userAttackSequence(dinosaur, opponent, fightActions);
            if (fight.isOver()) {
                break;
            }
            opponentAttackSequence(dinosaur, opponent, opponentFightActions);
        }

        return fight;
    }

    private void userAttackSequence(Dinosaur dinosaur, Dinosaur opponent, List<FightAction> fightActions) {
        Menu menu = new Menu(console, fightActions);
        menu.displayOptions();
        final int prompt = menu.promptForOptionNumber();
        final FightAction fightAction = fightActions.get(prompt);
        fightAction.setFocus(opponent);
        menu.execute(prompt);
        printAttackString(dinosaur, opponent, fightAction);
    }

    private void opponentAttackSequence(Dinosaur dinosaur, Dinosaur opponent, List<FightAction> opponentFightActions) {
        final FightAction opponentFightAction = opponentFightActions.get(SMARTLY_CHOSEN_ATTACK);
        opponentFightAction.setFocus(dinosaur);
        opponentFightAction.execute();
        printAttackString(opponent, dinosaur, opponentFightAction);
    }

    private void printAttackString(Dinosaur dinosaur, Dinosaur opponent, FightAction fightAction) {
        console.print(dinosaur.getName() + " attacked " + opponent.getName() + " with " + fightAction.getName() + " for " + fightAction.getDamage() + " damage.");
        console.print(opponent.getName() + " has " + opponent.getHealth() + " remaining health.");
        console.pause(1);
    }
}
