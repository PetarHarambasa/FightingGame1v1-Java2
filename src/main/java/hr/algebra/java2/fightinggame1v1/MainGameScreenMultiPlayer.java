package hr.algebra.java2.fightinggame1v1;

import hr.algebra.java2.jndi.JndiHelper;
import hr.algebra.java2.jndi.ServerConfigurationKey;
import hr.algebra.java2.model.*;
import hr.algebra.java2.rmi.ChatService;
import hr.algebra.java2.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


public class MainGameScreenMultiPlayer {
    @FXML
    private Button btnPlayerMoveOne;

    @FXML
    private Button btnPlayerMoveThree;

    @FXML
    private Button btnPlayerMoveTwo;

    @FXML
    private TextField chatMessageTextField;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private ImageView imPlayerClassIcon;

    @FXML
    private Label lbPlayerInfo;

    @FXML
    private Label lbPlayerDmgDeal;
    public static Archer archer = Archer.getInstance();
    public static Warrior warrior = Warrior.getInstance();
    public static Wizard wizard = Wizard.getInstance();
    public static HorseMan horseMan = HorseMan.getInstance();
    public static Assassin assassin = Assassin.getInstance();

    public PlayerInfo playerInfo = MultiplayerCharacterSelectionScreen.getPlayerInfo();
    public SerializablePlayer serializablePlayerInfo = MultiplayerCharacterSelectionScreen.getSerializablePlayerInfo();

    private ChatService stub = null;

    public void initialize() {
        try {
            int RMI_SERVER_PORT = Integer.parseInt(JndiHelper.getConfigurationParameter(ServerConfigurationKey.RMI_SERVER_PORT));

            Registry registry = LocateRegistry.getRegistry("localhost", RMI_SERVER_PORT);
            stub = (ChatService) registry.lookup(ChatService.REMOTE_OBJECT_NAME);
        } catch (IOException | NotBoundException | NamingException e) {
            throw new RuntimeException(e);
        }

        setInfoForPlayer(playerInfo);

    }

