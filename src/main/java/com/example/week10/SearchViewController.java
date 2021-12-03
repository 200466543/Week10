package com.example.week10;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private ProgressBar progressBar;

    @FXML
    private void getSearchResults() throws IOException, InterruptedException {
        initialMovieDataListView.getItems().clear();


        ApiResponse apiResponse = APIUtility.getMoviesFromOMDB(searchTextField.getText());
        if (apiResponse.getSearch() != null)
        {
            initialMovieDataListView.getItems().addAll(apiResponse.getSearchSorted());
            setMovieFound(true, false);
        } else
        {
            setMovieFound(false, false);
        }
    }

    /**
     * This method will turn visual element to be visible or not
     * visible on the state of the GUI
     * @param movieFound
     * @param movieSelected
     */
    private void setMovieFound(boolean movieFound, boolean movieSelected) {
        initialMovieDataListView.setVisible(movieFound);
        getDetailsButton.setVisible(movieSelected);
        posterImageView.setVisible(movieSelected);
        errMsgLabel.setVisible(!movieFound);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errMsgLabel.setVisible(false);
        setMovieFound(false, false);
        progressBar.setVisible(false);

        initialMovieDataListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldMovie, movieSelected) -> {
                    progressBar.setVisible(true);
                    Thread fetchPosterThread = new Thread(new Runnable() { // Thread to fetch poster art
                        @Override
                        public void run() {
                            double progress = 0;
                            for (int i=0; i<=10; i++){
                                //simulate computer doing work
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                progress += 0.1;

                                //create a final (non-changeable) variable to pass in the progress
                                //to the JavaFX thread
                                final double reportedProgress = progress;

                                //this is the JavaFX Thread. the method runLater() will execute
                                //once the thread is available
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (reportedProgress>.9) {
                                            progressBar.setVisible(false);
                                            try{
                                                //Thread.sleep(5000);
                                                posterImageView.setImage(new Image(movieSelected.getPoster()));
                                                setMovieFound(true, true);
                                            }catch(Exception e)
                                            {
                                                // add a default poster
                                            }
                                        }
                                        progressBar.setProgress(reportedProgress);
                                    }
                                });
                            }
                        }
                    });
                    fetchPosterThread.start();

                });
    }

    @FXML
    private void getMovieDetails(ActionEvent event) throws IOException, InterruptedException {
        String movieId = initialMovieDataListView.getSelectionModel().getSelectedItem().getImdbID();
        SceneChanger.changeScenes(event, "movie-details-view.fxml", movieId);
    }
}
