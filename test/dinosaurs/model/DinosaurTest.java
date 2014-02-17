package dinosaurs.model;

import dinosaurs.factory.DinosaurFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DinosaurTest {

    public static final int TOTAL_HEALTH = 10;
    private Dinosaur underTest;

    @Before
    public void setup() {
        underTest = DinosaurFactory.createWithHealth("test", TOTAL_HEALTH);
    }

    @Test
    public void shouldDetermineIfAlive() {
        underTest = DinosaurFactory.createWithHealth("test", 0);
        assertTrue(underTest.isDead());
        underTest = DinosaurFactory.createWithHealth("test", -10);
        assertTrue(underTest.isDead());
    }

    @Test
    public void shouldInjureDinosaur() {
        assertFalse(underTest.isDead());
        underTest.injure(TOTAL_HEALTH);
        assertTrue(underTest.isDead());
    }

    @Test
    public void shouldHealDinosaur() {
        final int damage = 5;
        underTest.injure(damage);
        assertEquals(underTest.getHealth(), TOTAL_HEALTH - damage);
        underTest.heal();
        assertEquals(underTest.getHealth(), TOTAL_HEALTH);
    }

    @Test
    public void shouldNotHaveHealthBelowZero() {
        underTest = DinosaurFactory.createWithHealth("pawn", -2);
        assertEquals(underTest.getHealth(), 0);
    }

    @Test
    public void shouldBlockAttack() {
        final int initialHealth = underTest.getHealth();
        underTest.blockAttack();
        underTest.injure(3);
        assertTrue(underTest.getHealth() == initialHealth);
        underTest.injure(3);
        assertTrue(underTest.getHealth() == initialHealth - 3);
    }
}
