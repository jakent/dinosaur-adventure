package dinosaurs.model.fight_action;

import dinosaurs.model.Dinosaur;
import lombok.Getter;

public class Attack implements FightAction {
    private final String name;
    private final int damage;
    @Getter
    private Dinosaur opponent;
    private boolean attackSuccessfull;

    public Attack(String attackName, int damage) {
        name = attackName;
        this.damage = damage;
    }

    @Override
    public String getName() {
        return name + " Attack";
    }

    @Override
    public void execute() {
        attackSuccessfull = opponent.injure(damage);
    }

    @Override
    public void setFocus(Dinosaur opponent) {
        this.opponent = opponent;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String onSuccessMessage() {
        String returnStr = " attacked " + opponent.getName() + " with " + name + " for " + damage + " damage.\n";
        if (attackSuccessfull)
            returnStr += opponent.getName() + " has " + opponent.getHealth() + " remaining health.";
        else
            returnStr += opponent.getName() + " blocked the attack and has  " + opponent.getHealth() + " remaining health.";
        return returnStr;
    }
}
