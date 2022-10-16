package hr.algebra.java2.utils;

import hr.algebra.java2.model.PlayerInfo;
import javafx.scene.control.Label;

public class Messenger {

    private static final String VICTORY_MESSAGE = "Victory!";
    public static void setVictoryTitle(PlayerInfo player, Label labelPlayer){
        labelPlayer.setText(player.getPlayerName() + " has " + player.getNumberOfWins() + " victories!");
    }
    public static void setBestTime(PlayerInfo player, Label labelPlayer){
        labelPlayer.setText("Best victory time: " + player.getBestVictoryTime() + " seconds");
    }

    public static String getVICTORY_MESSAGE(){
        return VICTORY_MESSAGE;
    }
}
