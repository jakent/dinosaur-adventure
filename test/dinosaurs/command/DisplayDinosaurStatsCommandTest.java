package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DisplayDinosaurStatsCommandTest {
    @Mock
    private DinosaurRepository dinosaurRepository;
    @Mock
    private Console console;


    private DisplayDinosaurStatsCommand underTest;

    @Before
    public void setup() {
        initMocks(this);
        underTest = new DisplayDinosaurStatsCommand(console, dinosaurRepository);
    }

    @Test
    public void shouldDisplayName(){
        assertTrue(underTest.getName().contains("dinosaur stats"));
    }

    @Test
    public void shouldDisplayDinosaurStats() {
        final Dinosaur dinosaur = DinosaurFactory.create("testDino");
        when(dinosaurRepository.getDinosaur()).thenReturn(dinosaur);
        underTest.execute();
        verify(console).print(dinosaur.toString());
    }

}
