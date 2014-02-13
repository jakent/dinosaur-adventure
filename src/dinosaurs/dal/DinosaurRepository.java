package dinosaurs.dal;

import dinosaurs.model.Dinosaur;

public interface DinosaurRepository {

    public void createDinosaur(Dinosaur dino);

    public Dinosaur getDinosaur();
}
