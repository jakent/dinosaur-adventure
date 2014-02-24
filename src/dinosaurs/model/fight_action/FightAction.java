package dinosaurs.model.fight_action;

import dinosaurs.command.Command;
import dinosaurs.model.Dinosaur;

public interface FightAction extends Command {

    @Override
    String getName();

    @Override
    void execute();

    void setFocus(Dinosaur opponent);

    int getDamage();

    String onSuccessMessage();
}
