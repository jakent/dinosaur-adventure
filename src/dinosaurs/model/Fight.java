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
        winner = determineWinner();
        return determineProgress();
    }

    public Dinosaur getWinner() {
        return determineWinner();
    }

    private boolean determineProgress() {
        return winner != null;
    }

    private Dinosaur determineWinner() {
        Dinosaur winner = null;
        if (dinosaur1.isDead() || dinosaur1.isRetreating()) {
            winner = dinosaur2;
        } else if (dinosaur2.isDead() || dinosaur2.isRetreating()) {
            winner = dinosaur1;
        }
        return winner;
    }

}
