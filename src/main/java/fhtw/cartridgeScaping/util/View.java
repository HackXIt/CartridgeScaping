package fhtw.cartridgeScaping.util;

/**
 * INFO Header of View.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Enum
 * @path src/main/java/fhtw/cartridgeScaping/util
 * @project CartridgeScaping
 */
public enum View {
    MAIN("mainMenu.fxml", true),
    CONFIGURATION("playConfiguration.fxml", true),
    SETTINGS("appSettings.fxml", true),
    WAITING("waitingRoom.fxml", false),
    GAMEPLAY("gameplayView.fxml", false),
    CREDITS("gameCredits.fxml", true),
    HOST("hostingDialog.fxml", true),
    JOIN("joinDialog.fxml", true);

//    TODO Add static log messages to View to make switchView and openDialog less annoying

    private String fileName;
    private boolean hasCache;

    View(String fileName, boolean hasCache) {
        this.fileName = fileName;
        this.hasCache = hasCache;
    }
    public String getFileName() {
        return fileName;
    }

    public boolean hasCache() {
        return hasCache;
    }
}
