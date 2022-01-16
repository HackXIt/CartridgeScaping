package fhtw.cartridgeScaping.model;

/**
 * INFO Header of SettingsModel.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Class
 * @path src/main/java/fhtw/cartridgeScaping/model
 * @project CartridgeScaping
 */
public class SettingsModel extends Model{
    private int window_width;
    private int window_height;
    private boolean fullscreen;
    private boolean blinkingCursor;

    public static int default_width;
    public static int default_height;
    public static boolean default_fullscreen;
    public static boolean default_blinkingCursor;
    static {
        default_width = 800;
        default_height = 600;
        default_fullscreen = false;
        default_blinkingCursor = true;
    }

    public SettingsModel() {
        super();
    }

    public SettingsModel(int window_width, int window_height, boolean fullscreen, boolean blinkingCursor) {
        this();
        this.window_width = window_width;
        this.window_height = window_height;
        this.fullscreen = fullscreen;
        this.blinkingCursor = blinkingCursor;
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

    public boolean isBlinkingCursor() {
        return blinkingCursor;
    }

    public void setBlinkingCursor(boolean blinkingCursor) {
        this.blinkingCursor = blinkingCursor;
    }
}
