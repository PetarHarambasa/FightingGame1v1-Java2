package hr.algebra.java2.utils;

public class Settings {
    private final static String FILENAME_CHAR_SELECTION = "characterSelectionScreen.fxml";
    private final static String FILENAME_MAIN_GAME = "mainGameScreen.fxml";
    private final static String FILENAME_MAIN_GAME_MULTIPLAYER = "mainGameScreenMultiPlayer.fxml";
    private static final String FILENAME_MOVES_HISTORY = "usedMovesScreen.fxml";
    private static final String FILENAME_PLAYER_VICTORY = "playerVictoryScreen.fxml";
    private static final String FILENAME_MULTIPLAYER_CHAR_SELECTION = "multiplayerCharacterSelectionScreen.fxml";
    private final static String STAGE_TITLE = "Fighting Game 1v1";
    private final static String ERROR_MESSAGE_CHAR_NOT_VALID = "Please insert names and select classes!";

    public static String getFILENAME_CHAR_SELECTION() {
        return FILENAME_CHAR_SELECTION;
    }

    public static String getFILENAME_MAIN_GAME() {
        return FILENAME_MAIN_GAME;
    }

    public static String getFILENAME_MAIN_GAME_MULTIPLAYER() {
        return FILENAME_MAIN_GAME_MULTIPLAYER;
    }

    public static String getSTAGE_TITLE() {
        return STAGE_TITLE;
    }

    public static String getERROR_MESSAGE_CHAR_NOT_VALID() {
        return ERROR_MESSAGE_CHAR_NOT_VALID;
    }

    public static String getFILENAME_MOVES_HISTORY() {
        return FILENAME_MOVES_HISTORY;
    }

    public static String getFILENAME_PLAYER_VICTORY() {
        return FILENAME_PLAYER_VICTORY;
    }

    public static String getFILENAME_MULTIPLAYER_CHAR_SELECTION() {
        return FILENAME_MULTIPLAYER_CHAR_SELECTION;
    }

}
