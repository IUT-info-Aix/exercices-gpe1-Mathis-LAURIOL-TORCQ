package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;


public class IHMPendu extends Application {

    Dico dico = new Dico();
    private final String motSecret = dico.getMot();
    private StringBuilder motAffiche = new StringBuilder("******");
    private int vies = 7;

    private Label labelVie;
    private Label labelMot;
    private Label penduLabel;
    private GridPane clavier;
    private Button rejouerBtn;


    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #D6FCF9;");

        penduLabel = new Label("Pendu : 0 / 7");
        penduLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        labelVie = new Label("Nombre de vies : " + vies);
        labelVie.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        labelMot = new Label(motAffiche.toString());
        labelMot.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        clavier = createClavier();

        rejouerBtn = new Button("Rejouer");
        rejouerBtn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rejouerBtn.setStyle("-fx-background-color: #DFF; -fx-text-fill: orange;");
        rejouerBtn.setOnAction(e -> resetGame());

        root.getChildren().addAll(penduLabel, labelVie, labelMot, clavier, rejouerBtn);

        Scene scene = new Scene(root, 450, 600);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createClavier() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        String letters = "abcdefghijklmnopqrstuvwxyz";
        int col = 0, row = 0;
        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            Button btn = new Button(String.valueOf(c));
            btn.setPrefSize(40, 40);
            btn.setStyle("-fx-background-color: #EFFFFF; -fx-border-color: #BFDADA; -fx-border-radius: 10; -fx-background-radius: 10;");
            btn.setOnAction(e -> {
                btn.setDisable(true);
                handleGuess(c);
            });

            grid.add(btn, col, row);

            col++;
            if (col >= 8) { // 8 lettres par ligne max
                col = 0;
                row++;
            }
        }
        return grid;
    }

    private void handleGuess(char guess) {
        boolean correct = false;
        for (int i = 0; i < motSecret.length(); i++) {
            if (motSecret.charAt(i) == guess) {
                motAffiche.setCharAt(i, guess);
                correct = true;
            }
        }

        if (!correct) {
            vies--;
        }

        labelMot.setText(motAffiche.toString());
        labelVie.setText("Nombre de vies : " + vies);
        penduLabel.setText("Pendu : " + (7 - vies) + " / 7");

        if (motAffiche.toString().equals(motSecret)) {
            endGame("Gagné !");
        } else if (vies == 0) {
            endGame("Perdu ! Le mot était : " + motSecret);
        }
    }

    private void endGame(String message) {
        labelMot.setText(message);
        for (javafx.scene.Node node : clavier.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    private void resetGame() {
        vies = 5;
        motAffiche = new StringBuilder("******");
        labelVie.setText("Nombre de vies : " + vies);
        labelMot.setText(motAffiche.toString());
        penduLabel.setText("Pendu : 0 / 5");
        clavier.getChildren().clear();
        clavier.getChildren().addAll(createClavier().getChildren());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
