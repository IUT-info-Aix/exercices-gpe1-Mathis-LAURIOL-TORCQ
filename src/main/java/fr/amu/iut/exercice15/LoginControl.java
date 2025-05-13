package fr.amu.iut.exercice15;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    public LoginControl() {
        // Constructeur par défaut nécessaire pour FXML
    }

    @FXML
    public void initialize() {
        // Initialisation appelée après l'injection des champs FXML
        createBindings();
    }

    private void createBindings() {
        // Contrainte 1: Le champ du mot de passe n'est pas éditable si le nom de l'utilisateur fait moins de 6 caractères
        pwd.editableProperty().bind(userId.textProperty().length().greaterThanOrEqualTo(6));

        // Contrainte 2: Le bouton cancel n'est pas cliquable si les deux champs sont vides
        BooleanBinding champsVides = userId.textProperty().isEmpty().and(pwd.textProperty().isEmpty());
        cancelBtn.disableProperty().bind(champsVides);

        // Contrainte 3: Le bouton ok n'est pas cliquable tant que le mot de passe n'a pas au moins 8 caractères,
        // et ne contient pas au moins une majuscule et un chiffre
        BooleanBinding motDePasseValide = new BooleanBinding() {
            {
                super.bind(pwd.textProperty());
            }

            @Override
            protected boolean computeValue() {
                String password = pwd.getText();

                // Vérification de la longueur minimale (8 caractères)
                if (password.length() < 8) {
                    return false;
                }

                // Vérification présence d'au moins une majuscule
                boolean contientMajuscule = false;
                for (char c : password.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        contientMajuscule = true;
                        break;
                    }
                }

                if (!contientMajuscule) {
                    return false;
                }

                // Vérification présence d'au moins un chiffre
                boolean contientChiffre = false;
                for (char c : password.toCharArray()) {
                    if (Character.isDigit(c)) {
                        contientChiffre = true;
                        break;
                    }
                }

                return contientChiffre;
            }
        };

        okBtn.disableProperty().bind(motDePasseValide.not());
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}