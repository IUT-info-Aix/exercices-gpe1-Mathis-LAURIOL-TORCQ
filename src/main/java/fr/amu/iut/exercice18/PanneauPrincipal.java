package fr.amu.iut.exercice18;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class PanneauPrincipal {

    private final Rectangle rectangle = new Rectangle();
    private final int valeurMaxCoordonnees = 20;
    private final int ratioDessin = 10;

    @FXML private Slider sliderXA;
    @FXML private Slider sliderYA;
    @FXML private TextField tfBx;
    @FXML private TextField tfBy;
    @FXML private TextField tfPerimetre;
    @FXML private Pane panneauDessin;

    private Line ligneHaut, ligneBas, ligneGauche, ligneDroite;

    @FXML
    public void initialize() {
        addLines();
        bindSommetsRectangle();
        bindPerimeterTextField();
        bindHorizontal1();
        bindVertical1();
        bindHorizontal2();
        bindVertical2();
    }

    public void addLines() {
        ligneHaut = new Line();
        ligneBas = new Line();
        ligneGauche = new Line();
        ligneDroite = new Line();
        panneauDessin.getChildren().addAll(ligneHaut, ligneBas, ligneGauche, ligneDroite);
    }

    @FXML
    private void incrementerBx() {
        if (rectangle.xBProperty().get() < valeurMaxCoordonnees)
            rectangle.xBProperty().set(rectangle.xBProperty().get() + 1);
    }

    @FXML
    private void decrementerBx() {
        if (rectangle.xBProperty().get() > 0)
            rectangle.xBProperty().set(rectangle.xBProperty().get() - 1);
    }

    @FXML
    private void setByPlusAction() {
        if (rectangle.yBProperty().get() < valeurMaxCoordonnees)
            rectangle.yBProperty().set(rectangle.yBProperty().get() + 1);
    }

    @FXML
    private void setByMinusAction() {
        if (rectangle.yBProperty().get() > 0)
            rectangle.yBProperty().set(rectangle.yBProperty().get() - 1);
    }

    public void bindSommetsRectangle() {
        rectangle.xAProperty().bind(sliderXA.valueProperty());
        rectangle.yAProperty().bind(sliderYA.valueProperty());

        tfBx.textProperty().bind(rectangle.xBProperty().asString());
        tfBy.textProperty().bind(rectangle.yBProperty().asString());
    }

    public void bindPerimeterTextField() {
        tfPerimetre.textProperty().bind(rectangle.perimetreProperty().asString());
    }

    public void bindHorizontal1() {
        ligneHaut.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneHaut.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
        ligneHaut.endXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        ligneHaut.endYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
    }

    public void bindVertical1() {
        ligneGauche.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneGauche.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
        ligneGauche.endXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneGauche.endYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
    }

    public void bindHorizontal2() {
        ligneBas.startXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        ligneBas.startYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
        ligneBas.endXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        ligneBas.endYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
    }

    public void bindVertical2() {
        ligneDroite.startXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        ligneDroite.startYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
        ligneDroite.endXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        ligneDroite.endYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
    }
}
