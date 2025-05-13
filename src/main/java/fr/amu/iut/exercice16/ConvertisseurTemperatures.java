package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création des sliders verticaux
        Slider sliderCelsius = new Slider(0, 100, 0);
        sliderCelsius.setOrientation(javafx.geometry.Orientation.VERTICAL);
        sliderCelsius.setShowTickLabels(true);
        sliderCelsius.setShowTickMarks(true);
        sliderCelsius.setMajorTickUnit(10);
        sliderCelsius.setPrefHeight(300);

        Slider sliderFahrenheit = new Slider(32, 212, 32);
        sliderFahrenheit.setOrientation(javafx.geometry.Orientation.VERTICAL);
        sliderFahrenheit.setShowTickLabels(true);
        sliderFahrenheit.setShowTickMarks(true);
        sliderFahrenheit.setMajorTickUnit(20);
        sliderFahrenheit.setPrefHeight(300);

        // Création des labels pour les unités
        Label labelCelsius = new Label("°C");
        labelCelsius.setFont(Font.font(14));

        Label labelFahrenheit = new Label("°F");
        labelFahrenheit.setFont(Font.font(14));

        // Création des champs texte avec largeur fixe
        TextField textCelsius = new TextField("0.00");
        textCelsius.setPrefWidth(70);

        TextField textFahrenheit = new TextField("32.00");
        textFahrenheit.setPrefWidth(70);

        // Layout pour les labels en haut
        HBox headerBox = new HBox(50);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().addAll(labelCelsius, labelFahrenheit);
        headerBox.setPadding(new Insets(10, 10, 5, 10));

        // Layout pour les sliders au milieu
        HBox slidersBox = new HBox(40);
        slidersBox.setAlignment(Pos.CENTER);
        slidersBox.getChildren().addAll(sliderCelsius, sliderFahrenheit);
        slidersBox.setPadding(new Insets(0, 10, 10, 10));

        // Layout pour les champs texte en bas
        HBox textFieldsBox = new HBox(30);
        textFieldsBox.setAlignment(Pos.CENTER);
        textFieldsBox.getChildren().addAll(textCelsius, textFahrenheit);
        textFieldsBox.setPadding(new Insets(0, 10, 10, 10));

        // Création du binding bidirectionnel entre les sliders
        // Formule de conversion: F = C * (9/5) + 32
        sliderCelsius.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (Math.abs(sliderFahrenheit.getValue() - (newVal.doubleValue() * (9.0/5.0) + 32)) > 0.01) {
                double fahrenheit = newVal.doubleValue() * (9.0/5.0) + 32;
                sliderFahrenheit.setValue(fahrenheit);
            }
        });

        sliderFahrenheit.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (Math.abs(sliderCelsius.getValue() - ((newVal.doubleValue() - 32) * (5.0/9.0))) > 0.01) {
                double celsius = (newVal.doubleValue() - 32) * (5.0/9.0);
                sliderCelsius.setValue(celsius);
            }
        });

        // Binding bidirectionnel entre les sliders et les textfields
        StringConverter<Number> converter = new NumberStringConverter("#0.00");
        Bindings.bindBidirectional(textCelsius.textProperty(), sliderCelsius.valueProperty(), converter);
        Bindings.bindBidirectional(textFahrenheit.textProperty(), sliderFahrenheit.valueProperty(), converter);

        // Layout principal avec une BorderPane pour un meilleur contrôle
        BorderPane root = new BorderPane();
        root.setTop(headerBox);
        root.setCenter(slidersBox);
        root.setBottom(textFieldsBox);

        // Mise en place de la scène
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}