package dinosaurs.command;

import dinosaurs.dal.DinosaurRepository;
import dinosaurs.io.Console;
import dinosaurs.model.Dinosaur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateDinosaurCommandTest {

    @Mock
    private Console console;
    @Mock
    private DinosaurRepository dinoRepo;

    @InjectMocks
    private CreateDinosaurCommand underTest;

    @Test
    public void shouldDisplayName() {
        assertEquals(underTest.getName(), "Add Dinosaur");
    }

    @Test
    public void shouldAddADinosaur() {
        String dinoName = "Triceratops";
        when(console.prompt(anyString())).thenReturn(dinoName);
        underTest.execute();
        verify(dinoRepo).createDinosaur(any(Dinosaur.class));
    }

}
