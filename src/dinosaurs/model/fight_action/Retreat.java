package dinosaurs.model.fight_action;

import dinosaurs.model.Dinosaur;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class Retreat implements FightAction {

    private Dinosaur dinosaur;

    @Override
    public String getName() {
        return "Retreat";
    }

    @Override
    public void execute() {
        dinosaur.retreat();
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
        return " retreated like a coward.";
    }
}
