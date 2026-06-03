package game.graphics.text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

import game.graphics.Screen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 A crisp bitmap font renderer that supports custom character mapping
 from a JSON bitmask definition.
 */
public class BitmapFont {
    
    private Map<String, int[]> charMap;
    private final int CHAR_HEIGHT = 12;
    private final int CHAR_WIDTH = 5;

    public BitmapFont(String jsonPath) {
        try {
            Gson gson = new Gson();
            InputStream is = BitmapFont.class.getResourceAsStream(jsonPath);
            if (is == null) {
                System.err.println("Could not find font map: " + jsonPath);
                return;
            }
            InputStreamReader reader = new InputStreamReader(is);
            Type type = new TypeToken<Map<String, int[]>>(){}.getType();
            charMap = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Renders text to the screen 
    public void render(Screen screen, String text, int xp, int yp, int color, int scale, boolean shadow, boolean fixed) {
        if (charMap == null) return;
        
        // Render Shadow 
        if (shadow) {
            renderPass(screen, text, xp + 1, yp + 1, 0x000000, scale, fixed);
        }

        // Render main color
        renderPass(screen, text, xp, yp, color, scale, fixed);
    }

    private void renderPass(Screen screen, String text, int xp, int yp, int color, int scale, boolean fixed) {
        int xOffset = 0;
        int yOffset = 0;
        int currentColor = color;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Handle layout
            if (c == '\n') {
                xOffset = 0;
                yOffset += (CHAR_HEIGHT + 2) * scale;
                continue;
            }
            if (c == ' ') {
                xOffset += (CHAR_WIDTH + 1) * scale;
                continue;
            }

            int[] mask = charMap.get(String.valueOf(c));
            if (mask != null) {
                renderChar(screen, mask, xp + xOffset, yp + yOffset, currentColor, scale, fixed);
                xOffset += (CHAR_WIDTH + 1) * scale;
            }
        }
    }

    private void renderChar(Screen screen, int[] mask, int xp, int yp, int color, int scale, boolean fixed) {
        for (int row = 0; row < mask.length; row++) {
            int rowValue = mask[row];
            // Each char is 5 pixels wide 
            for (int col = 0; col < CHAR_WIDTH; col++) {
                // Extract the bit for the current column (1 = draw, 0 = skip)
                int bit = (rowValue >> col) & 1;
                if (bit == 1) {
                    // Draw a scaled block
                    for (int sx = 0; sx < scale; sx++) {
                        for (int sy = 0; sy < scale; sy++) {
                            screen.renderPixel(xp + col * scale + sx, yp + row * scale + sy, color, fixed);
                        }
                    }
                }
            }
        }
    }
}
