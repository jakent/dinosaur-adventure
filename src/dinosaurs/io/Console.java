package dinosaurs.io;

public interface Console {
    public void print(String output);

    public int ask();

    public String prompt(String s);

    public void pause(int i);
}
