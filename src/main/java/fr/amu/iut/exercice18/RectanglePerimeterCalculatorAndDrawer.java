package fr.amu.iut.exercice18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class RectanglePerimeterCalculatorAndDrawer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fenetrePerimetre.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Perimeter Calculator And Drawer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
