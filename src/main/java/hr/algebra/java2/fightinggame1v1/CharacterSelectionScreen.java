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

import static hr.algebra.java2.utils.Messenger.*;
import static hr.algebra.java2.utils.ReflectionUtils.*;

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
            Scene scene = new Scene(fxmlLoader.load(),1244,600);
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

    public void loadGame() {

        File f1 = new File("savedGamePlayerOneMoves.ser");
        File f2 = new File("savedGamePlayerTwoMoves.ser");
        File f3 = new File("savedGamePlayerOne.ser");
        File f4 = new File("savedGamePlayerTwo.ser");

        try (ObjectInputStream playerOneDeserializator = new ObjectInputStream((
                new FileInputStream("savedGamePlayerOne.ser")
        ))) {
            SerializablePlayer PlayerOneData = (SerializablePlayer) playerOneDeserializator.readObject();
            try (ObjectInputStream playerTwoDeserializator = new ObjectInputStream((
                    new FileInputStream("savedGamePlayerTwo.ser")
            ))) {
                SerializablePlayer PlayerTwoData = (SerializablePlayer) playerTwoDeserializator.readObject();
                continueGame(PlayerOneData, PlayerTwoData);
            }

        }
        catch (IOException | ClassNotFoundException e) {
            showLoadingErrorMessage();
        }

        f1.delete();
        f2.delete();
        f3.delete();
        f4.delete();
    }

    private void continueGame(SerializablePlayer playerOne, SerializablePlayer playerTwo) throws IOException {

        playerOneInfo =  new PlayerInfo(playerOne.getPlayerName(), playerOne.getCharacterClass(), playerOne.getHealthPoints(), setPlayerImageOnLoad(playerOne), playerOne.getNumberOfWins(), playerOne.getBestVictoryTime(), playerOne.getMovesList());
        playerTwoInfo =  new PlayerInfo(playerTwo.getPlayerName(), playerTwo.getCharacterClass(), playerTwo.getHealthPoints(), setPlayerImageOnLoad(playerTwo), playerTwo.getNumberOfWins(), playerTwo.getBestVictoryTime(), playerTwo.getMovesList());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Settings.getFILENAME_MAIN_GAME()));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HelloApplication.getMainStage().setTitle(Settings.getSTAGE_TITLE());
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    private Image setPlayerImageOnLoad(SerializablePlayer playerOne) throws FileNotFoundException {
        if (playerOne.getCharacterClass() == Characters.Archer){
            return FileUtils.getImage(archer.getIMAGE_PATH());
        } else if(playerOne.getCharacterClass() == Characters.Warrior){
            return FileUtils.getImage(warrior.getIMAGE_PATH());
        }else if(playerOne.getCharacterClass() == Characters.Wizard){
            return FileUtils.getImage(wizard.getIMAGE_PATH());
        }else if(playerOne.getCharacterClass() == Characters.HorseMan) {
            return FileUtils.getImage(horseMan.getIMAGE_PATH());
        }else {
            return FileUtils.getImage(assassin.getIMAGE_PATH());
        }
    }

    public void generateDocumentation(){
        StringBuilder builder = new StringBuilder();

        builder.append("<!DOCTYPE html>");
        builder.append("<html>");
        builder.append("<head>");
        builder.append("<title>Dokumentacija igre</title>");
        builder.append("</head>");
        builder.append("<body>");
        builder.append("<h1>Dokumentacija projektnog zadatka</h1>");
        builder.append("<h3>Popis apstraktnih klasa:</h3>");
        appendDivToBuilder(CharacterClass.class, builder);
        builder.append("<h3>Popis klasa:</h3>");
        appendDivToBuilder(Archer.class,builder);
        appendDivToBuilder(Warrior.class,builder);
        appendDivToBuilder(Wizard.class,builder);
        appendDivToBuilder(HorseMan.class,builder);
        appendDivToBuilder(Assassin.class,builder);
        appendDivToBuilder(PlayerInfo.class,builder);
        appendDivToBuilder(Characters.class,builder);
        appendDivToBuilder(SerializablePlayer.class,builder);


        builder.append("</body>");
        builder.append("</html>");


        try (FileWriter fw = new FileWriter("documentation.html")) {
            fw.write(builder.toString());

            showDocumentCreationSuccessMessage();

        } catch (IOException e) {
            showDocumentCreationErrorMessage(e.getMessage());
        }
    }

    private static void appendDivToBuilder(Class<?> clazz, StringBuilder builder) {
        readClassInfo(clazz, builder);
        builder.append("<div style='margin-left: 25px;'>");
        builder.append("<p>Properties:</p>");
        appendFields(clazz, builder);
        builder.append("<p style='margin-top: 25px;'>Methods:</p>");
        appendMethods(clazz, builder);
        builder.append("<p style='margin-top: 25px;'>Constructors:</p>");
        appendConstructors(clazz, builder);
        builder.append("<p></p>");
        builder.append("</div>");
    }
}
