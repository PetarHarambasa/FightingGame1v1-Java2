package hr.algebra.java2.fightinggame1v1;

import hr.algebra.java2.utils.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static hr.algebra.java2.utils.Settings.getFILENAME_MULTIPLAYER_CHAR_SELECTION;

public class StartMultiPlayer extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Settings.getFILENAME_MULTIPLAYER_CHAR_SELECTION()));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        MultiplayerCharacterSelectionScreen multiplayerCharacterSelectionScreen = fxmlLoader.getController();
        multiplayerCharacterSelectionScreen.setPlayerId("123");
        stage.setTitle(Settings.getSTAGE_TITLE());
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.out.println("PlayerID is required!");
            System.exit(1);
        }

        String playerID = args[0];

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(getFILENAME_MULTIPLAYER_CHAR_SELECTION()));
        Parent root = fxmlLoader.load();
        MultiplayerCharacterSelectionScreen multiplayerCharacterSelectionScreen =  fxmlLoader.getController();
        multiplayerCharacterSelectionScreen.setPlayerId(playerID);

        System.out.println("Process ID: " + ProcessHandle.current().pid());

        launch();
    }
}
