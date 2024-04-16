package DictionaryApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private AnchorPane pane2;

    @FXML
    private Button quizButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        quizButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("Quiz.fxml");
            }
        });
    }

    private void showComponent (String path){
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            setNode(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNode(Node node) {
        pane2.getChildren().clear();
        pane2.getChildren().add(node);
    }

}
