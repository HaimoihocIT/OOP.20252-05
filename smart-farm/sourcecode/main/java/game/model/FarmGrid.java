package game.model;
import game.model.weather.Sunny;
import game.model.weather.Weather;


public class FarmGrid {
    private final int rows;
    private final int cols;
    private final FarmCell[][] cells;
    private Weather currentWeather = new Sunny();
    private char[][] tileMap;

    public FarmGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new FarmCell[rows][cols];
        this.tileMap = new char[rows][cols];
        loadMap("/maps/farm_map.txt");
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
    
}
