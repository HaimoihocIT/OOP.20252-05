package game.model.trigger;

import game.GameContext;
public class PestTrigger implements GameTrigger {
    private boolean active = false;
    @Override
    public String getId() {
        return "PEST";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void activate(GameContext ctx) {
        // 
    }

    @Override
    public void deactivate(GameContext ctx) {
        //
    }

    @Override
    public void onAdvanceDay(GameContext ctx) {
        // 
    }
}