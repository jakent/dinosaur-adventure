package dinosaurs.command;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExitGameCommandTest {

    ExitGameCommand underTest;

    @Test
    public void shouldDisplayName() {
        underTest = new ExitGameCommand();
        assertTrue(underTest.getName().contains("Exit"));
    }

    @Test
    public void shouldSendMessageToStopMainLoop() {
        //TODO: gracefully exit without using sys.exit
    }
}
