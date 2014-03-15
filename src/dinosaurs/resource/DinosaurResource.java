package dinosaurs.resource;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dinosaurs.dal.DinosaurRepository;
import dinosaurs.dal.InMemoryDinosaurRepository;
import dinosaurs.factory.DinosaurFactory;
import dinosaurs.model.Dinosaur;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/start")
public class DinosaurResource {

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String startGame() {
//        //createCommand = new CreateDinosaurCommand(console, dinoRepo);
//        //Command exitCommand = new ExitGameCommand();
//
//        // commands = newArrayList(createCommand, exitCommand);
//
//        //menu = new Menu(console, commands);
//        //menu.displayOptions();
//
//        //menu.promptForOptionNumber()
//
//        //return console.flush();
//        return null;
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String executeCommandPrompt(@FormParam("choice") int commandChoice) {
//        //menu.executePrompt(commandChoice);
//
//        //console.flush();
//        return null;
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String completeExecution(@FormParam("name") String name) {
//        //menu.execute(commandChoice);
//
//        //console.flush();
//        return null;
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String doADinosaurThang(@FormParam("name") String name) {
        final DinosaurRepository dinosaurRepository = new InMemoryDinosaurRepository();

        Dinosaur dino = DinosaurFactory.create(name);
        dinosaurRepository.insertDinosaur(dino);

        String greeting = "You have created a dinosaur named \"" + name + "\"";
        String stats = dino.toString();

        JsonObject response = new JsonObject();
        response.add("greeting", new JsonPrimitive(greeting));
        response.add("stats", new JsonPrimitive(stats));
        return response.toString();
    }

}