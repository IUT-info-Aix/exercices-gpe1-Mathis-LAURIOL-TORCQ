package fr.amu.iut.exercice17;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppMaths extends Application {

    private VBox root;
    private ComboBox<Integer> comboBox;
    private VBox exercicesBox;
    private List<LigneExercice> lignes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        root = new VBox(10);
        root.setPadding(new Insets(10));

        HBox topBox = new HBox(10);
        Label label = new Label("Combien d'exercices ?");
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(6, 9, 12, 15);
        comboBox.setValue(6); // valeur par défaut

        topBox.getChildren().addAll(label, comboBox);
        root.getChildren().add(topBox);

        exercicesBox = new VBox(5);
        root.getChildren().add(exercicesBox);

        comboBox.valueProperty().addListener((obs, oldVal, newVal) -> mettreAJourExercices(newVal));

        // Initialisation
        mettreAJourExercices(comboBox.getValue());

        Scene scene = new Scene(root);
        primaryStage.setTitle("Exercices de maths");
        primaryStage.setScene(scene);
        primaryStage.show();

// Appelle la méthode ici, quand la scène est bien affichée
        mettreAJourExercices(comboBox.getValue());

    }

    private void mettreAJourExercices(int nombre) {
        exercicesBox.getChildren().clear();
        lignes.clear();

        for (int i = 0; i < nombre; i++) {
            LigneExercice ligne = new LigneExercice(new Exercice());
            lignes.add(ligne);
            exercicesBox.getChildren().add(ligne.getHBox());
        }

        Button boutonValider = new Button("Voir le résultat");
        boutonValider.setOnAction(e -> verifierResultats());
        exercicesBox.getChildren().add(boutonValider);

        // Dans mettreAJourExercices
        if (root.getScene() != null && root.getScene().getWindow() != null) {
            root.getScene().getWindow().sizeToScene();
        }

    }

    private void verifierResultats() {
        long corrects = lignes.stream().filter(l -> l.getCorrect().get()).count();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Résultat");
        alert.setHeaderText(null);
        alert.setContentText(corrects + " réponse(s) correcte(s) sur " + lignes.size());
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
