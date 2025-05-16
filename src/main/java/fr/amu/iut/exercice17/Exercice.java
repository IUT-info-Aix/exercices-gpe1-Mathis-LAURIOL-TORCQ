package fr.amu.iut.exercice17;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Exercice {
    private String enonce;
    private IntegerProperty solution = new SimpleIntegerProperty();

    public Exercice() {
        Random r = new Random();
        int a = r.nextInt(50);
        int b = r.nextInt(10) + 1;
        switch (r.nextInt(4)) {
            case 0:
                enonce = a + " + " + b;
                solution.set(a + b);
                break;
            case 1:
                enonce = a + " - " + b;
                solution.set(a - b);
                break;
            case 2:
                enonce = a + " * " + b;
                solution.set(a * b);
                break;
            case 3:
                enonce = (a + b) + " / " + b;
                solution.set((a + b) / b);
                break;
        }
    }

    public String getEnonce() {
        return enonce + " = ";
    }

    public int getSolution() {
        return solution.get();
    }

    public IntegerProperty solutionProperty() {
        return solution;
    }
}
