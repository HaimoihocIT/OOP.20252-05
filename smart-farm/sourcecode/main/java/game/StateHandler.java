package game;
public class StateHandler {

    private GameState currentState = GameState.MENU;
    private GameState lastState;

    public GameState getState() {
        return currentState;
    }

    public void setState(GameState state) {
        lastState = currentState;
        currentState = state;
    }

    public GameState getLastState() {
        return lastState;
    }
}
