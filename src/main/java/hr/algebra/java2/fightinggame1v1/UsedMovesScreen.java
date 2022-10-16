package hr.algebra.java2.fightinggame1v1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsedMovesScreen implements Initializable {

    @FXML
    private ListView<List<String>> lvPlayerOneMoves;
    @FXML
    private ListView<List<String>> lvPlayerTwoMoves;
    public List<List<String>> historyOfGameMovesPlayerOne = MainGameScreen.getMovesListPlayerOne();
    public List<List<String>> historyOfGameMovesPlayerTwo = MainGameScreen.getMovesListPlayerTwo();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (List<String> gameMovesPlayer : historyOfGameMovesPlayerOne) {
            lvPlayerOneMoves.getItems().add(gameMovesPlayer);
        }
        for (List<String> gameMovesPlayer : historyOfGameMovesPlayerTwo) {
            lvPlayerTwoMoves.getItems().add(gameMovesPlayer);
        }
    }
}
