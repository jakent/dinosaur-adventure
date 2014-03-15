package dinosaurs.dal;

import dinosaurs.model.Dinosaur;

public interface DinosaurRepository {

    void insertDinosaur(Dinosaur dino);

    Dinosaur getDinosaur();
}
