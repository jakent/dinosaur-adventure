package dinosaurs.command;

import dinosaurs.factory.DinosaurFactory;
import dinosaurs.factory.PlayerFactory;
import dinosaurs.factory.StartFightCommandFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Fight;
import dinosaurs.model.Player;
import dinosaurs.service.FightDeterminer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StartFightCommandTest {
    public static final String WINS = " wins";
    @Mock
    private Console console;
    @Mock
    private FightDeterminer fightDeterminer;

    private Dinosaur opponentDino;

    private StartFightCommand underTest;
    private Dinosaur dino;

    @Before
    public void setup() {
        dino = DinosaurFactory.create("test-dino");
        opponentDino = DinosaurFactory.create("test-opponentDino");
        Player user = PlayerFactory.createUserPlayer(dino, console);
        Player opponent = PlayerFactory.createOpponentPlayer(opponentDino, console);
        when(console.ask()).thenReturn(1);


        underTest = StartFightCommandFactory.createFight(console, fightDeterminer, user, opponent);
    }

    @Test
    public void shouldDisplayName() {
        assertEquals(underTest.getName(), "Start Fight");
    }

    @Test
    public void shouldPrintWinningMessage() {
        Fight fight = buildEndedFight(dino);
        when(fightDeterminer.startFight(any(Player.class), any(Player.class))).thenReturn(fight);
        underTest.execute();
        verify(console).print(contains(dino.getName() + WINS));
    }

    @Test
    public void shouldGainExpAfterWinning() {
        Fight fight = buildEndedFight(dino);
        when(fightDeterminer.startFight(any(Player.class), any(Player.class))).thenReturn(fight);
        final int oldExp = dino.getExp();
        underTest.execute();
        assertTrue(dino.getExp() > oldExp);
    }

    @Test
    public void shouldPrintLosingMessage() {
        Fight fight = buildEndedFight(opponentDino);
        when(fightDeterminer.startFight(any(Player.class), any(Player.class))).thenReturn(fight);
        underTest.execute();
        verify(console).print(contains(opponentDino.getName() + WINS));
    }

    private Fight buildEndedFight(Dinosaur winner) {
        Fight fight = mock(Fight.class);
        when(fight.isOver()).thenReturn(true);
        when(fight.getWinner()).thenReturn(winner);
        return fight;
    }
}
