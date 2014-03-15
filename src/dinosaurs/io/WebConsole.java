package dinosaurs.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class WebConsole implements Console {

    public WebConsole() {

    }

    @Override
    public void print(String output) {
        //just add to json response
    }

    @Override
    public int ask() {
        return 0;
    }

    @Override
    public String prompt(String s) {
        return null;
    }

    @Override
    public void pause(int i) {

    }
}
