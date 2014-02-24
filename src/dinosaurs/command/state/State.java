package dinosaurs.command.state;

public interface State {
    State handle(int lastExecutedCommand);
}
