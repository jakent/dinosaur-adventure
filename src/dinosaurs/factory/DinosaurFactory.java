package dinosaurs.factory;

import com.google.common.collect.Lists;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.fight_action.Attack;
import dinosaurs.model.fight_action.FightAction;

import java.util.List;

public class DinosaurFactory {
    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_DAMAGE = 30;

    private static FightAction defaultFightAction = new Attack("Slash", DEFAULT_DAMAGE);

    public static Dinosaur create(String dinoName) {
        return new Dinosaur(dinoName, Lists.newArrayList(defaultFightAction), 0, DEFAULT_HEALTH);
    }

    public static Dinosaur create(String dinoName, int exp) {
        return new Dinosaur(dinoName, Lists.newArrayList(defaultFightAction), exp, DEFAULT_HEALTH);
    }

    public static Dinosaur create(String dinoName, List<FightAction> fightActions) {
        return new Dinosaur(dinoName, fightActions, 0, DEFAULT_HEALTH);
    }

    public static Dinosaur createWithHealth(String dinoName, int health) {
        return new Dinosaur(dinoName, Lists.newArrayList(defaultFightAction), 0, health);
    }
}
