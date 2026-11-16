module org.example.javafx_ui {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.javafx_ui to javafx.fxml;
    exports org.example.javafx_ui;
}
