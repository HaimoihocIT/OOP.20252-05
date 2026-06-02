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
}