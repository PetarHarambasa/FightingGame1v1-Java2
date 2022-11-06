package hr.algebra.java2.utils;

import hr.algebra.java2.model.PlayerInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class Messenger {

    private static final String VICTORY_MESSAGE = "Victory!";
    private static final String SAVE_MESSAGE = "Saving game";
    public static void setVictoryTitle(PlayerInfo player, Label labelPlayer){
        labelPlayer.setText(player.getPlayerName() + " has " + player.getNumberOfWins() + " victories!");
    }
    public static void setBestTime(PlayerInfo player, Label labelPlayer){
        labelPlayer.setText("Best victory time: " + player.getBestVictoryTime() + " seconds");
    }
    public static String getVICTORY_MESSAGE(){
        return VICTORY_MESSAGE;
    }
    public static String getSAVE_MESSAGE(){
        return SAVE_MESSAGE;
    }
    public static void showSavedGameMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Messenger.getSAVE_MESSAGE());
        alert.setHeaderText("Saving current game.");
        alert.setContentText("Game saved!");
        alert.showAndWait();
    }
    public static void showLoadingErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loading game");
        alert.setHeaderText("Error occurred while loading game!");
        alert.setContentText("No saved game!");

        alert.showAndWait();
    }
    public static void showDocumentCreationErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while creating documentation file");
        alert.setHeaderText("Cannot generate documentation");
        alert.setContentText("Details: " + message);

        alert.showAndWait();
    }
    public static void showDocumentCreationSuccessMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Documentation generated successfuly!");
        alert.setContentText("The file \"documentation.html\" has been generated!");

        alert.showAndWait();
    }
}
