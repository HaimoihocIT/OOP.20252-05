package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import game.controller.GameController;
import game.controller.GameOverController;
import game.controller.HelpController;
import game.controller.MenuController;
import game.controller.ShopController;
import game.controller.StateUpdater;
import game.graphics.Screen;
import game.graphics.text.BitmapFont;
import game.input.InputManager;
import game.input.Keyboard;
import game.input.Mouse;
import game.model.FarmGrid;
import game.view.HelpRenderer;
import game.view.MenuRenderer;
import game.view.StateRenderer;
import static game.GameConstants.*;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private boolean running = false;

    private final BufferedImage image =
        new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels =
        ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public int numUpdates = 0;
    public int numFrames = 0;

    public GameContext ctx;
    private InputManager inputManager;

    private final StateUpdater menuUpdater     = new MenuController();
    private final StateUpdater helpUpdater     = new HelpController();
    private final StateUpdater gameUpdater     = new GameController();
    private final StateUpdater shopUpdater     = new ShopController();
    private final StateUpdater gameOverUpdater = new GameOverController();

    private final StateRenderer menuRenderer = new MenuRenderer();
    private final StateRenderer helpRenderer = new HelpRenderer();

    public Game() {
        ctx = new GameContext();
        inputManager = new InputManager();

        setPreferredSize(new Dimension(SCREEN_WIDTH * DEFAULT_SCALE, SCREEN_HEIGHT * DEFAULT_SCALE));

        ctx.screen  = new Screen(SCREEN_WIDTH, SCREEN_HEIGHT);
        ctx.handler = new StateHandler();
        ctx.mouse   = new Mouse();
        ctx.keyboard = new Keyboard();
        ctx.grid    = new FarmGrid(GRID_ROWS, GRID_COLS);
        ctx.guiFont = new BitmapFont("/font maps/monogram-bitmap.json");

        addMouseListener(ctx.mouse);
        addMouseMotionListener(ctx.mouse);
        addKeyListener(ctx.keyboard);
        setFocusTraversalKeysEnabled(false);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try { thread.join(); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1_000_000_000.0 / 60.0;
        double delta = 0;
        int frames = 0, updates = 0;
        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) { update(); updates++; delta--; }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                numFrames = frames; numUpdates = updates;
                frames = 0; updates = 0;
            }
        }
    }

    public void update() {
        ctx.keyboard.update();
        ctx.tickCounter++;

        switch (ctx.handler.getState()) {
            case MENU     -> menuUpdater.update(ctx, inputManager);
            case HELP     -> helpUpdater.update(ctx, inputManager);
            case GAME     -> gameUpdater.update(ctx, inputManager);
            case SHOP     -> shopUpdater.update(ctx, inputManager);
            case GAMEOVER -> gameOverUpdater.update(ctx, inputManager);
            default       -> {}
        }

        if (ctx.scaleChanged) {
            ctx.scaleChanged = false;
            if (ctx.scaleChangedCallback != null) ctx.scaleChangedCallback.run();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) { createBufferStrategy(3); return; }

        ctx.screen.clear();

        switch (ctx.handler.getState()) {
            case MENU -> menuRenderer.render(ctx);
            case HELP -> helpRenderer.render(ctx);
            default   -> {}
        }

        for (int i = 0; i < pixels.length; i++) pixels[i] = ctx.screen.pixels[i];

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}
