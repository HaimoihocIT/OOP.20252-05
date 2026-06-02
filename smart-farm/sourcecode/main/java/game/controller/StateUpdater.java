package game.controller;

import game.GameContext;
import game.input.InputManager;

public interface StateUpdater {
    void update(GameContext ctx, InputManager inputManager);
}
