package hr.algebra.java2.fightinggame1v1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UsedMovesScreen implements Initializable {

    @FXML
    private ListView<List<String>> lvPlayerOneMoves;
    @FXML
    private ListView<List<String>> lvPlayerTwoMoves;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (List<String> gameMovesPlayer : CharacterSelectionScreen.getPlayerOneInfo().getMovesList()) {
            lvPlayerOneMoves.getItems().add(gameMovesPlayer);
        }
        for (List<String> gameMovesPlayer : CharacterSelectionScreen.getPlayerTwoInfo().getMovesList()) {
            lvPlayerTwoMoves.getItems().add(gameMovesPlayer);
        }
    }
}
