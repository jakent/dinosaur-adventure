package dinosaurs.model.fight_action;

import dinosaurs.factory.DinosaurFactory;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RetreatTest {

    private Retreat underTest;
    private Dinosaur dinosaur;

    @Before
    public void setup() {
        dinosaur = DinosaurFactory.create("Hue");
        underTest = new Retreat(dinosaur);
    }

    @Test
    public void shouldDisplayName() {
        assertTrue(underTest.getName().contains("Retreat"));
    }

    @Test
    public void shouldExitBattleSequence() {
        underTest.execute();
        assertTrue(dinosaur.isRetreating());
    }
}
