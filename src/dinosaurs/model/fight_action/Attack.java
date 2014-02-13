package dinosaurs.model.fight_action;

import dinosaurs.model.Dinosaur;

public class Attack implements FightAction {
    private final String name;
    private final int damage;
    private Dinosaur opponent;

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
        opponent.injure(damage);
    }

    @Override
    public void setFocus(Dinosaur opponent) {
        this.opponent = opponent;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
