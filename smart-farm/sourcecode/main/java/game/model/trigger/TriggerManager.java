package game.model.trigger;

import game.GameContext;
import java.util.HashMap;
import java.util.Map;

public class TriggerManager {
    private final Map<String, GameTrigger> triggers = new HashMap<>();
    public void registerTrigger(GameTrigger trigger) {
        triggers.put(trigger.getId(), trigger);
    }

    public boolean isTriggerActive(String id) {
        GameTrigger t = triggers.get(id);
        return t != null && t.isActive();
    }

    
    public void toggleTrigger(String id, GameContext ctx) {
        GameTrigger t = triggers.get(id);
        if (t == null) return;

        if (t.isActive()) {
            t.deactivate(ctx);
        } else {
            if (id.startsWith("WEATHER_")) {
                for (GameTrigger other : triggers.values()) {
                    if (other.getId().startsWith("WEATHER_") && other.isActive()) {
                        other.deactivate(ctx);
                    }
                }
            }
            t.activate(ctx);
        }
    }

    public void onAdvanceDay(GameContext ctx) {
        for (GameTrigger t : triggers.values()) {
            t.onAdvanceDay(ctx);
        }
    }
}
