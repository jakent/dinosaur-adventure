package dinosaurs.factory;

import com.google.common.collect.Lists;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.fight_action.Attack;
import dinosaurs.model.fight_action.FightAction;
import dinosaurs.model.fight_action.Retreat;

import java.util.List;

public class DinosaurFactory {
    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_DAMAGE = 30;

    private static FightAction defaultAttack = new Attack("Slash", DEFAULT_DAMAGE);
    private static Dinosaur dinosaur;
    private static FightAction retreat;

    public static Dinosaur create(String dinoName) {
        dinosaur = new Dinosaur(dinoName, Lists.newArrayList(defaultAttack), 0, DEFAULT_HEALTH);
        retreat = new Retreat(dinosaur);
        dinosaur.addFightAction(retreat);
        return dinosaur;
    }

    public static Dinosaur createWithExp(String dinoName, int exp) {
        dinosaur = new Dinosaur(dinoName, Lists.newArrayList(defaultAttack), exp, DEFAULT_HEALTH);
        retreat = new Retreat(dinosaur);
        dinosaur.addFightAction(retreat);
        return dinosaur;
    }

    public static Dinosaur create(String dinoName, List<FightAction> fightActions) {
        return new Dinosaur(dinoName, fightActions, 0, DEFAULT_HEALTH);
    }

    public static Dinosaur createWithHealth(String dinoName, int health) {
        dinosaur = new Dinosaur(dinoName, Lists.newArrayList(defaultAttack), 0, health);
        retreat = new Retreat(dinosaur);
        dinosaur.addFightAction(retreat);
        return dinosaur;

    }
}
