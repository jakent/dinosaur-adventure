package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GatherFoodCommandTest {
    @Mock
    private Console console;
    @Mock
    private DinosaurRepository dinoRepo;

    @InjectMocks
    private GatherFoodCommand underTest;
    private Dinosaur dinosaur;

    @Before
    public void setup() {
        dinosaur = DinosaurFactory.createWithExp("test", 0);
        when(dinoRepo.getDinosaur()).thenReturn(dinosaur);
    }

    @Test
    public void shouldDisplayName() {
        assertEquals(underTest.getName(), "Gather Food");
    }

    @Test
    public void shouldGainExperience() {
        underTest.execute();
        assertEquals(dinosaur.getExp(), 1);
    }

    @Test
    public void shouldPauseConsoleOutputToMakeUserWait() {
        underTest.execute();
        verify(console, times(3)).pause(1);
    }
}
