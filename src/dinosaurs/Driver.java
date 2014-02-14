package dinosaurs;

import dinosaurs.io.Console;

public class Driver {
    public static void main(String[] args){
        Console console = new Console(System.in, System.out);
        Game game = new Game(console);

        console.print("Welcome to Dino Destruction!");

        game.chooseDinosaur();

        console.print("Now that you have your dinosaur.\n" +
                "It is time for your dinosaur to start gathering food so it will become strong.");

        game.loop();

    }
}
