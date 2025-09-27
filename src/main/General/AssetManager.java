package main.General;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private static AssetManager instance = new AssetManager();
    private Map<String, BufferedImage> cache = new HashMap<>();

    public static AssetManager getInstance() {
        return instance;
    }

    public BufferedImage getTexture(String fileName) {
        if (cache.containsKey(fileName)) {
            return cache.get(fileName);
        }
        try {
            BufferedImage img = ImageIO.read(new File(fileName));
            cache.put(fileName, img);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
