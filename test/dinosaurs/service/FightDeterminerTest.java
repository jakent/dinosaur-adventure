package dinosaurs.service;

import com.google.common.collect.Lists;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.fight_action.Attack;
import dinosaurs.model.fight_action.FightAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FightDeterminerTest {
    @Mock
    private Console console;

    private Dinosaur otherDino;
    private Dinosaur userDino;
    private FightDeterminer underTest;

    @Before
    public void setup() {
        when(console.ask()).thenReturn(1);
        userDino = DinosaurFactory.create("test2");
        otherDino = DinosaurFactory.create("test1");
        underTest = new FightDeterminer(console);
    }

    @Test
    public void shouldDisplayDinosaurAttacks() {
        underTest.startFight(userDino, otherDino);
        verify(console, atLeastOnce()).print(contains(userDino.getFightActions().get(0).getName()));
    }

    @Test
    public void shouldProptForAttackOption() {
        underTest.startFight(userDino, otherDino);
        verify(console, atLeastOnce()).ask();
    }

    @Test
    public void shouldExecuteAttack() {
        final int initialHealth = otherDino.getHealth();
        underTest.startFight(userDino, otherDino);
        assertTrue(otherDino.getHealth() < initialHealth);
    }

    @Test
    public void shouldCheckIfFightIsOverAfterEachAttack() {
        final int damage = 100;
        final FightAction goldenBullet = new Attack("goldenBullet", damage);
        userDino = DinosaurFactory.create("strongDino", Lists.newArrayList(goldenBullet));
        final int initialOtherHealth = otherDino.getHealth();
        final int initialUserHealth = otherDino.getHealth();
        underTest.startFight(userDino, otherDino);
        assertTrue(otherDino.getHealth() == initialOtherHealth - damage);
        assertTrue(userDino.getHealth() == initialUserHealth);
    }
}
