package com.example.week10;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class MovieDetailsViewController {

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label runTimeLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label writersLabel;

    @FXML
    private ListView<?> actorsListView;

    @FXML
    private ListView<?> ratingsListView;

    @FXML
    private ImageView imageView;

}
