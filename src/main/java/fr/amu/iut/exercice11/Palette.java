package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private IntegerProperty nbFois = new SimpleIntegerProperty();

    private StringProperty couleurActuelle = new SimpleStringProperty("vert");
    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;


    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        texteDuHaut.textProperty().bind(Bindings.createStringBinding(
                () -> couleurActuelle.get() + " cliqué " + nbFois.get() + " fois",
                nbFois, couleurActuelle
        ));

        vert.setOnMouseClicked(e -> {
            couleurActuelle.setValue("vert");
            nbVert = nbVert + 50;
            nbFois.setValue(nbVert/50);
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + "," + nbVert + "," + nbBleu + ");");

        });

        rouge.setOnMouseClicked(e -> {
            couleurActuelle.setValue("rouge");
            nbRouge = nbRouge + 50;
            nbFois.setValue(nbRouge/50);
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + "," + nbVert + "," + nbBleu + ");");
        });

        bleu.setOnMouseClicked(e -> {
            couleurActuelle.setValue("bleu");
            nbBleu = nbBleu + 50;
            nbFois.setValue(nbBleu/50);
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + "," + nbVert + "," + nbBleu + ");");

        });


        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

