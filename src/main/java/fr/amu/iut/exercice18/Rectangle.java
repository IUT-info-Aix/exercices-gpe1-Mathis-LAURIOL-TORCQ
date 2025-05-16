package fr.amu.iut.exercice18;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rectangle {

    private final IntegerProperty xA = new SimpleIntegerProperty();
    private final IntegerProperty yA = new SimpleIntegerProperty();
    private final IntegerProperty xB = new SimpleIntegerProperty();
    private final IntegerProperty yB = new SimpleIntegerProperty();
    private final IntegerProperty perimetre = new SimpleIntegerProperty();

    public Rectangle() {
        createBinding();
    }

    private void createBinding() {
        IntegerBinding largeur = Bindings.createIntegerBinding(() ->
                Math.abs(xB.get() - xA.get()), xA, xB);
        IntegerBinding hauteur = Bindings.createIntegerBinding(() ->
                Math.abs(yB.get() - yA.get()), yA, yB);
        perimetre.bind(largeur.add(hauteur).multiply(2));
    }

    public IntegerProperty xAProperty() { return xA; }
    public IntegerProperty yAProperty() { return yA; }
    public IntegerProperty xBProperty() { return xB; }
    public IntegerProperty yBProperty() { return yB; }
    public IntegerProperty perimetreProperty() { return perimetre; }
}
