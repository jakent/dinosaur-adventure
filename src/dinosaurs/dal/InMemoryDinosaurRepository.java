package dinosaurs.dal;

import dinosaurs.model.Dinosaur;

public class InMemoryDinosaurRepository implements DinosaurRepository {

    private Dinosaur dinosaur;

    @Override
    public void insertDinosaur(Dinosaur dino) {
        dinosaur = dino;
    }

    @Override
    public Dinosaur getDinosaur() {
        return dinosaur;
    }
}
