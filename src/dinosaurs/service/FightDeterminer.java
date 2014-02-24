package dinosaurs.service;

import dinosaurs.Menu;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Fight;
import dinosaurs.model.Player;
import dinosaurs.model.fight_action.FightAction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FightDeterminer {

    private final Console console;

    public Fight startFight(Player user, Player opponent) {
        final Fight fight = new Fight(user.getDinosaur(), opponent.getDinosaur());

        boolean userGoesNext = true;

        while (!fight.isOver()) {
            userGoesNext = nextTurn(user, opponent, userGoesNext);
        }

        return fight;
    }

    private boolean nextTurn(Player userPrompter, Player opponentPrompter, boolean userGoesNext) {
        if (userGoesNext) {
            attackSequence(userPrompter, opponentPrompter.getDinosaur());
            return false;
        }
        attackSequence(opponentPrompter, userPrompter.getDinosaur());
        return true;
    }

    private void attackSequence(Player attackerPrompter, Dinosaur defender) {
        final Dinosaur attacker = attackerPrompter.getDinosaur();
        final Menu menu = attackerPrompter.getMenu();

        menu.displayOptions();
        final int prompt = menu.promptForOptionNumber();
        final FightAction fightAction = attacker.getFightActions().get(prompt);
        fightAction.setFocus(defender);
        menu.execute(prompt);
        printAttackString(attacker, fightAction);
        defender.resetBlock();
    }

    private void printAttackString(Dinosaur dinosaur, FightAction fightAction) {
        console.print(dinosaur.getName() + fightAction.onSuccessMessage());
        console.pause(1);
    }
}
