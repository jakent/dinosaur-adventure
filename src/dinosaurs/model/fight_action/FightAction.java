package dinosaurs.model.fight_action;

import dinosaurs.command.Command;
import dinosaurs.model.Dinosaur;

public interface FightAction extends Command {

    @Override
    public String getName();

    @Override
    public void execute();

    public void setFocus(Dinosaur opponent);

    public int getDamage();
}
