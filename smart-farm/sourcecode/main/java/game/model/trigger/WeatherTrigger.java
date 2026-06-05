package game.model.trigger;

import game.GameContext;
import game.model.weather.Weather;

public class WeatherTrigger implements GameTrigger {
    private final String id;
    private final Weather targetWeather;
    private boolean active = false;

    public WeatherTrigger(String id, Weather targetWeather) {
        this.id = id;
        this.targetWeather = targetWeather;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void activate(GameContext ctx) {
        // replace the current weather by the weather triggered
    }

    @Override
    public void deactivate(GameContext ctx) {
       // take the current weather again 
    }

    @Override
    public void onAdvanceDay(GameContext ctx) {
        // 
    }
}
