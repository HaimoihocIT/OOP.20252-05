package game.model.trigger;

import game.GameContext;
import game.model.weather.Drought;
import game.model.weather.Rainy;
import game.model.weather.Sunny;

import java.util.HashMap;
import java.util.Map;

public class TriggerManager {
    private final Map<String, GameTrigger> triggers = new HashMap<>();

    public TriggerManager(){
        registerTrigger(new PestTrigger());
        registerTrigger(new WeatherTrigger("WEATHER_SUNNY", new Sunny()));
        registerTrigger(new WeatherTrigger("WEATHER_RAINY", new Rainy()));
        registerTrigger(new WeatherTrigger("WEATHER_DROUGHT", new Drought()));
    }
    public void registerTrigger(GameTrigger trigger) {
        triggers.put(trigger.getId(), trigger);
    }

    public boolean isTriggerActive(String id) {
        GameTrigger t = triggers.get(id);
        return t != null && t.isActive();
    }

    
    public void toggleTrigger(String id, GameContext ctx) {
        GameTrigger t = triggers.get(id);
        if (t == null) 
            return;

        if (t.isActive()) {
            t.deactivate(ctx);
        } else {
            if (t.getCategory() == TriggerCategory.WEATHER) {
                for (GameTrigger other : triggers.values()) {
                    if (other.getCategory() == TriggerCategory.WEATHER && other.isActive()) {
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
