package hr.algebra.java2.fightinggame1v1;

import hr.algebra.java2.model.*;
import hr.algebra.java2.utils.ClassPLayerUtil;
import hr.algebra.java2.utils.FileUtils;
import hr.algebra.java2.utils.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CharacterSelectionScreen implements Initializable {
    @FXML
    private Label lbErrorMessage;
    @FXML
    private TextField tfPlayerOneName;
    @FXML
    private TextField tfPlayerTwoName;
    @FXML
    private ComboBox<Characters> cbPlayerOneClass;
    @FXML
    private ComboBox<Characters> cbPlayerTwoClass;
    @FXML
    private ImageView imPlayerOneImage;
    @FXML
    private ImageView imPlayerTwoImage;
    private static PlayerInfo playerOneInfo;
    private static PlayerInfo playerTwoInfo;
    public static PlayerInfo getPlayerOneInfo() {
        return playerOneInfo;
    }

    public static PlayerInfo getPlayerTwoInfo() {
        return playerTwoInfo;
    }

    public static Archer archer = Archer.getInstance();
    public static Warrior warrior = Warrior.getInstance();
    public static Wizard wizard = Wizard.getInstance();
    public static HorseMan horseMan = HorseMan.getInstance();
    public static Assassin assassin = Assassin.getInstance();

    public ObservableList<Characters> characterClasses(final int numberOfEntries) {
        final ObservableList<Characters> dataCharacterClasses = FXCollections.observableArrayList();
        dataCharacterClasses.addAll(Arrays.asList(Characters.values()).subList(0, numberOfEntries));
        return dataCharacterClasses;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbPlayerOneClass.setItems(characterClasses(Characters.values().length));
        cbPlayerTwoClass.setItems(characterClasses(Characters.values().length));
    }

    public void startGame() throws IOException {
        if (isValid()) {
            playerOneInfo = new PlayerInfo(tfPlayerOneName.getText(), cbPlayerOneClass.getValue(), ClassPLayerUtil.selectedHp(cbPlayerOneClass.getValue()), imPlayerOneImage.getImage());
            playerTwoInfo = new PlayerInfo(tfPlayerTwoName.getText(), cbPlayerTwoClass.getValue(), ClassPLayerUtil.selectedHp(cbPlayerTwoClass.getValue()), imPlayerTwoImage.getImage());

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Settings.getFILENAME_MAIN_GAME()));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            HelloApplication.getMainStage().setTitle(Settings.getSTAGE_TITLE());
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        }
    }

    private boolean isValid() {
        if (!tfPlayerOneName.getText().equals("") && !tfPlayerTwoName.getText().equals("") && cbPlayerOneClass.getValue() != null && cbPlayerTwoClass.getValue() != null) {
            return true;
        } else {
            lbErrorMessage.setText(Settings.getERROR_MESSAGE_CHAR_NOT_VALID());
            return false;
        }
    }


    public void onCharacterClassChanged() throws FileNotFoundException {

        Image archerImage = FileUtils.getImage(archer.getIMAGE_PATH());
        Image warriorImage = FileUtils.getImage(warrior.getIMAGE_PATH());
        Image wizardImage = FileUtils.getImage(wizard.getIMAGE_PATH());
        Image horseManImage = FileUtils.getImage(horseMan.getIMAGE_PATH());
        Image assassinImage = FileUtils.getImage(assassin.getIMAGE_PATH());

        checkPlayerClass(cbPlayerOneClass.getValue(), imPlayerOneImage, archerImage, warriorImage, wizardImage, horseManImage, assassinImage);
        checkPlayerClass(cbPlayerTwoClass.getValue(), imPlayerTwoImage, archerImage, warriorImage, wizardImage, horseManImage, assassinImage);
    }

    private void checkPlayerClass(Characters cbPlayerClass, ImageView playerImage, Image archerImage, Image warriorImage, Image wizardImage, Image horseManImage, Image assassinImage) {
        if (cbPlayerClass == Characters.Archer) {
            playerImage.setImage(archerImage);
        } else if (cbPlayerClass == Characters.Warrior) {
            playerImage.setImage(warriorImage);
        } else if (cbPlayerClass == Characters.Wizard) {
            playerImage.setImage(wizardImage);
        } else if (cbPlayerClass == Characters.HorseMan) {
            playerImage.setImage(horseManImage);
        } else if (cbPlayerClass == Characters.Assassin) {
            playerImage.setImage(assassinImage);
        }
    }
}
