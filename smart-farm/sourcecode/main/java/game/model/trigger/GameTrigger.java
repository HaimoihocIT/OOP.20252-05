package game.model.trigger;

import game.GameContext;

public interface GameTrigger {
    String getId();

    TriggerCategory getCategory();

    boolean isActive();

    void activate(GameContext ctx);

    void deactivate(GameContext ctx);
    
    void onAdvanceDay(GameContext ctx);
}
