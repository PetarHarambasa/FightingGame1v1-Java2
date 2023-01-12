package hr.algebra.java2.fightinggame1v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReplayScreen {
    @FXML
    private TextArea replayTextArea;

    @FXML
    void startReplay(ActionEvent event) {
        new Thread(() -> {
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

                StringBuilder moveReplayBuilder = new StringBuilder();

                for (int i = 0; i < nodeListPlayerOne.getLength(); i++) {
                    if (i <= nodeListPlayerOne.getLength()) {
                        String playerOneMoveString = nodeListPlayerOne.item(i).getTextContent();
                        moveReplayBuilder.append("Player one move: " + playerOneMoveString);
                        moveReplayBuilder.append("\n");
                        replayTextArea.setText(moveReplayBuilder.toString());
                        Thread.sleep(1000);
                    }

                    if (i < nodeListPlayerTwo.getLength()) {
                        String playerTwoMoveString = nodeListPlayerTwo.item(i).getTextContent();
                        moveReplayBuilder.append("Player two move: " + playerTwoMoveString);
                        moveReplayBuilder.append("\n");
                        replayTextArea.setText(moveReplayBuilder.toString());
                        Thread.sleep(1000);
                    }
                }

                replayTextArea.setText(String.valueOf(moveReplayBuilder.append("Replay finished!")));
            } catch (IOException | ParserConfigurationException | SAXException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
