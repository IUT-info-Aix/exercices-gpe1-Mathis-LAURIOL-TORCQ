package fr.amu.iut.exercice17;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class LigneExercice {

    private Exercice exercice;
    private TextField reponseField;
    private BooleanProperty correct = new SimpleBooleanProperty(false);
    private HBox hbox;

    public LigneExercice(Exercice exercice) {
        this.exercice = exercice;

        Label label = new Label(exercice.getEnonce());
        label.setMinWidth(100);
        label.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(3), null)));

        reponseField = new TextField("0");
        reponseField.setPrefWidth(50);

        hbox = new HBox(10, label, reponseField);

        // Écoute des changements
        reponseField.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                int rep = Integer.parseInt(newVal.trim());
                correct.set(rep == exercice.getSolution());
            } catch (NumberFormatException e) {
                correct.set(false);
            }
        });
    }

    public HBox getHBox() {
        return hbox;
    }

    public BooleanProperty getCorrect() {
        return correct;
    }

    public Exercice getExercice() {
        return exercice;
    }
}
