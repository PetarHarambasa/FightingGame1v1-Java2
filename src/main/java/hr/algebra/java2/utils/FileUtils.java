package hr.algebra.java2.utils;

import hr.algebra.java2.model.PlayerInfo;
import hr.algebra.java2.model.SerializablePlayer;
import javafx.scene.image.Image;

import java.io.*;
import java.util.List;

public class FileUtils {
    static File f3 = new File("savedGamePlayerOne.ser");
    static File f4 = new File("savedGamePlayerTwo.ser");
    public static Image getImage(String path) throws FileNotFoundException {
        FileInputStream fileStream = new FileInputStream(path);

        return new Image(fileStream);
    }

    public static void saveCurrentGame(PlayerInfo playerOne, PlayerInfo playerTwo) throws IOException {

        f3.delete();
        f4.delete();

        SerializablePlayer serializablePlayerOne = new SerializablePlayer(playerOne.getPlayerName(),
                playerOne.getCharacterClass(), playerOne.getHealthPoints(), playerOne.getNumberOfWins(),
                playerOne.getBestVictoryTime(), playerOne.getMovesList());
        SerializablePlayer serializablePlayerTwo = new SerializablePlayer(playerTwo.getPlayerName(),
                playerTwo.getCharacterClass(), playerTwo.getHealthPoints(), playerTwo.getNumberOfWins(),
                playerTwo.getBestVictoryTime(), playerTwo.getMovesList());

        try (ObjectOutputStream playerOneSerializator = new ObjectOutputStream(
                new FileOutputStream("savedGamePlayerOne.ser"))) {
            playerOneSerializator.writeObject(serializablePlayerOne);
        }
        try (ObjectOutputStream playerTwoSerializator = new ObjectOutputStream(
                new FileOutputStream("savedGamePlayerTwo.ser"))) {
            playerTwoSerializator.writeObject(serializablePlayerTwo);
        }

    }

    public static void saveCurrentGameMoves(List<String> movesListPlayerOne, List<String> movesListPlayerTwo) throws IOException {

        try (ObjectOutputStream playerOneMovesSerializator = new ObjectOutputStream(
                new FileOutputStream("savedGamePlayerOneMoves.ser"))) {
            playerOneMovesSerializator.writeObject(movesListPlayerOne);
        }
        try (ObjectOutputStream playerTwoMovesSerializator = new ObjectOutputStream(
                new FileOutputStream("savedGamePlayerTwoMoves.ser"))) {
            playerTwoMovesSerializator.writeObject(movesListPlayerTwo);
        }

    }
}
