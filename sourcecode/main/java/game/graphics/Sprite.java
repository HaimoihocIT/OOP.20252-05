package game.graphics;

public class Sprite {
    public final int[] pixels;
    private final int width;
    private final int height;

    public Sprite(int[] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public int getWidth()  { return width; }
    public int getHeight() { return height; }
}