    private void setInfoForPlayer(PlayerInfo player) {
        lbPlayerInfo.setText(player.getPlayerName() + " " + player.getCharacterClass() +
                ", HP: " + player.getHealthPoints());
        imPlayerClassIcon.setImage(playerInfo.getPlayerImage());
        abilityClassCheck(playerInfo.getCharacterClass(), btnPlayerMoveOne, btnPlayerMoveTwo, btnPlayerMoveThree);
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

    private int getRandomAbility(List<String> abilityList) {
        return (int) Math.floor(Math.random() * (abilityList.size()));
    }

    @FXML
    public void sendMessage() {
        messageProvider();
    }

    @FXML
    public void onEnterSendMessage(KeyEvent e) {
        if (e.getCode().toString().equals("TAB")) {
            messageProvider();
            chatMessageTextField.requestFocus();
        }
    }

    private void messageProvider() {
        try {
            if (!chatMessageTextField.getText().equals("")) {
                stub.sendMessage(playerInfo.getPlayerName() + " (" + playerInfo.getCharacterClass() + ") " + " > " + chatMessageTextField.getText());
                List<String> chatHistory = stub.getChatHistory();
                StringBuilder chatHistoryBuilder = new StringBuilder();
                for (String message : chatHistory) {
                    chatHistoryBuilder.append(message);
                    chatHistoryBuilder.append("\n");
                }
                chatTextArea.setText(chatHistoryBuilder.toString());
            }
            chatMessageTextField.clear();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void attackOneButtonPressed(ActionEvent event) throws NamingException, IOException {
        playerDisableUsedAttackOne();
        abilityClassCheck(playerInfo.getCharacterClass(), btnPlayerMoveOne, btnPlayerMoveTwo, btnPlayerMoveThree);
        updatePlayerHp(playerInfo, btnPlayerMoveOne);
    }

    private void updatePlayerHp(PlayerInfo player, Button btnPlayerMove) throws NamingException, IOException {
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
            characterClassUpdateLayout(player, btnPlayerMove, archerMoveOneDmg, archerMoveTwoDmg, archerMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.Warrior) {
            characterClassUpdateLayout(player, btnPlayerMove, warriorMoveOneDmg, warriorMoveTwoDmg, warriorMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.Wizard) {
            characterClassUpdateLayout(player, btnPlayerMove, wizardMoveOneDmg, wizardMoveTwoDmg, wizardMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.HorseMan) {
            characterClassUpdateLayout(player, btnPlayerMove, horseManMoveOneDmg, horseManMoveTwoDmg, horseManMoveThreeDmg);
        } else if (player.getCharacterClass() == Characters.Assassin) {
            characterClassUpdateLayout(player, btnPlayerMove, assassinMoveOneDmg, assassinMoveTwoDmg, assassinMoveThreeDmg);
        }
    }

    private void characterClassUpdateLayout(PlayerInfo player, Button btnPlayerMove, int archerMoveOneDmg, int archerMoveTwoDmg, int archerMoveThreeDmg) throws NamingException, IOException {
        if (btnPlayerMove == btnPlayerMoveOne) {
            int hp = player.getHealthPoints() - archerMoveOneDmg;
            playerShowLostHp(archerMoveOneDmg);
            player.setHealthPoints(hp);
            updateOnServerPlayer(hp);
            setInfoForPlayer(player);
        } else if (btnPlayerMove == btnPlayerMoveTwo) {
            int hp = player.getHealthPoints() - archerMoveTwoDmg;
            playerShowLostHp(archerMoveTwoDmg);
            player.setHealthPoints(hp);
            updateOnServerPlayer(hp);
            setInfoForPlayer(player);
        } else if (btnPlayerMove == btnPlayerMoveThree) {
            int hp = player.getHealthPoints() - archerMoveThreeDmg;
            playerShowLostHp(archerMoveThreeDmg);
            player.setHealthPoints(hp);
            updateOnServerPlayer(hp);
            setInfoForPlayer(player);
        }
    }

    private void playerShowLostHp(int classMoveDmg) {
        lbPlayerDmgDeal.setText("-" + classMoveDmg);
    }

    private void updateOnServerPlayer(int newHp) throws NamingException, IOException {
        int SERVER_PORT = Integer.parseInt(JndiHelper.getConfigurationParameter(ServerConfigurationKey.SERVER_PORT));

        try (Socket clientSocket = new Socket(Server.HOST, SERVER_PORT)) {
            System.err.println("Client is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

            oos.writeObject(serializablePlayerInfo);

            serializablePlayerInfo.setHealthPoints(newHp);

            lbPlayerInfo.setText(serializablePlayerInfo.getPlayerName() + " " + serializablePlayerInfo.getCharacterClass() +
                    ", HP: " + serializablePlayerInfo.getHealthPoints());

            System.out.println("Client sent message back to the server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playerDisableUsedAttackOne() {
        btnPlayerMoveOne.setDisable(true);
        btnPlayerMoveOne.setTextFill(Color.RED);
        btnPlayerMoveTwo.setDisable(false);
        btnPlayerMoveTwo.setTextFill(Color.BLACK);
        btnPlayerMoveThree.setDisable(false);
        btnPlayerMoveThree.setTextFill(Color.BLACK);
    }

    @FXML
    void attackThreeButtonPressed(ActionEvent event) throws NamingException, IOException {
        playerDisableUsedAttackThree();
        abilityClassCheck(playerInfo.getCharacterClass(), btnPlayerMoveOne, btnPlayerMoveTwo, btnPlayerMoveThree);
        updatePlayerHp(playerInfo, btnPlayerMoveThree);
    }

    private void playerDisableUsedAttackThree() {
        btnPlayerMoveOne.setDisable(false);
        btnPlayerMoveOne.setTextFill(Color.BLACK);
        btnPlayerMoveTwo.setDisable(false);
        btnPlayerMoveTwo.setTextFill(Color.BLACK);
        btnPlayerMoveThree.setDisable(true);
        btnPlayerMoveThree.setTextFill(Color.RED);
    }

    @FXML
    void attackTwoButtonPressed(ActionEvent event) throws NamingException, IOException {
        playerDisableUsedAttackTwo();
        abilityClassCheck(playerInfo.getCharacterClass(), btnPlayerMoveOne, btnPlayerMoveTwo, btnPlayerMoveThree);
        updatePlayerHp(playerInfo, btnPlayerMoveTwo);
    }

    private void playerDisableUsedAttackTwo() {
        btnPlayerMoveOne.setDisable(false);
        btnPlayerMoveOne.setTextFill(Color.BLACK);
        btnPlayerMoveTwo.setDisable(true);
        btnPlayerMoveTwo.setTextFill(Color.RED);
        btnPlayerMoveThree.setDisable(false);
        btnPlayerMoveThree.setTextFill(Color.BLACK);
    }

}
