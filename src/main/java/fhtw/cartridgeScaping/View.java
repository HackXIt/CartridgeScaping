package fhtw.cartridgeScaping;

/**
 * INFO Header of View.java
 *
 * @author Nikolaus Rieder (c)2022
 * @type Enum
 * @path src/main/java/fhtw/cartridgeScaping
 * @project CartridgeScaping
 */
public enum View {
    MAIN("mainMenu.fxml", true),
    CONFIGURATION("playConfiguration.fxml", true),
    SETTINGS("applicationSettings.fxml", true),
    WAITING("waitingRoom.fxml", false),
    GAMEPLAY("gameplayView.fxml", false),
    CREDITS("gameCredits.fxml", true);

    private String fileName;
    private boolean hasCache;

    View(String fileName, boolean hasCache) {
        this.fileName = fileName;
        this.hasCache = hasCache;
    }


    public String getFileName() {
        return fileName;
    }
}
