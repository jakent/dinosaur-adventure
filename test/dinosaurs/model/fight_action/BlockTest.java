package dinosaurs.model.fight_action;

import dinosaurs.factory.DinosaurFactory;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BlockTest {

    private Block underTest;
    private Dinosaur user;

    @Before
    public void setup() {
        user = DinosaurFactory.create("user");
        underTest = new Block(user);
    }

    @Test
    public void shouldDisplayName() {
        assertTrue(underTest.getName().contains("Block"));
    }

    @Test
    public void shouldBlockOpponentsAttack() {
        final int initialHealth = user.getHealth();
        final Dinosaur dinosaur = DinosaurFactory.create("opponent");
        underTest.execute();
        final FightAction opponentAttack = dinosaur.getFightActions().get(0);
        opponentAttack.setFocus(user);
        opponentAttack.execute();
        assertTrue(user.getHealth() == initialHealth);
    }
}
