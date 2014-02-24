package dinosaurs.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    private final Scanner in;
    private final PrintStream out;

    public Console(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void print(String output) {
        out.println(output);
    }

    public int ask() {
        int i = -1;
        boolean isValid = false;
        while (!isValid) {
            try {
                i = in.nextInt();
                in.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                print("Please enter a number.");
                in.next();
            }
        }
        return i;
    }

    public String prompt(String s) {
        out.println(s);
        return in.nextLine();
    }

    public void pause(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
