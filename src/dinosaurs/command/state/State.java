package dinosaurs.command.state;

public interface State {
    public State handle(int lastExecutedCommand);
}
