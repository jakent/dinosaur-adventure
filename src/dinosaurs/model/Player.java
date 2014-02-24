package dinosaurs.model;

import dinosaurs.Menu;
import lombok.Data;

@Data
public class Player {
    private final Dinosaur dinosaur;
    private final Menu menu;
}
