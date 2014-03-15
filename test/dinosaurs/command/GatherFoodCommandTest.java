package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.DefaultConsole;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GatherFoodCommandTest {
    @Mock
    private DefaultConsole console;
    @Mock
    private DinosaurRepository dinoRepo;
    @Mock
    private Random generator;

    @InjectMocks
    private GatherFoodCommand underTest;
    private Dinosaur dinosaur;

    @Before
    public void setup() {
        when(generator.nextInt(anyInt())).thenReturn(1);
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
        verify(console, atLeastOnce()).pause(1);
    }

    @Test
    public void shouldGetAttackedWhenGatheringFood() {
        when(console.ask()).thenReturn(1);
        when(generator.nextInt(anyInt())).thenReturn(0);
        underTest.execute();
        verify(console).print(dinoRepo.getDinosaur().getName() + " has been attacked while gathering food!");
    }
}
