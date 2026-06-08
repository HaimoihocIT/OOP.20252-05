package game.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import game.model.crop.Crop;
import game.GameContext;
import static game.GameConstants.*;
import game.model.trigger.*;
import game.model.weather.Drought;
import game.model.weather.Rainy;
import game.model.weather.Sunny;
import game.model.weather.Weather;

public class FarmGrid {
    private final int rows;
    private final int cols;
    private final FarmCell[][] cells;
    private Weather bufferWeather; // the buffer supports the trigger weather events
    private Weather currentWeather = new Sunny();
    private final TriggerManager triggerManager;
    private final Random random = new Random();
    private int daysSinceLastPests = 0;
    private int pestSpawnThreshold = PEST_SPAWN_BASE_DAYS + random.nextInt(PEST_SPAWN_RANDOM_DAYS);
    private char[][] tileMap;
    public FarmGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new FarmCell[rows][cols];
        this.triggerManager = new TriggerManager();
        this.tileMap = new char[rows][cols];

        loadMap(FARM_MAP_PATH);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new FarmCell();
            }
        }
    }

    // load the raw map
    private void loadMap(String path) {
        try {
            java.io.InputStream is = getClass().getResourceAsStream(path);
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));

            for (int y = 0; y < cols; y++) {
                String line = br.readLine();
                if (line == null) break;
                for (int x = 0; x < rows; x++) {
                    tileMap[x][y] = (x < line.length()) ? line.charAt(x) : 'G';
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    tileMap[x][y] = 'G';
                }
            }
        }
    }

    public char getTileType(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return tileMap[row][col];
        }
        return 'G';
    }

    public FarmCell getCell(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return cells[row][col];
        }
        return null;
    }

    // we need to update the trigger manager before go to the next day
    public void advanceDay(GameContext ctx) {
        triggerManager.onAdvanceDay(ctx);
        boolean isSpawnPests = updatePestTimer();
        updateFarm();
        generateNextWeather();
        if (isSpawnPests) {
            spawnPests();
        }
    }

    private boolean updatePestTimer() {
        daysSinceLastPests++;
        if (daysSinceLastPests >= pestSpawnThreshold) {
            daysSinceLastPests = 0;
            pestSpawnThreshold = PEST_SPAWN_BASE_DAYS + random.nextInt(PEST_SPAWN_RANDOM_DAYS);
            return true;
        }
        return false;
    }

    private void updateFarm() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                updateCell(cells[i][j]);
            }
        }
    }

    private void updateCell(FarmCell cell) {
        currentWeather.apply(cell);
        Crop crop = cell.getCurrentCrop();
        if (crop == null) {
            return;
        }
        crop.consumeResources(cell, currentWeather);
        if (cell.hasPests()) {
            crop.pestsAttack();
        } else {
            crop.grow();
        }
        crop.checkSurvival();
    }

    private void generateNextWeather() {
        double weatherRoll = random.nextDouble();

        if (weatherRoll < 0.2) {
            currentWeather = new Rainy();
        } else if (weatherRoll < 0.4) {
            currentWeather = new Drought();
        } else {
            currentWeather = new Sunny();
        }
    }

    private void spawnPests() {
        List<FarmCell> candidates = getPestSpawnCandidates();
        if (candidates.isEmpty()) {
            return;
        }
        // randomly arrange the candidates
        Collections.shuffle(candidates);
        int baseSpawn = (int) ((6 + random.nextInt(3)) * currentWeather.getPestSpawnMultiplier());
        int numToSpawn = Math.min(baseSpawn, candidates.size());
        for (int i = 0; i < numToSpawn; i++) {
            candidates.get(i).setPests(true);
        }
    }

    private List<FarmCell> getPestSpawnCandidates() {
        List<FarmCell> candidates = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (getTileType(i, j) == 'S' && !cells[i][j].hasPests()) {
                    candidates.add(cells[i][j]);
                }
            }
        }

        return candidates;
    }

    public Weather getCurrentWeather() { 
        return currentWeather; 
    }

    public void setWeather(Weather weather) {
        this.currentWeather = weather; 
    }

    public int getRows() { 
        return rows; 
    }

    public int getCols() { 
        return cols; 
    }
    
    public Weather getBufferWeather(){
        return this.bufferWeather;
    }

    public void setBufferWeather(Weather weather){
        this.bufferWeather = weather;
    }

    public TriggerManager getTriggerManager() {
        return this.triggerManager;
    }
}

