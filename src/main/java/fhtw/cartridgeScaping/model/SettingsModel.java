package fhtw.cartridgeScaping.model;

import fhtw.cartridgeScaping.controller.ViewManager;

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
    private boolean verbose;
    private boolean localEcho;

    public static int default_width = 800;
    public static int default_height = 600;
    public static boolean default_fullscreen = false;
    public static boolean default_blinkingCursor = true;
    public static boolean default_verbose = false;
    public static boolean default_localEcho = false;

    public SettingsModel() {
        super();
        this.window_width = default_width;
        this.window_height = default_height;
        this.fullscreen = default_fullscreen;
        this.blinkingCursor = default_blinkingCursor;
        this.verbose = default_verbose;
        this.localEcho = default_localEcho;
    }

    public int getWindowWidth() {
        return window_width;
    }

    public void setWindowWidth(int window_width) {
        if(fullscreen) {
            // TODO Implement fullscreen setWindow_width()
        } else {
            this.window_width = window_width;
        }
    }

    public int getWindowHeight() {
        return window_height;
    }

    public void setWindowHeight(int window_height) {
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
        ViewManager.getInstance().devLog(
                String.format("%s blinking cursor.",
                        blinkingCursor ? "Enabled" : "Disabled"));
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
        ViewManager.getInstance().devLog(
                String.format("%s verbose mode.",
                        verbose ? "Enabled" : "Disabled"));
    }

    public boolean isVerbose() {
        return verbose;
    }
}
