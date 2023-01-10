package hr.algebra.java2.fightinggame1v1;

import hr.algebra.java2.model.PlayerInfo;
import hr.algebra.java2.utils.ClassPLayerUtil;
import hr.algebra.java2.utils.FileUtils;
import hr.algebra.java2.utils.Messenger;
import hr.algebra.java2.utils.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerVictoryScreen implements Initializable {

    @FXML
    private Label lbPlayerOneWins;
    @FXML
    private Label lbPlayerTwoWins;
    @FXML
    private Label lbTimeOfVictory;
    @FXML
    private Label lbBestVictoryTimePLayerOne;
    @FXML
    private Label lbBestVictoryTimePLayerTwo;
    @FXML
    private ImageView imPlayerOneImage;
    @FXML
    private ImageView imPlayerTwoImage;
    public long victoryTime = MainGameScreen.getTimeElapsed();
    public PlayerInfo playerOne = CharacterSelectionScreen.getPlayerOneInfo();
    public PlayerInfo playerTwo = CharacterSelectionScreen.getPlayerTwoInfo();

    public void startNewGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Settings.getFILENAME_MAIN_GAME()));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HelloApplication.getMainStage().setTitle(Settings.getSTAGE_TITLE());
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imPlayerOneImage.setImage(playerOne.getPlayerImage());
        imPlayerTwoImage.setImage(playerTwo.getPlayerImage());
        Messenger.setVictoryTitle(playerOne, lbPlayerOneWins);
        Messenger.setVictoryTitle(playerTwo, lbPlayerTwoWins);
        lbTimeOfVictory.setText(victoryTime + " seconds");
        updatePLayerBestTime(MainGameScreen.getVictoryPlayer());
        playerOne.setHealthPoints(ClassPLayerUtil.selectedHp(playerOne.getCharacterClass()));
        playerTwo.setHealthPoints(ClassPLayerUtil.selectedHp(playerTwo.getCharacterClass()));

    }

    private void updatePLayerBestTime(PlayerInfo victoryPlayer) {

        playSetBestTime(victoryPlayer, playerOne, lbBestVictoryTimePLayerOne);

        playSetBestTime(victoryPlayer, playerTwo, lbBestVictoryTimePLayerTwo);

    }

    private void playSetBestTime(PlayerInfo victoryPlayer, PlayerInfo player, Label lbBestVictoryTimePLayer) {
        if (victoryPlayer == player && victoryPlayer.getBestVictoryTime() == 0 || (victoryPlayer == player && victoryPlayer.getBestVictoryTime() > victoryTime)) {
            victoryPlayer.setBestVictoryTime(victoryTime);
        }
        Messenger.setBestTime(player, lbBestVictoryTimePLayer);
    }

    public void showUsedMoves() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Settings.getFILENAME_MOVES_HISTORY()));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle(Settings.getSTAGE_TITLE());
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void saveGame() throws IOException {
        FileUtils.saveCurrentGame(playerOne, playerTwo);
        Messenger.showSavedGameMessage();
    }

    public void replay() {
        try {
            InputStream playerOneMovesFile = new FileInputStream("playerone.xml");
            InputStream playerTwoMovesFile = new FileInputStream("playertwo.xml");

            DocumentBuilder parserPlayerOne =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();
            DocumentBuilder parserPlayerTwo =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document xmlPlayerOneDocument = parserPlayerOne.parse(playerOneMovesFile);
            Document xmlPlayerTwoDocument = parserPlayerTwo.parse(playerTwoMovesFile);

            NodeList nodeListPlayerOne = xmlPlayerOneDocument.getElementsByTagName("Move");
            NodeList nodeListPlayerTwo = xmlPlayerTwoDocument.getElementsByTagName("Move");

            for (int i = 0; i < nodeListPlayerOne.getLength(); i++) {
                if (i <= nodeListPlayerOne.getLength()) {
                    String playerOneMoveString = nodeListPlayerOne.item(i).getTextContent();
                    System.out.println("Player one move: " + playerOneMoveString);
                    Thread.sleep(1000);
                }

                if (i < nodeListPlayerTwo.getLength()) {
                    String playerTwoMoveString = nodeListPlayerTwo.item(i).getTextContent();
                    System.out.println("Player two move: " + playerTwoMoveString);
                    Thread.sleep(1000);
                }
            }

            System.out.println("Replay finished!");
        } catch (IOException | ParserConfigurationException | SAXException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
