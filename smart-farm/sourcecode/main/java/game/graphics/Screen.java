package game.graphics;


public class Screen {
	public int width;
	public int height;
	public int[] pixels; 
	public int xOffset;
	public int yOffset;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; 
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		int w = sprite.getWidth();
		int h = sprite.getHeight();
		for (int y = 0; y < h; y++) {
			int ya = y + yp;
			for (int x = 0; x < w; x++) {
				int xa = x + xp;
				if (xa < -w || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * w];
				if (col != 0xffff00ff) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderPixel(int xp, int yp, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		if (xp < 0 || xp >= width || yp < 0 || yp >= height)
			return;
		if (color != 0xffff00ff)
			pixels[xp + yp * width] = color;
	}

    public void renderOutline(int xp, int yp, int w, int h, int color) {
        for (int x = xp; x < xp + w; x++) {
            if (x < 0 || x >= width) continue;
            if (yp >= 0 && yp < height) pixels[x + yp * width] = color;
            if (yp + h - 1 >= 0 && yp + h - 1 < height) pixels[x + (yp + h - 1) * width] = color;
        }
        for (int y = yp; y < yp + h; y++) {
            if (y < 0 || y >= height) continue;
            if (xp >= 0 && xp < width) pixels[xp + y * width] = color;
            if (xp + w - 1 >= 0 && xp + w - 1 < width) pixels[(xp + w - 1) + y * width] = color;
        }
    }

    public void fillRect(int xp, int yp, int w, int h, int color) {
        for (int y = yp; y < yp + h; y++) {
            if (y < 0 || y >= height) continue;
            for (int x = xp; x < xp + w; x++) {
                if (x < 0 || x >= width) continue;
                pixels[x + y * width] = color;
            }
        }
    }

	/*
    Applies a rain overlay effect: renders rain sprites at fixed positions
    and adds a subtle blue color tint to the entire screen.
     */
    public void applyRainOverlay(int tick) {
        // Select rain sprite frame based on tick for animation
        Sprite rainSprite;
        int frame = (tick / 10) % 3;
        if (frame == 0) rainSprite = Sprite.rain1;
        else if (frame == 1) rainSprite = Sprite.rain2;
        else rainSprite = Sprite.rain3;

        // Render rain sprites at several positions across the grid area
        int spacing = 48;
        int yShift = (tick * 2) % spacing; // Falling effect
        for (int rx = 0; rx < width; rx += spacing) {
            for (int ry = -16 + yShift; ry < height; ry += spacing) {
                renderSprite(rx, ry, rainSprite, false);
            }
        }

        // Apply a subtle blue tint to all pixels
        for (int i = 0; i < pixels.length; i++) {
            int col = pixels[i];
            int r = (col >> 16) & 0xFF;
            int g = (col >> 8) & 0xFF;
            int b = col & 0xFF;
            r = (int)(r * 0.92);
            g = (int)(g * 0.95);
            b = Math.min(255, (int)(b * 1.08));
            pixels[i] = (r << 16) | (g << 8) | b;
        }
    }

}