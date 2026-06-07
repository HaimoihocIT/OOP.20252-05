package game.model.weather;
import game.model.FarmCell;

public interface Weather {
    void apply(FarmCell cell);
    
    String getName();

    int getWaterRequirement();

    double getPestSpawnMultiplier();

    TileAppearance getGrassAppearance(int baseSeed);

    TileAppearance getFarmlandAppearance(boolean up, boolean down, boolean left, boolean right, int moisture);
}
