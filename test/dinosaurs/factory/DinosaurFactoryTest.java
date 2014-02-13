package dinosaurs.factory;

import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
