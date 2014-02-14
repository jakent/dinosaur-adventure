package dinosaurs.model;

import dinosaurs.factory.DinosaurFactory;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FightTest {

    @Test
    public void shouldDetermineFightEnd() {
        final Dinosaur dinoOne = DinosaurFactory.create("dinoOne");
        final Dinosaur dinoTwo = DinosaurFactory.createWithHealth("dinoTwo", 0);
        final Fight underTest = new Fight(dinoOne, dinoTwo);
        assertTrue(underTest.isOver());
    }

    @Test
    public void shouldDetermineFightInProgress() {
        final Dinosaur dinoOne = DinosaurFactory.create("dinoOne");
        final Dinosaur dinoTwo = DinosaurFactory.create("dinoTwo");
        final Fight underTest = new Fight(dinoOne, dinoTwo);
        assertFalse(underTest.isOver());
    }

    @Test
    public void shouldDetermineIfDinoRetreats() {
        final Dinosaur dinoOne = DinosaurFactory.create("dinoOne");
        dinoOne.retreat();
        final Dinosaur dinoTwo = DinosaurFactory.create("dinoTwo");
        final Fight underTest = new Fight(dinoOne, dinoTwo);
        assertTrue(underTest.isOver());
    }
}
