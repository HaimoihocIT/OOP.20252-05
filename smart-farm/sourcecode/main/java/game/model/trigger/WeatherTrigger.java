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
    public TriggerCategory getCategory() {
        return TriggerCategory.WEATHER;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void activate(GameContext ctx) {
        active = true;
        if (ctx.grid.getBufferWeather() == null) {
            ctx.grid.setBufferWeather(ctx.grid.getCurrentWeather());
        }
        ctx.grid.setWeather(targetWeather);
    }

    @Override
    public void deactivate(GameContext ctx) {
        active = false;
        // Restore the natural weather from buffer
        if (ctx.grid.getBufferWeather() != null) {
            ctx.grid.setWeather(ctx.grid.getBufferWeather());
            ctx.grid.setBufferWeather(null);
        }
    }

    @Override
    public void onAdvanceDay(GameContext ctx) {
        active = false;
        ctx.grid.setBufferWeather(null);
    }
}
