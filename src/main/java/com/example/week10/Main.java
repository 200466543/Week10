package com.example.week10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
        //ApiResponse result = APIUtility.getMoviesJsonFile();
        //System.out.println(result);
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Search our Movie Database!");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }

}
