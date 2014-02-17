package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandListTest {
    @Mock
    private Console console;
    @Mock
    private DinosaurRepository dinoRepo;
    private CommandList underTest;
    private Dinosaur dinosaur;

    @Before
    public void setup() {
        dinosaur = DinosaurFactory.create("test");
        when(dinoRepo.getDinosaur()).thenReturn(dinosaur);
        underTest = new CommandList(console, dinoRepo);
    }

    @Test
    public void shouldTellIfUserDinosaurIsAlive() {
        underTest.prepareChooseDinosaur();
        assertTrue(underTest.isAlive());
        dinosaur.injure(100);
        assertFalse(underTest.isAlive());
    }

    @Test
    public void shouldDisplayEatCommandAfterGathering() {
        final List<Command> commands = underTest.prepareChooseDinosaur();
        underTest.prepareLoop();
        final int initialSize = commands.size();
        underTest.update(1);
        assertTrue(commands.size() > initialSize);
    }

    @Test
    public void shouldRemoveEatCommandAfterEating() {
        final List<Command> commands = underTest.prepareChooseDinosaur();
        underTest.prepareLoop();
        final int initialSize = commands.size();
        underTest.update(2);
        assertTrue(commands.size() == initialSize);
        underTest.update(1);
        underTest.update(2);
        assertTrue(commands.size() == initialSize);
        underTest.update(2);
        assertTrue(commands.size() == initialSize);
    }

    @Test
    public void shouldLevelUpDinosaur() {
        dinosaur = DinosaurFactory.createWithExp("levelingDino", 10);
        when(dinoRepo.getDinosaur()).thenReturn(dinosaur);
        underTest = new CommandList(console, dinoRepo);
        underTest.prepareChooseDinosaur();
        underTest.prepareLoop();
        underTest.update(0);
        assertEquals(dinosaur.getLevel(), 2);
        underTest.update(0);
        assertEquals(dinosaur.getLevel(), 2);
    }
}
