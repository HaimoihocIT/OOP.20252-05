package game;

import game.graphics.Screen;
import game.graphics.text.BitmapFont;
import game.input.Keyboard;
import game.input.Mouse;
import game.input.Tool;
import static game.GameConstants.*;

public class GameContext {
    public Screen screen;
    public StateHandler handler;
    public Mouse mouse;
    public BitmapFont guiFont;
    public Keyboard keyboard;
    public int scale = DEFAULT_SCALE;
    public boolean scaleChanged = false;

    public int day = STARTING_DAY;
    public int balance = STARTING_BALANCE;
    public int selectedX = -1;
    public int selectedY = -1;
    public Tool selectedTool = Tool.NONE;
    public String message = "Welcome to Smart Farm!";

    public int tickCounter = 0;
    public int shopPage = 0;
    public int seedIndex = 0;

    public Runnable scaleChangedCallback;

    public boolean showQuitConfirm = false;
    public boolean hasActiveGame = false;

    public void reset() {
        day = STARTING_DAY;
        balance = STARTING_BALANCE;
        selectedX = -1;
        selectedY = -1;
        selectedTool = Tool.NONE;
        message = "Welcome to Smart Farm!";
        showQuitConfirm = false;
        shopPage = 0;
        seedIndex = 0;
        hasActiveGame = true;
    }
}
