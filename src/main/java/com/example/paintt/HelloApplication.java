package com.example.paintt;


//Tutorials verwendet: https://youtu.be/YQ8KDf8eyBU?si=rWlrpFS6Mun3t2FJ

//TODO: new Canvas fixen
//TODO: Speicherfunktion hinzufügen

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /**
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paint.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
         **/
        Parent root = FXMLLoader.load(getClass().getResource("paint.fxml"));

        stage.setTitle("Paint app");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}