package game.model.trigger;

import game.GameContext;
import game.model.FarmCell;
import game.model.FarmGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PestTrigger implements GameTrigger {
    private boolean active = false;
    private final Random random = new Random();
    private final List<FarmCell> affectedCells = new ArrayList<>();

    @Override
    public String getId() {
        return "PEST";
    }

    @Override
    public TriggerCategory getCategory() {
        return TriggerCategory.PEST;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void activate(GameContext ctx) {
        FarmGrid grid = ctx.grid;
        active = true;
        List<FarmCell> candidates = new ArrayList<>();
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                if (grid.getTileType(i, j) == 'S' && !grid.getCell(i, j).hasPests()) {
                    candidates.add(grid.getCell(i, j));
                }
            }
        }
        Collections.shuffle(candidates);
        int numToSpawn = Math.min(10 + random.nextInt(3), candidates.size());
        for (int i = 0; i < numToSpawn; i++) {
            FarmCell cell = candidates.get(i);
            cell.setPests(true);
            affectedCells.add(cell);
        }
    }

    @Override
    public void deactivate(GameContext ctx) {
        active = false;
        for (FarmCell cell : affectedCells) {
            cell.clearPests();
        }
        affectedCells.clear();
    }

    @Override
    public void onAdvanceDay(GameContext ctx) {
        active = false;
        affectedCells.clear();
    }
}