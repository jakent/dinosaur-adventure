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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EatFoodCommandTest {

    @Mock
    private DinosaurRepository dinoRepo;
    @Mock
    private Console console;
    private EatFoodCommand underTest;
    private Dinosaur dino;

    @Before
    public void setup() {
        dino = DinosaurFactory.create("test");
        when(dinoRepo.getDinosaur()).thenReturn(dino);
        underTest = new EatFoodCommand(dinoRepo, console);
    }

    @Test
    public void shouldDisplayName() {
        assertTrue(underTest.getName().contains("Eat"));
    }

    @Test
    public void shouldGainHealthByEatingOnlyIfInjured() {
        int health = dino.getHealth();
        underTest.execute();
        assertEquals(dino.getHealth(), health);
        dino.injure(50);
        health = dino.getHealth();
        underTest.execute();
        assertTrue(dino.getHealth() > health);
    }
}
