package dinosaurs.service;

import dinosaurs.factory.DinosaurFactory;
import dinosaurs.factory.PlayerFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Player;
import dinosaurs.model.fight_action.Attack;
import dinosaurs.model.fight_action.FightAction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
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
    private ArrayList<FightAction> actions;
    private Player user;
    private Player opponent;

    @Before
    public void setup() {
        when(console.ask()).thenReturn(1);
        actions = newArrayList((FightAction) new Attack("generalAttack", 40));
        userDino = DinosaurFactory.create("test2");
        otherDino = DinosaurFactory.create("test1");
        user = PlayerFactory.createUserPlayer(userDino, console);
        opponent = PlayerFactory.createOpponentPlayer(otherDino, console);
        underTest = new FightDeterminer(console);
    }

    @Test
    public void shouldDisplayDinosaurAttacks() {
        underTest.startFight(user, opponent);
        verify(console, atLeastOnce()).print(contains(userDino.getFightActions().get(0).getName()));
    }

    @Test
    public void shouldPromptForAttackOption() {
        underTest.startFight(user, opponent);
        verify(console, atLeastOnce()).ask();
    }

    @Test
    public void shouldExecuteAttack() {
        final int initialHealth = otherDino.getHealth();
        underTest.startFight(user, opponent);
        assertTrue(otherDino.getHealth() < initialHealth);
    }

    @Test
    public void shouldCheckIfFightIsOverAfterEachAttack() {
        final int damage = 100;
        final FightAction goldenBullet = new Attack("goldenBullet", damage);
        userDino = DinosaurFactory.createWithFightActions("strongDino", newArrayList(goldenBullet));
        user = PlayerFactory.createUserPlayer(userDino, console);
        final int initialOtherHealth = otherDino.getHealth();
        final int initialUserHealth = userDino.getHealth();
        underTest.startFight(user, opponent);
        assertTrue(otherDino.getHealth() == initialOtherHealth - damage);
        assertTrue(userDino.getHealth() == initialUserHealth);
    }

    @Test
    public void shouldBeAttackedByOpponent() {
        final int initialHealth = userDino.getHealth();
        userDino = DinosaurFactory.createWithFightActions("DinoA", actions);
        otherDino = DinosaurFactory.createWithFightActions("DinoB", actions);
        user = PlayerFactory.createUserPlayer(userDino, console);
        opponent = PlayerFactory.createOpponentPlayer(otherDino, console);
        underTest.startFight(user, opponent);
        assertTrue(userDino.getHealth() < initialHealth);
    }

    @Test
    public void shouldResetBlockDinosaurAfterGettingChanceToDefend() {
        userDino = mock(Dinosaur.class);
        when(userDino.getFightActions()).thenReturn(actions);
        user = PlayerFactory.createUserPlayer(userDino, console);
        underTest.startFight(user, opponent);
        verify(userDino, atLeastOnce()).resetBlock();
    }

    @Test
    public void shouldLetOpponentAttackFirst() {
        userDino = DinosaurFactory.createWithHealth("DinoA", 40);
        otherDino = DinosaurFactory.createWithFightActions("DinoB", actions);
        user = PlayerFactory.createUserPlayer(userDino, console);
        opponent = PlayerFactory.createOpponentPlayer(otherDino, console);
        underTest.startFight(opponent, user);
        verify(console, never()).ask();
    }
}
