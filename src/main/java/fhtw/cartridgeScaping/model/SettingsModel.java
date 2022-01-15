package fhtw.cartridgeScaping.model;

/**
 * INFO Header of SettingsModel.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/model
 * @project CartridgeScaping
 */
public class SettingsModel {
    private int window_width;
    private int window_height;
    private boolean fullscreen;

    public static int default_width = 800;
    public static int default_height = 600;

    public SettingsModel(int window_width, int window_height, boolean fullscreen) {
        this.window_width = window_width;
        this.window_height = window_height;
        this.fullscreen = fullscreen;
    }

    public int getWindow_width() {
        return window_width;
    }

    public void setWindow_width(int window_width) {
        if(fullscreen) {
            // TODO Implement fullscreen setWindow_width()
        } else {
            this.window_width = window_width;
        }
    }

    public int getWindow_height() {
        return window_height;
    }

    public void setWindow_height(int window_height) {
        if(fullscreen) {
            // TODO Implement fullscreen setWindow_height()
        } else {
            this.window_height = window_height;
        }
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        // TODO Implement fullscreen mode
        this.fullscreen = fullscreen;
    }
}
