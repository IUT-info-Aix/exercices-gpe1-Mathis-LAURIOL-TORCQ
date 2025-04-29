package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;



    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        root.setPrefSize(400, 200);

        label = new Label();
        label.setPrefSize(400, 20);
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(10, 10, 10, 10));

        panneau = new Pane();

        bas = new HBox();
        bas.setAlignment(Pos.CENTER);
        bas.setSpacing(10);
        bas.setPadding(new Insets(10));

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnMouseClicked(e -> {
            nbVert = nbVert + 50;
            label.setText("Vert choisi " + nbVert/50);
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + "," + nbVert + "," + nbBleu + ");");

        });

        rouge.setOnMouseClicked(e -> {
            nbRouge = nbRouge + 50;
            label.setText("Rouge choisi " + nbRouge/50);
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + "," + nbVert + "," + nbBleu + ");");
        });

        bleu.setOnMouseClicked(e -> {
            nbBleu = nbBleu + 50;
            label.setText("Bleu choisi " + nbBleu/50);
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + "," + nbVert + "," + nbBleu + ");");

        });



        bas.getChildren().addAll(vert, rouge, bleu);
        root.setTop(label);
        root.setCenter(panneau);
        root.setBottom(bas);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

