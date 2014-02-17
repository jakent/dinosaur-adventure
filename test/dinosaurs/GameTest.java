package dinosaurs;

import dinosaurs.command.CommandList;
import dinosaurs.io.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
//        Game underTest = new Game(console, commandList);
//        when(console.ask()).thenReturn(1);
//        when(console.prompt(anyString())).thenReturn("something");
//
//        underTest.chooseDinosaur();
//        when(console.ask()).thenReturn(2);
//        //when(commandList)
//        when(commandList.isAlive()).thenReturn(true).thenReturn(false);
//        underTest.loop();
//        verify(console, times(1)).ask();
    }
}