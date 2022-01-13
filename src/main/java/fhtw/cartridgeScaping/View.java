package fhtw.cartridgeScaping;

public enum View {
    MAIN("mainMenu.fxml", true),
    CONFIGURATION("playConfiguration.fxml", true),
    SETTINGS("gameSettings.fxml", true),
    LOADING("loadingScreen.fxml", false),
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
