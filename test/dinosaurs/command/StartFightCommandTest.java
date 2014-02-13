package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import dinosaurs.model.Fight;
import dinosaurs.model.fight_action.FightAction;
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
    @Mock
    private Console console;
    @Mock
    private DinosaurRepository dinoRepo;
    @Mock
    private FightDeterminer fightDeterminer;
    @Mock
    private FightAction fightAction;

    private Dinosaur opponent;

    private StartFightCommand underTest;
    private Dinosaur dino;

    @Before
    public void setup() {
        dino = DinosaurFactory.create("test-dino");
        when(dinoRepo.getDinosaur()).thenReturn(dino);
        when(console.ask()).thenReturn(1);

        opponent = DinosaurFactory.create("test-opponent");

        underTest = new StartFightCommand(console, dinoRepo, fightDeterminer, opponent);
    }

    @Test
    public void shouldDisplayName() {
        assertEquals(underTest.getName(), "Start Fight");
    }

    @Test
    public void shouldPrintWinningMessage() {
        Fight fight = buildEndedFight(dino);
        when(fightDeterminer.startFight(dino, opponent)).thenReturn(fight);
        underTest.execute();
        verify(console).print(contains(dino.getName() + " wins"));
    }

    @Test
    public void shouldGainExpAfterWinning() {
        Fight fight = buildEndedFight(dino);
        when(fightDeterminer.startFight(dino, opponent)).thenReturn(fight);
        final int oldExp = dino.getExp();
        underTest.execute();
        assertTrue(dino.getExp() > oldExp);
    }

    @Test
    public void shouldPrintLosingMessage() {
        Fight fight = buildEndedFight(opponent);
        when(fightDeterminer.startFight(dino, opponent)).thenReturn(fight);
        underTest.execute();
        verify(console).print(contains(opponent.getName() + " wins"));
    }

    private Fight buildEndedFight(Dinosaur winner) {
        Fight fight = mock(Fight.class);
        when(fight.isOver()).thenReturn(true);
        when(fight.getWinner()).thenReturn(winner);
        return fight;
    }
}
