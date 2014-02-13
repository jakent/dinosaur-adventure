package dinosaurs.model;

public class Fight {
    private final Dinosaur dinosaur1;
    private final Dinosaur dinosaur2;

    private Dinosaur winner = null;

    public Fight(Dinosaur dinoOne, Dinosaur dinoTwo) {
        dinosaur1 = dinoOne;
        dinosaur2 = dinoTwo;
    }

    public boolean isOver() {
        winner = determinWinner();
        return determinProgress();
    }

    public Dinosaur getWinner() {
        return determinWinner();
    }

    private boolean determinProgress() {
        return winner != null;
    }

    private Dinosaur determinWinner() {
        Dinosaur winner = null;
        if (dinosaur1.isDead()) {
            winner = dinosaur2;
        } else if (dinosaur2.isDead()) {
            winner = dinosaur1;
        }
        return winner;
    }

}
