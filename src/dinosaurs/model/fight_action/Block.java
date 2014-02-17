package dinosaurs.model.fight_action;

import dinosaurs.model.Dinosaur;

public class Block implements FightAction{

    private final Dinosaur user;

    public Block(Dinosaur user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return "Block";
    }

    @Override
    public void execute() {
        user.blockAttack();
    }

    @Override
    public void setFocus(Dinosaur opponent) {
        //Do nothing
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String onSuccessMessage() {
        return " is waiting to block the next attack.";
    }
}
