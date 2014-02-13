package dinosaurs;

import dinosaurs.command.Command;
import dinosaurs.io.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

    public static final String OPTION_ONE = "optionOne";
    public static final String OPTION_TWO = "optionTwo";
    public static final String OPTION_THREE = "optionThree";
    @Mock
    private Console console;
    @Mock
    private Command option;
    @Mock
    private Command optionTwo;
    @Mock
    private Command optionThree;

    private Menu menu;

    @Before
    public void setup() {
        when(option.getName()).thenReturn(OPTION_ONE);
        when(optionTwo.getName()).thenReturn(OPTION_TWO);
        when(optionThree.getName()).thenReturn(OPTION_THREE);
        menu = new Menu(console, asList(option, optionTwo, optionThree));
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        menu.displayOptions();
        verify(console, atLeastOnce()).print(anyString());
    }

    @Test
    public void shouldDisplayMenuOptions() {
        menu.displayOptions();
        verify(console).print(Mockito.matches(".*"+OPTION_ONE+".*"));
        verify(console).print(Mockito.matches(".*"+OPTION_TWO+".*"));
        verify(console).print(Mockito.matches(".*"+OPTION_THREE+".*"));
    }

    @Test
    public void shouldAskForUserInputAndReturnAnOptionIndex() {
        when(console.ask()).thenReturn(3);
        assertEquals(menu.promptForOptionNumber(), 2);
    }

    @Test
    public void shouldAskAgainForInputIfInputIsOutOfBounds() {
        when(console.ask()).thenReturn(4, -1, 2);
        assertEquals(menu.promptForOptionNumber(), 1);
        verify(console, times(3)).ask();
    }

    @Test
    public void shouldExecuteCommand() {
        menu.execute(2);
        verify(optionThree).execute();
    }

}
