package dinosaurs.model;

import dinosaurs.model.fight_action.FightAction;
import lombok.Getter;

import java.util.List;

public class Dinosaur {
    @Getter
    private final String name;
    @Getter
    private final List<FightAction> fightActions;
    @Getter
    private int exp;
    @Getter
    private int level;
    private final int maxHealth;
    private int health;
    private boolean retreating;
    private boolean blocking;

    public Dinosaur(String dinoName, List<FightAction> fightActions, int exp, int health) {
        this.name = dinoName;
        this.fightActions = fightActions;
        this.exp = exp;
        this.health = health;
        maxHealth = health;
        retreating = false;
        level = 1;
        blocking = false;
    }

    public void incrementExp() {
        incrementExp(1);
    }

    public void incrementExp(int magnitude) {
        int increment = 1;
        exp += increment * magnitude;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean injure(int damage) {
        if (blocking) {
            this.blocking = false;
            return false;
        }
        health -= damage;
        return true;
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

    public int getHealth() {
        health = (health < 0) ? 0 : health;
        return health;
    }

    @Override
    public String toString() {
        return name + "'s stats: \n" +
                "\t health: " + Integer.toString(health) + "\n" +
                "\t experience: " + Integer.toString(exp) + "\n" +
                "\t level: " + Integer.toString(level);

    }

    public void levelUp() {
        level++;
    }

    public void blockAttack() {
        this.blocking = true;
    }

    public void resetBlock() {
        this.blocking = false;
    }
}
