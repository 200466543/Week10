module com.example.week10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    opens com.example.week10 to javafx.fxml, com.google.gson;
    exports com.example.week10;
}