package dinosaurs.model;

import dinosaurs.model.fight_action.FightAction;
import lombok.Data;

import java.util.List;

@Data
public class Dinosaur {
    private int increment = 1;

    private final String name;
    private final List<FightAction> fightActions;
    private int exp;
    private int maxHealth;
    private int health;
    private boolean retreating;

    public Dinosaur(String dinoName, List<FightAction> fightActions, int exp, int health) {
        //TODO: lombok should do this for us
        this.name = dinoName;
        this.fightActions = fightActions;
        this.exp = exp;
        this.health = health;
        maxHealth = health;
        retreating = false;
    }

    public void incrementExp() {
        incrementExp(1);
    }

    public void incrementExp(int magnitude) {
        exp += increment * magnitude;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void injure(int damage) {
        health -= damage;
    }

    public void heal() {
        health = maxHealth;
    }

    public boolean isRetreating() {
        return retreating;
    }

    public void retreat() {
        retreating = true;
    }

    public void standGround() {
        retreating = false;
    }

    public void addFightAction(FightAction action) {
        fightActions.add(action);
    }

    @Override
    public String toString() {
        return name + "'s stats: \n" +
                "\t health: " + Integer.toString(health) + "\n" +
                "\t exp: " + Integer.toString(exp);

    }
}
