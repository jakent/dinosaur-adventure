package dinosaurs.model.fight_action;

import dinosaurs.factory.DinosaurFactory;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AttackTest {

    public static final int DAMAGE = 20;
    private Attack underTest;
    private String attackName;

    @Before
    public void setup() {
        attackName = "test";
        underTest = new Attack(attackName, DAMAGE);
    }

    @Test
    public void shouldDisplayName() {
        assertTrue(underTest.getName().contains(attackName));
    }

    @Test
    public void shouldDealSpecifiedDamage() {
        final Dinosaur dinosaur = DinosaurFactory.create("john");
        final int initialHealth = dinosaur.getHealth();
        underTest.setFocus(dinosaur);
        underTest.execute();
        assertTrue(dinosaur.getHealth() == initialHealth - DAMAGE);
    }
}
