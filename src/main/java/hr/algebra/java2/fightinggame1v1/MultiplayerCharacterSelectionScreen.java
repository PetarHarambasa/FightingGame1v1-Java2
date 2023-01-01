package hr.algebra.java2.fightinggame1v1;

import hr.algebra.java2.jndi.JndiHelper;
import hr.algebra.java2.jndi.ServerConfigurationKey;
import hr.algebra.java2.model.*;
import hr.algebra.java2.server.Server;
import hr.algebra.java2.thread.ClientThread;
import hr.algebra.java2.utils.ClassPLayerUtil;
import hr.algebra.java2.utils.FileUtils;
import hr.algebra.java2.utils.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.naming.NamingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiplayerCharacterSelectionScreen implements Initializable {
    @FXML
    private ComboBox<Characters> cbPlayerClass;

    @FXML
    private ImageView imPlayerImage;

    @FXML
    private Label lbErrorMessage;

    @FXML
    private TextField tfPlayerName;
    public static Archer archer = Archer.getInstance();
    public static Warrior warrior = Warrior.getInstance();
    public static Wizard wizard = Wizard.getInstance();
    public static HorseMan horseMan = HorseMan.getInstance();
    public static Assassin assassin = Assassin.getInstance();
    public static PlayerInfo playerInfo;
    public static SerializablePlayer serializablePlayerInfo;
    private String playerId;

    public static PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
    public static SerializablePlayer getSerializablePlayerInfo() {
        return serializablePlayerInfo;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public ObservableList<Characters> characterClasses(final int numberOfEntries) {
        final ObservableList<Characters> dataCharacterClasses = FXCollections.observableArrayList();
        dataCharacterClasses.addAll(Arrays.asList(Characters.values()).subList(0, numberOfEntries));
        return dataCharacterClasses;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbPlayerClass.setItems(characterClasses(Characters.values().length));
    }

    @FXML
    void onCharacterClassChanged(ActionEvent event) throws FileNotFoundException {
        Image archerImage = FileUtils.getImage(archer.getIMAGE_PATH());
        Image warriorImage = FileUtils.getImage(warrior.getIMAGE_PATH());
        Image wizardImage = FileUtils.getImage(wizard.getIMAGE_PATH());
        Image horseManImage = FileUtils.getImage(horseMan.getIMAGE_PATH());
        Image assassinImage = FileUtils.getImage(assassin.getIMAGE_PATH());

        checkPlayerClass(cbPlayerClass.getValue(), imPlayerImage, archerImage, warriorImage, wizardImage, horseManImage, assassinImage);
    }

    private void checkPlayerClass(Characters cbClass, ImageView imPlayerImage, Image archerImage, Image warriorImage, Image wizardImage, Image horseManImage, Image assassinImage) {
        if (cbClass == Characters.Archer) {
            imPlayerImage.setImage(archerImage);
        } else if (cbClass == Characters.Warrior) {
            imPlayerImage.setImage(warriorImage);
        } else if (cbClass == Characters.Wizard) {
            imPlayerImage.setImage(wizardImage);
        } else if (cbClass == Characters.HorseMan) {
            imPlayerImage.setImage(horseManImage);
        } else if (cbClass == Characters.Assassin) {
            imPlayerImage.setImage(assassinImage);
        }
    }

    @FXML
    void startGame(ActionEvent event) throws NamingException, IOException {

        if (isValid()) {

            playerInfo = new PlayerInfo(tfPlayerName.getText(), cbPlayerClass.getValue(), ClassPLayerUtil.selectedHp(cbPlayerClass.getValue()), imPlayerImage.getImage());

            serializablePlayerInfo = new SerializablePlayer(tfPlayerName.getText(), cbPlayerClass.getValue(), ClassPLayerUtil.selectedHp(cbPlayerClass.getValue()));

            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.execute(new ClientThread(new GameStateDto()));

            int SERVER_PORT = Integer.parseInt(JndiHelper.getConfigurationParameter(ServerConfigurationKey.SERVER_PORT));

            try (Socket clientSocket = new Socket(Server.HOST, SERVER_PORT)) {
                System.err.println("Client is connecting to " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                oos.writeObject(serializablePlayerInfo);

                System.out.println("Client sent message back to the server!");
            }catch (IOException e) {
                e.printStackTrace();
            }

            FXMLLoader fxmlLoader = new FXMLLoader(StartMultiPlayer.class.getResource(Settings.getFILENAME_MAIN_GAME_MULTIPLAYER()));
            Scene scene = new Scene(fxmlLoader.load(),1015,600);
            StartMultiPlayer.getMainStage().setTitle(Settings.getSTAGE_TITLE());
            StartMultiPlayer.getMainStage().setScene(scene);
            StartMultiPlayer.getMainStage().show();
        }
    }

    private boolean isValid() {
        if (!tfPlayerName.getText().equals("") && !tfPlayerName.getText().equals("") && cbPlayerClass.getValue() != null && cbPlayerClass.getValue() != null) {
            return true;
        } else {
            lbErrorMessage.setText(Settings.getERROR_MESSAGE_CHAR_NOT_VALID());
            return false;
        }
    }
}
