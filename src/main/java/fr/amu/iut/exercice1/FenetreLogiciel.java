package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // code de  l'exercice 1
        BorderPane root = new BorderPane();

        //Barre de menus
        MenuBar MenuBar = new MenuBar();
        Menu File = new Menu("File");
        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");
        MenuItem Close = new MenuItem("Close");
        Menu Edit = new Menu("Edit");
        MenuItem Cut = new MenuItem("Cut");
        MenuItem Copy = new MenuItem("Copy");
        MenuItem Paste = new MenuItem("Paste");
        Menu Help = new Menu("Help");

        File.getItems().addAll(New, Open, Save, Close);
        Edit.getItems().addAll(Cut, Copy, Paste);

        MenuBar.getMenus().addAll(File,Edit,Help);

        root.setTop(MenuBar);


        //Liste boutons
        HBox hbox = new HBox();
        VBox vBox = new VBox();
        Label Boutons = new Label("Boutons :");
        Button Bouton1 = new Button("Bouton 1");
        Button Bouton2 = new Button("Bouton 2");
        Button Bouton3 = new Button("Bouton 3");

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        vBox.getChildren().addAll(Boutons,Bouton1,Bouton2,Bouton3);
        vBox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(vBox, separator);

        root.setLeft(hbox);

        //Formulaire
        GridPane gridPane = new GridPane();
        Label Name = new Label("Name : ");
        TextField name = new TextField();
        Label Email = new Label("Email : ");
        TextField email = new TextField();
        Label Password = new Label("Password : ");
        TextField password = new TextField();

        gridPane.add(Name,0,0);
        gridPane.add(name,1,0);
        gridPane.add(Email,0,1);
        gridPane.add(email,1,1);
        gridPane.add(Password,0,2);
        gridPane.add(password,1,2);

        gridPane.setAlignment(Pos.CENTER);

        root.setCenter(gridPane);


        Scene scene = new Scene(root, 800, 500);

        // Définir la scène principale de l'application
        primaryStage.setScene(scene);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

