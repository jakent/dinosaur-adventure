package dinosaurs.dal;

import dinosaurs.model.Dinosaur;

public interface DinosaurRepository {

    void createDinosaur(Dinosaur dino);

    Dinosaur getDinosaur();
}
