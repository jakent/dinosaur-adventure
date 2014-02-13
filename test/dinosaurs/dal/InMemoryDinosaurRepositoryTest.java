package dinosaurs.dal;

import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;

import static dinosaurs.factory.DinosaurFactory.create;
import static org.junit.Assert.assertEquals;


public class InMemoryDinosaurRepositoryTest {

    public static final String DINO_NAME = "ted";
    public static final Dinosaur DINO = create(DINO_NAME);

    private InMemoryDinosaurRepository underTest;

    @Before
    public void setup() {
        underTest = new InMemoryDinosaurRepository();
    }

    @Test
    public void shouldAddDinoAndRetrieveIt() {
        underTest.createDinosaur(DINO);
        assertEquals(underTest.getDinosaur(), DINO);
    }

}
