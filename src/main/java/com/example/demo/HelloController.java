package com.example.demo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;


public class HelloController extends Application {
    public Text time;
    int flag = 0;
    static Timer timer;
    public TextField timeM;
    public TextField timeS;
    public int seconds;
    public  int minutes;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void pressButtonStart(ActionEvent event) {
        timer.start();
        seconds = Integer.parseInt(timeS.getText());
        minutes = Integer.parseInt(timeM.getText());
    }

    public void pressButtonStop(ActionEvent event) {
        flag = 1 - flag;
        timer.stop();
    }

    private String timerStart() {
        seconds--;
        if (seconds < 0) {
            minutes--;
            seconds = 59;
        }
        if (seconds == 0 && minutes == 0) {
            time.setFill(Color.RED);
            timer.stop();
            return "TIME IS OUT";
        }

        return (minutes < 10 ? "0" + minutes : minutes) +
                ":" + (seconds < 10 ? "0" + seconds : seconds);
    }

    public void pressButtonReset(ActionEvent actionEvent) {
        seconds = Integer.parseInt(timeS.getText());
        minutes = Integer.parseInt(timeM.getText());
        time.setText(timerStart());
    }

    public HelloController() throws NullPointerException {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() throws NullPointerException {
                        time.setText(timerStart());
                    }
                });
            }
        };
        timer = new Timer(1000, taskPerformer);
    }
}
