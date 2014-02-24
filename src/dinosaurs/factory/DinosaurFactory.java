package dinosaurs.factory;

import dinosaurs.model.Dinosaur;
import dinosaurs.model.fight_action.Attack;
import dinosaurs.model.fight_action.FightAction;
import dinosaurs.model.fight_action.Retreat;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DinosaurFactory {
    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_DAMAGE = 30;

    private static final FightAction defaultAttack = new Attack("Slash", DEFAULT_DAMAGE);

    public DinosaurFactory() {
    }

    public static Dinosaur create(String dinoName) {
        final Dinosaur dinosaur = new Dinosaur(dinoName, newArrayList(defaultAttack), 0, DEFAULT_HEALTH);
        final Retreat retreat = new Retreat(dinosaur);
        dinosaur.addFightAction(retreat);
        return dinosaur;
    }

    public static Dinosaur createWithExp(String dinoName, int exp) {
        final Dinosaur dinosaur = new Dinosaur(dinoName, newArrayList(defaultAttack), exp, DEFAULT_HEALTH);
        final Retreat retreat = new Retreat(dinosaur);
        dinosaur.addFightAction(retreat);
        return dinosaur;
    }

    public static Dinosaur createWithFightActions(String dinoName, List<FightAction> fightActions) {
        return new Dinosaur(dinoName, fightActions, 0, DEFAULT_HEALTH);
    }

    public static Dinosaur createWithHealth(String dinoName, int health) {
        final Dinosaur dinosaur = new Dinosaur(dinoName, newArrayList(defaultAttack), 0, health);
        final Retreat retreat = new Retreat(dinosaur);
        dinosaur.addFightAction(retreat);
        return dinosaur;
    }

    public static Dinosaur createEqualOpponent(Dinosaur userDino) {
        final ArrayList<FightAction> actions = newArrayList(userDino.getFightActions());
        final Dinosaur dinosaur = new Dinosaur("Doctor Opponent", actions, userDino.getExp(), 100);
        final Retreat userDinoRetreat = new Retreat(userDino);
        if (userDino.getFightActions().contains(userDinoRetreat)) {
            dinosaur.getFightActions().remove(userDinoRetreat);
            final Retreat retreat = new Retreat(dinosaur);
            dinosaur.addFightAction(retreat);
        }
        return dinosaur;
    }
}
