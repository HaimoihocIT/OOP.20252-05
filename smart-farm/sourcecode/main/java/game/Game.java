package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private boolean running = false;

    private final BufferedImage image =
        new BufferedImage(400, 225, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels =
        ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public int numUpdates = 0;
    public int numFrames = 0;

    public Game() {
        setPreferredSize(new Dimension(800, 450));
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
        // Do sth
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) { createBufferStrategy(3); return; }
        // Do sth
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}
