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
    Main("mainMenu.fxml", true),
    Configuration("playConfiguration.fxml", true),
    AppSettings("appSettings.fxml", true),
    Waiting("waitingRoom.fxml", false),
    Gameplay("gameplayView.fxml", false),
    Credits("gameCredits.fxml", true),
    Host("hostingDialog.fxml", true),
    Join("joinDialog.fxml", true),
    SettingsDialog("settingsDialog.fxml", true);

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
