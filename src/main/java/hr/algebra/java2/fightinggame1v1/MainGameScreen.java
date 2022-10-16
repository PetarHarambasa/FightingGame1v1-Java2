package hr.algebra.java2.fightinggame1v1;

import hr.algebra.java2.model.*;
import hr.algebra.java2.utils.Messenger;
import hr.algebra.java2.utils.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainGameScreen implements Initializable {
    @FXML
    private Label lbPlayerOneInfo;
    @FXML
    private Label lbPlayerTwoInfo;
    @FXML
    private Label lbPlayerOneDmgDeal;
    @FXML
    private Label lbPlayerTwoDmgDeal;
    @FXML
    private ImageView imPlayerOneImage;
    @FXML
    private ImageView imPlayerTwoImage;
    public PlayerInfo playerOne = CharacterSelectionScreen.getPlayerOneInfo();
    public PlayerInfo playerTwo = CharacterSelectionScreen.getPlayerTwoInfo();
    @FXML
    private Button btnPlayerOneMoveOne;
    @FXML
    private Button btnPlayerOneMoveTwo;
    @FXML
    private Button btnPlayerOneMoveThree;
    @FXML
    private Button btnPlayerTwoMoveOne;
    @FXML
    private Button btnPlayerTwoMoveTwo;
    @FXML
    private Button btnPlayerTwoMoveThree;
    private Boolean playerOneTurn;
    private Boolean playerTwoTurn;
    public Instant startTimeCounter;
    public Instant finishTimeCounter;
    public static long timeElapsed;

    public static long getTimeElapsed() {
        return timeElapsed;
    }

    public static PlayerInfo victoryPlayer;

    public static PlayerInfo getVictoryPlayer() {
        return victoryPlayer;
    }

    public List<String> movesListPlayerOne = new ArrayList<>();
    public List<String> movesListPlayerTwo = new ArrayList<>();

    public static List<List<String>> getMovesListPlayerOne() {
        return historyOfGameMovesPlayerOne;
    }

    public static List<List<String>> getMovesListPlayerTwo() {
        return historyOfGameMovesPlayerTwo;
    }

    public static Archer archer = Archer.getInstance();
    public static Warrior warrior = Warrior.getInstance();
    public static Wizard wizard = Wizard.getInstance();
    public static HorseMan horseMan = HorseMan.getInstance();
    public static Assassin assassin = Assassin.getInstance();

    public static List<List<String>> historyOfGameMovesPlayerOne = new ArrayList<>();
    public static List<List<String>> historyOfGameMovesPlayerTwo = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTimeCounter = Instant.now();

        playerOneTurn = true;
        playerTwoTurn = false;
        setTextForPlayer(playerOne);
        imPlayerOneImage.setImage(playerOne.getPlayerImage());
        setTextForPlayer(playerTwo);
        imPlayerTwoImage.setImage(playerTwo.getPlayerImage());

        abilityClassCheck(playerOne.getCharacterClass(), btnPlayerOneMoveOne, btnPlayerOneMoveTwo, btnPlayerOneMoveThree);
        abilityClassCheck(playerTwo.getCharacterClass(), btnPlayerTwoMoveOne, btnPlayerTwoMoveTwo, btnPlayerTwoMoveThree);

        btnPlayerTwoMoveOne.setVisible(false);
        btnPlayerTwoMoveTwo.setVisible(false);
        btnPlayerTwoMoveThree.setVisible(false);
    }

    private void setTextForPlayer(PlayerInfo player) {
        if (player == playerOne) {
            lbPlayerOneInfo.setText(player.getPlayerName() + " " + player.getCharacterClass() + ", HP: " + player.getHealthPoints());
        } else if (player == playerTwo) {
            lbPlayerTwoInfo.setText(player.getPlayerName() + " " + player.getCharacterClass() + ", HP: " + player.getHealthPoints());
        }
    }

    private void abilityClassCheck(Characters characterClass, Button btnPlayerMoveOne, Button btnPlayerMoveTwo, Button btnPlayerMoveThree) {
        if (characterClass == Characters.Archer) {
            btnPlayerMoveOne.setText(archer.getClassMovesOne().get(getRandomAbility(archer.getClassMovesOne())));
            btnPlayerMoveTwo.setText(archer.getClassMovesTwo().get(getRandomAbility(archer.getClassMovesTwo())));
            btnPlayerMoveThree.setText(archer.getClassMovesThree().get(getRandomAbility(archer.getClassMovesThree())));
        } else if (characterClass == Characters.Warrior) {
            btnPlayerMoveOne.setText(warrior.getClassMovesOne().get(getRandomAbility(warrior.getClassMovesOne())));
            btnPlayerMoveTwo.setText(warrior.getClassMovesTwo().get(getRandomAbility(warrior.getClassMovesTwo())));
            btnPlayerMoveThree.setText(warrior.getClassMovesThree().get(getRandomAbility(warrior.getClassMovesThree())));
        } else if (characterClass == Characters.Wizard) {
            btnPlayerMoveOne.setText(wizard.getClassMovesOne().get(getRandomAbility(wizard.getClassMovesOne())));
            btnPlayerMoveTwo.setText(wizard.getClassMovesTwo().get(getRandomAbility(wizard.getClassMovesTwo())));
            btnPlayerMoveThree.setText(wizard.getClassMovesThree().get(getRandomAbility(wizard.getClassMovesThree())));
        } else if (characterClass == Characters.HorseMan) {
            btnPlayerMoveOne.setText(horseMan.getClassMovesOne().get(getRandomAbility(horseMan.getClassMovesOne())));
            btnPlayerMoveTwo.setText(horseMan.getClassMovesTwo().get(getRandomAbility(horseMan.getClassMovesTwo())));
            btnPlayerMoveThree.setText(horseMan.getClassMovesThree().get(getRandomAbility(horseMan.getClassMovesThree())));
        } else if (characterClass == Characters.Assassin) {
            btnPlayerMoveOne.setText(assassin.getClassMovesOne().get(getRandomAbility(assassin.getClassMovesOne())));
            btnPlayerMoveTwo.setText(assassin.getClassMovesTwo().get(getRandomAbility(assassin.getClassMovesTwo())));
            btnPlayerMoveThree.setText(assassin.getClassMovesThree().get(getRandomAbility(assassin.getClassMovesThree())));
        }
    }

    public int getRandomAbility(List<String> abilityList) {
        return (int) Math.floor(Math.random() * (abilityList.size()));
    }

    public void attackOneButtonPressed() throws IOException {
        if (playerOneTurn) {
            playerOneTurn = false;
            playerTwoTurn = true;
            movesListPlayerOne.add(btnPlayerOneMoveOne.getText());
            playerTwoMovesVisible();
            playerDisableUsedAttackOne(playerOne);
            updatePlayerHp(playerTwo, btnPlayerOneMoveOne);
            abilityClassCheck(playerOne.getCharacterClass(), btnPlayerOneMoveOne, btnPlayerOneMoveTwo, btnPlayerOneMoveThree);
            checkWinner(playerTwo, playerOne);
        } else if (playerTwoTurn) {
            playerOneTurn = true;
            playerTwoTurn = false;
            movesListPlayerTwo.add(btnPlayerTwoMoveOne.getText());
            playerOneMovesVisible();
            playerDisableUsedAttackOne(playerTwo);
            updatePlayerHp(playerOne, btnPlayerTwoMoveOne);
            abilityClassCheck(playerTwo.getCharacterClass(), btnPlayerTwoMoveOne, btnPlayerTwoMoveTwo, btnPlayerTwoMoveThree);
            checkWinner(playerOne, playerTwo);
        }
    }

    private void playerDisableUsedAttackOne(PlayerInfo player) {
        if (player == playerOne) {
            btnPlayerOneMoveOne.setDisable(true);
            btnPlayerOneMoveOne.setTextFill(Color.RED);
            btnPlayerOneMoveTwo.setDisable(false);
            btnPlayerOneMoveTwo.setTextFill(Color.BLACK);
            btnPlayerOneMoveThree.setDisable(false);
            btnPlayerOneMoveThree.setTextFill(Color.BLACK);
            return;
        } else if (player == playerTwo)
            btnPlayerTwoMoveOne.setDisable(true);
        btnPlayerTwoMoveOne.setTextFill(Color.RED);
        btnPlayerTwoMoveTwo.setDisable(false);
        btnPlayerTwoMoveTwo.setTextFill(Color.BLACK);
        btnPlayerTwoMoveThree.setDisable(false);
        btnPlayerTwoMoveThree.setTextFill(Color.BLACK);
    }

    public void attackTwoButtonPressed() throws IOException {
        if (playerOneTurn) {
            playerOneTurn = false;
            playerTwoTurn = true;
            movesListPlayerOne.add(btnPlayerOneMoveTwo.getText());
            playerTwoMovesVisible();
            playerDisableUsedAttackTwo(playerOne);
            updatePlayerHp(playerTwo, btnPlayerOneMoveTwo);
            abilityClassCheck(playerOne.getCharacterClass(), btnPlayerOneMoveOne, btnPlayerOneMoveTwo, btnPlayerOneMoveThree);
            checkWinner(playerTwo, playerOne);
        } else if (playerTwoTurn) {
            playerOneTurn = true;
            playerTwoTurn = false;
            movesListPlayerTwo.add(btnPlayerTwoMoveTwo.getText());
            playerOneMovesVisible();
            playerDisableUsedAttackTwo(playerTwo);
            updatePlayerHp(playerOne, btnPlayerTwoMoveTwo);
            abilityClassCheck(playerTwo.getCharacterClass(), btnPlayerTwoMoveOne, btnPlayerTwoMoveTwo, btnPlayerTwoMoveThree);
            checkWinner(playerOne, playerTwo);
        }
    }

    private void playerDisableUsedAttackTwo(PlayerInfo player) {
        if (player == playerOne) {
            btnPlayerOneMoveOne.setDisable(false);
            btnPlayerOneMoveOne.setTextFill(Color.BLACK);
            btnPlayerOneMoveTwo.setDisable(true);
            btnPlayerOneMoveTwo.setTextFill(Color.RED);
            btnPlayerOneMoveThree.setDisable(false);
            btnPlayerOneMoveThree.setTextFill(Color.BLACK);
        } else if (player == playerTwo) {
            btnPlayerTwoMoveOne.setDisable(false);
            btnPlayerTwoMoveOne.setTextFill(Color.BLACK);
            btnPlayerTwoMoveTwo.setDisable(true);
            btnPlayerTwoMoveTwo.setTextFill(Color.RED);
            btnPlayerTwoMoveThree.setDisable(false);
            btnPlayerTwoMoveThree.setTextFill(Color.BLACK);
        }
    }

    public void attackThreeButtonPressed() throws IOException {
        if (playerOneTurn) {
            playerOneTurn = false;
            playerTwoTurn = true;
            movesListPlayerOne.add(btnPlayerOneMoveThree.getText());
            updatePlayerHp(playerTwo, btnPlayerOneMoveThree);
            playerTwoMovesVisible();
            playerDisableUsedAttackThree(playerOne);
            abilityClassCheck(playerOne.getCharacterClass(), btnPlayerOneMoveOne, btnPlayerOneMoveTwo, btnPlayerOneMoveThree);
            checkWinner(playerTwo, playerOne);
        } else if (playerTwoTurn) {
            playerOneTurn = true;
            playerTwoTurn = false;
            movesListPlayerTwo.add(btnPlayerTwoMoveThree.getText());
            updatePlayerHp(playerOne, btnPlayerTwoMoveThree);
            playerOneMovesVisible();
            playerDisableUsedAttackThree(playerTwo);
            abilityClassCheck(playerTwo.getCharacterClass(), btnPlayerTwoMoveOne, btnPlayerTwoMoveTwo, btnPlayerTwoMoveThree);
            checkWinner(playerOne, playerTwo);
        }
    }

    private void checkWinner(PlayerInfo playerLose, PlayerInfo playerWin) throws IOException {
        if (playerLose.getHealthPoints() <= 0) {
            historyOfGameMovesPlayerOne.add(movesListPlayerOne);
            historyOfGameMovesPlayerTwo.add(movesListPlayerTwo);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(Messenger.getVICTORY_MESSAGE());
            alert.setContentText("Player " + playerWin.getPlayerName() + " won!");

            finishTimeCounter = Instant.now();
            timeElapsed = Duration.between(startTimeCounter, finishTimeCounter).toSeconds();
            victoryPlayer = playerWin;
            alert.showAndWait();
            playerWin.setNumberOfWins(playerWin.getNumberOfWins() + 1);
            victoryScreen();
        }
    }

    private void victoryScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Settings.getFILENAME_PLAYER_VICTORY()));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        HelloApplication.getMainStage().setTitle(Settings.getSTAGE_TITLE());
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    private void updatePlayerHp(PlayerInfo player, Button btnMove) {
        int archerMoveOneDmg = (int) (Math.random() * (400 - 200 + 1) + 200);
        int archerMoveTwoDmg = (int) (Math.random() * (600 - 400 + 1) + 400);
        int archerMoveThreeDmg = (int) (Math.random() * (800 - 600 + 1) + 600);

        int warriorMoveOneDmg = (int) (Math.random() * (200 - 100 + 1) + 100);
        int warriorMoveTwoDmg = (int) (Math.random() * (400 - 200 + 1) + 200);
        int warriorMoveThreeDmg = (int) (Math.random() * (600 - 400 + 1) + 400);

        int wizardMoveOneDmg = (int) (Math.random() * (200 - 100 + 1) + 100);
        int wizardMoveTwoDmg = (int) (Math.random() * (400 - 200 + 1) + 200);
        int wizardMoveThreeDmg = (int) (Math.random() * (1000 - 800 + 1) + 800);

        int horseManMoveOneDmg = (int) (Math.random() * (200 - 100 + 1) + 100);
        int horseManMoveTwoDmg = (int) (Math.random() * (400 - 200 + 1) + 200);
        int horseManMoveThreeDmg = (int) (Math.random() * (800 - 600 + 1) + 600);

        int assassinMoveOneDmg = (int) (Math.random() * (200 - 100 + 1) + 100);
        int assassinMoveTwoDmg = (int) (Math.random() * (400 - 200 + 1) + 200);
        int assassinMoveThreeDmg = (int) (Math.random() * (1800 - 1000 + 1) + 1000);

        if (player.getCharacterClass() == Characters.Archer) {
            characterClassUpdateLayout(player, btnMove, archerMoveOneDmg, archerMoveTwoDmg, archerMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.Warrior) {
            characterClassUpdateLayout(player, btnMove, warriorMoveOneDmg, warriorMoveTwoDmg, warriorMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.Wizard) {
            characterClassUpdateLayout(player, btnMove, wizardMoveOneDmg, wizardMoveTwoDmg, wizardMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.HorseMan) {
            characterClassUpdateLayout(player, btnMove, horseManMoveOneDmg, horseManMoveTwoDmg, horseManMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.Assassin) {
            characterClassUpdateLayout(player, btnMove, assassinMoveOneDmg, assassinMoveTwoDmg, assassinMoveThreeDmg);
        }
    }

    private void characterClassUpdateLayout(PlayerInfo player, Button btnMove, int archerMoveOneDmg, int archerMoveTwoDmg, int archerMoveThreeDmg) {
        if (btnMove == btnPlayerOneMoveOne || btnMove == btnPlayerTwoMoveOne) {
            playerShowLostHp(player, archerMoveOneDmg);
            player.setHealthPoints(player.getHealthPoints() - archerMoveOneDmg);
            setTextForPlayer(player);
        } else if (btnMove == btnPlayerOneMoveTwo || btnMove == btnPlayerTwoMoveTwo) {
            playerShowLostHp(player, archerMoveTwoDmg);
            player.setHealthPoints(player.getHealthPoints() - archerMoveTwoDmg);
            setTextForPlayer(player);
        } else if (btnMove == btnPlayerOneMoveThree || btnMove == btnPlayerTwoMoveThree) {
            playerShowLostHp(player, archerMoveThreeDmg);
            player.setHealthPoints(player.getHealthPoints() - archerMoveThreeDmg);
            setTextForPlayer(player);
        }
    }

    private void playerShowLostHp(PlayerInfo player, int classMoveOneDmg) {
        if (player == playerTwo) {
            lbPlayerTwoDmgDeal.setText("-" + classMoveOneDmg);
        } else if (player == playerOne) {
            lbPlayerOneDmgDeal.setText("-" + classMoveOneDmg);
        }
    }

    private void playerDisableUsedAttackThree(PlayerInfo player) {
        if (player == playerOne) {
            btnPlayerOneMoveOne.setDisable(false);
            btnPlayerOneMoveOne.setTextFill(Color.BLACK);
            btnPlayerOneMoveTwo.setDisable(false);
            btnPlayerOneMoveTwo.setTextFill(Color.BLACK);
            btnPlayerOneMoveThree.setDisable(true);
            btnPlayerOneMoveThree.setTextFill(Color.RED);
        } else if (player == playerTwo) {
            btnPlayerTwoMoveOne.setDisable(false);
            btnPlayerTwoMoveOne.setTextFill(Color.BLACK);
            btnPlayerTwoMoveTwo.setDisable(false);
            btnPlayerTwoMoveTwo.setTextFill(Color.BLACK);
            btnPlayerTwoMoveThree.setDisable(true);
            btnPlayerTwoMoveThree.setTextFill(Color.RED);
        }
    }

    private void playerOneMovesVisible() {
        btnPlayerTwoMoveOne.setVisible(false);
        btnPlayerTwoMoveTwo.setVisible(false);
        btnPlayerTwoMoveThree.setVisible(false);
        btnPlayerOneMoveOne.setVisible(true);
        btnPlayerOneMoveTwo.setVisible(true);
        btnPlayerOneMoveThree.setVisible(true);
    }

    private void playerTwoMovesVisible() {
        btnPlayerTwoMoveOne.setVisible(true);
        btnPlayerTwoMoveTwo.setVisible(true);
        btnPlayerTwoMoveThree.setVisible(true);
        btnPlayerOneMoveOne.setVisible(false);
        btnPlayerOneMoveTwo.setVisible(false);
        btnPlayerOneMoveThree.setVisible(false);
    }
}
