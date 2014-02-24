package dinosaurs.factory;

import dinosaurs.model.Dinosaur;
import dinosaurs.model.fight_action.Attack;
import dinosaurs.model.fight_action.FightAction;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DinosaurFactoryTest {

    private Dinosaur dino;

    @Before
    public void setup() {
        dino = DinosaurFactory.create("Triceratops");
    }

    @Test
    public void shouldCreateDinosaurWithName() {
        assertEquals(dino.getName(), "Triceratops");
    }

    @Test
    public void shouldCreateDinosaurWithInitialStats() {
        assertEquals(dino.getExp(), 0);
    }

    @Test
    public void shouldCreateEqualOpponent() {
        dino.incrementExp(10);
        final Dinosaur equalOpponent = DinosaurFactory.createEqualOpponent(dino);
        assertEquals(dino.getExp(), equalOpponent.getExp());
        assertEquals(dino.getFightActions().size(), equalOpponent.getFightActions().size());
    }

    @Test
    public void shouldCreateEqualOpponentWithFullHealth() {
        dino.injure(10);
        final Dinosaur equalOpponent = DinosaurFactory.createEqualOpponent(dino);
        assertEquals(100, equalOpponent.getHealth());
    }

    @Test
    public void shouldCreateEqualOpponentWithOneAttack() {
        final FightAction attack = new Attack("breath of fire", 9000);
        dino = DinosaurFactory.createWithFightActions("Triceratops", newArrayList(attack));
        final Dinosaur equalOpponent = DinosaurFactory.createEqualOpponent(dino);
        assertEquals(dino.getFightActions().size(), equalOpponent.getFightActions().size());
        assertEquals(dino.getFightActions().get(0).getDamage(), equalOpponent.getFightActions().get(0).getDamage());
        assertEquals(dino.getFightActions().get(0).getName(), equalOpponent.getFightActions().get(0).getName());
    }

    @Test
    public void shouldCreateEqualOpponentWithRetreatInitializedCorrectly() {
        final Dinosaur equalOpponent = DinosaurFactory.createEqualOpponent(dino);
        assertEquals(dino.getFightActions().size(), equalOpponent.getFightActions().size());
        final FightAction retreat = equalOpponent.getFightActions().get(equalOpponent.getFightActions().size() - 1);
        assertEquals("Retreat", retreat.getName());
        retreat.execute();
        assertTrue(equalOpponent.isRetreating());
    }
}
