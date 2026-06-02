package game.input;

import java.util.HashSet;
import java.util.Set;

public class InputManager {
    
    private final Set<String> triggeredActions = new HashSet<>();
    public boolean isJustPressed(boolean isActive, String actionId) {
        if (isActive) {
            if (!triggeredActions.contains(actionId)) {
                triggeredActions.add(actionId);
                return true;
            }
            return false;
        } else {
            triggeredActions.remove(actionId);
            return false;
        }
    }
}
