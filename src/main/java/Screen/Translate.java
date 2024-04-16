package Screen;

import DictionaryCommandLine.api.Speech;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Translate extends App {
    @FXML
    private Button buttonBack;
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Tải file FXML
        Parent root = FXMLLoader.load(getClass().getResource("TranslateScreen.fxml"));
        TextArea textTarget = new TextArea();
        TextArea textExplain = new TextArea();

        // Xử lý sự kiện khi nhập liệu vào textTarget
        textTarget.setOnKeyReleased(event -> {
            // Lấy văn bản từ textTarget
            String text = textTarget.getText();

            // Dịch văn bản và hiển thị vào textExplain
            String translatedText = DictionaryCommandLine.api.Translate.translateText(text, "en", "vi");
            textExplain.setText(translatedText);
        });

        // Tạo layout
        VBox Translateroot = new VBox(textTarget, textExplain);
        // Thiết lập stage
        primaryStage.setTitle("Translation Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private AnchorPane translateEtoVPane;

    @FXML
    private AnchorPane translateVtoEPane;

    @FXML
    private TextArea text_target;

    @FXML
    private TextArea text_explain;
    private MediaPlayer mediaPlayer;
    private Media media;

    @FXML
    public void switchBack(javafx.event.ActionEvent actionEvent) {
        try {
            // Load the previous screen's FXML file
            Parent previousRoot = FXMLLoader.load(getClass().getResource("/Screen/AppScreen.fxml"));

            // Get the current stage
            Stage stage = (Stage) buttonBack.getScene().getWindow();

            // Create a new scene with the previous root
            Scene previousScene = new Scene(previousRoot);

            // Set the scene to the stage
            stage.setScene(previousScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isVietnameseToEnglish = true; // Mặc định khi khởi đầu là dịch từ tiếng Việt sang tiếng Anh

    // ExecutorService để thực hiện dịch văn bản sau một khoảng thời gian chờ
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @FXML
    public void initialize() {
        // Ẩn AnchorPane chuyển từ V-E ban đầu
        translateEtoVPane.setVisible(false);

        // Sử dụng TextChangeListener cho text_target
        text_target.textProperty().addListener((observable, oldValue, newValue) -> {
            // Hủy bỏ task hiện tại nếu có
            cancelTranslationTask();

            // Lên lịch task mới để dịch văn bản sau một khoảng thời gian chờ
            scheduleTranslationTask(newValue);
        });
    }

    @FXML
    private void swapButtonClicked() {
        // Lấy trạng thái hiện tại của hai AnchorPane
        boolean eToVVisible = translateEtoVPane.isVisible();
        boolean vToEVisible = translateVtoEPane.isVisible();

        // Đảo ngược trạng thái hiển thị của hai AnchorPane
        translateEtoVPane.setVisible(!eToVVisible);
        translateVtoEPane.setVisible(!vToEVisible);

        // Đảo ngược hướng dịch
        isVietnameseToEnglish = !isVietnameseToEnglish;
    }

    // Hủy bỏ task dịch văn bản
    private void cancelTranslationTask() {
        executorService.shutdownNow();
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    // Lên lịch task mới để dịch văn bản sau một khoảng thời gian chờ
    private void scheduleTranslationTask(String text) {
        Task<Void> translationTask = new Task<>() {
            @Override
            protected Void call() {
                try {
                    // Dịch văn bản và hiển thị vào text_explain
                    String translatedText;
                    if (isVietnameseToEnglish) {
                        translatedText = DictionaryCommandLine.api.Translate.translateText(text, "vi", "en");
                    } else {
                        translatedText = DictionaryCommandLine.api.Translate.translateText(text, "en", "vi");
                    }
                    // Cập nhật giao diện
                    updateUI(translatedText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        // Lên lịch task để chạy sau 100ms (hoặc bất kỳ khoảng thời gian nào khác)
        executorService.schedule(translationTask, 100, TimeUnit.MILLISECONDS);
    }

    // Cập nhật giao diện sau khi dịch văn bản
    private void updateUI(String translatedText) {
        text_explain.setText(translatedText);
    }
    @FXML
    private void soundButton1Clicked() {
        String textToSpeak = text_target.getText();
        if (textToSpeak != null && !textToSpeak.isEmpty()) { // Kiểm tra xem textToSpeak có được xác định không
            new Thread(() -> {
                try {
                    if (isVietnameseToEnglish) {
                        Speech.VietnameseAPISpeech(textToSpeak);
                    } else {
                        Speech.EnglishAPISpeech(textToSpeak);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            System.out.println("Text to speak is undefined.");
        }
    }
    @FXML
    private void soundButton2Clicked() {
        String textToSpeak = text_explain.getText();
        if (textToSpeak != null && !textToSpeak.isEmpty()) { // Kiểm tra xem textToSpeak có được xác định không
            new Thread(() -> {
                try {
                    if (isVietnameseToEnglish) {
                        Speech.EnglishAPISpeech(textToSpeak);
                    } else {
                        Speech.VietnameseAPISpeech(textToSpeak);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            System.out.println("Text to speak is undefined.");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}