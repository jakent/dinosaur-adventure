package dinosaurs;

import dinosaurs.command.Command;
import dinosaurs.command.CommandList;
import dinosaurs.command.CreateDinosaurCommand;
import dinosaurs.dal.InMemoryDinosaurRepository;
import dinosaurs.io.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock
    private Console console;
    @Mock
    private CommandList commandList;

    @Test
    public void shouldEndGameWhenDinosaurIsDead() {
        Game underTest = new Game(console, commandList);
        when(console.ask()).thenReturn(1);
        when(console.prompt(anyString())).thenReturn("something");
        when(commandList.prepareChooseDinosaur()).thenReturn(newArrayList((Command) new CreateDinosaurCommand(console, new InMemoryDinosaurRepository())));

        underTest.chooseDinosaur();

        when(commandList.isAlive()).thenReturn(false);
        underTest.loop();
        verify(console, times(1)).ask();
    }
}