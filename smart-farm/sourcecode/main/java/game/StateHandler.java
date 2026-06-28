package game;

public class StateHandler {

    private GameState currentState = GameState.MENU;
    private GameState lastState;

    public GameState getState() {
        return currentState;
    }

    public void setState(GameState next) {
        if (!isValidTransition(currentState, next)) {
            throw new IllegalStateException("Invalid transition: " + currentState + " -> " + next);
        }
        lastState = currentState;
        currentState = next;
    }

    private boolean isValidTransition(GameState from, GameState to) {
        return switch (from) {
            case MENU     -> to == GameState.GAME || to == GameState.HELP;
            case GAME     -> to == GameState.MENU || to == GameState.SHOP || to == GameState.GAMEOVER;
            case SHOP     -> to == GameState.GAME;
            case HELP     -> to == GameState.MENU;
            case GAMEOVER -> to == GameState.MENU || to == GameState.GAME;
        };
    }

    public GameState getLastState() {
        return lastState;
    }
}
