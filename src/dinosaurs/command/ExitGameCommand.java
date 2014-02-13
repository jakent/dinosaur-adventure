package dinosaurs.command;

public class ExitGameCommand  implements Command {


    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void execute() {
        //TODO:dont use sys.exit
        System.exit(0);
    }
}
