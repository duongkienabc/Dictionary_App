package DictionaryApp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {

    @FXML
    private AnchorPane pane1;

    @FXML
    private Button homeButton, translateButton, gameButton, markButton, settingButton;

    @FXML
    private ImageView homeImage, translateImage, gameImage, markImage, settingImage, Background;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("Game.fxml");
            }
        });
    }

    private void showComponent(String path) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            setNode(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNode(Node node) {
        pane1.getChildren().clear();
        pane1.getChildren().add(node);
    }

}
