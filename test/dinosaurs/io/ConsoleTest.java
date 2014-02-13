package dinosaurs.io;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConsoleTest {

    @Mock
    InputStream in;
    @Mock
    PrintStream out;

    @InjectMocks
    Console underTest;

    @Before
    public void setUp() throws Exception {
         initMocks(this);
    }

    @Test
    public void shouldPrintStringToOutputStream() {
        final String s = "print me";
        underTest.print(s);
        verify(out).println(s);
    }

}
