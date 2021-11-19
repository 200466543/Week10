package com.example.week10;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Movie> initialMovieDataListView;

    @FXML
    private ImageView posterImageView;

    @FXML
    private Label errMsgLabel;

    @FXML
    private Button getDetailsButton;

    @FXML
    private void getSearchResults() throws IOException, InterruptedException {
        initialMovieDataListView.getItems().clear();
        ApiResponse apiResponse = APIUtility.getMoviesFromOMDB(searchTextField.getText());
        if (apiResponse != null)
        {
            initialMovieDataListView.getItems().addAll(apiResponse.getSearch());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialMovieDataListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldMovie, movieSelected) -> {
                    try{
                        posterImageView.setImage(new Image(movieSelected.getPoster()));
                    }catch(Exception e)
                    {

                    }

                });
    }
}
