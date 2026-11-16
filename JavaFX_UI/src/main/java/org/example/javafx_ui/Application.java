package org.example.javafx_ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Load the FXML from its new location
        FXMLLoader loader = new FXMLLoader(
                Application.class.getResource(
                        "/org/fxml/javafx_ui/login.fxml"
                )
        );

        Parent design = loader.load();

        // Original Scene Builder dimensions
        double originalWidth = 600;
        double originalHeight = 400;

        // Keep the original design size
        design.resize(originalWidth, originalHeight);

        // Create the window container
        Pane root = new Pane();
        root.getChildren().add(design);

        // Scale the entire interface
        Scale scale = new Scale(1, 1, 0, 0);
        design.getTransforms().add(scale);

        // Starting window dimensions
        Scene scene = new Scene(root, 900, 600);

        // Resize and center the interface
        Runnable updateLayout = () -> {

            double scaleX = root.getWidth() / originalWidth;
            double scaleY = root.getHeight() / originalHeight;

            double factor = Math.min(scaleX, scaleY);

            scale.setX(factor);
            scale.setY(factor);

            double scaledWidth = originalWidth * factor;
            double scaledHeight = originalHeight * factor;

            design.setLayoutX(
                    (root.getWidth() - scaledWidth) / 2
            );

            design.setLayoutY(
                    (root.getHeight() - scaledHeight) / 2
            );
        };

        root.widthProperty().addListener(
                (obs, oldVal, newVal) -> updateLayout.run()
        );

        root.heightProperty().addListener(
                (obs, oldVal, newVal) -> updateLayout.run()
        );

        stage.setTitle("FlightSync");
        stage.setScene(scene);

        stage.setMinWidth(600);
        stage.setMinHeight(400);

        stage.show();

        updateLayout.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
