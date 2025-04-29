package fr.amu.iut.exercice5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;
    private double x;
    private double y;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche(ArrayList<Obstacle> obstacles) {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }

        for (Obstacle obstacle : obstacles) {
            if (estEnCollisionObstacle(obstacle)) {
                setLayoutX(x);
                setLayoutY(y);
            }
        }

        x = getLayoutX();
        y = getLayoutY();
    }

    public void deplacerADroite(double largeurJeu, ArrayList<Obstacle> obstacles) {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }

        for (Obstacle obstacle : obstacles) {
            if (estEnCollisionObstacle(obstacle)) {
                setLayoutX(x);
                setLayoutY(y);
            }
        }

        x = getLayoutX();
        y = getLayoutY();
    }

    public void deplacerEnBas(double hauteurJeu, ArrayList<Obstacle> obstacles) {
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
        //déplacement ---->
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("bas")) {
            direction = "bas";
        }

        for (Obstacle obstacle : obstacles) {
            if (estEnCollisionObstacle(obstacle)) {
                System.out.println("COLISDGJRGIDBGJHB");
                setLayoutX(x);
                setLayoutY(y);
            }
        }

        x = getLayoutX();
        y = getLayoutY();

    }

    public void deplacerEnHaut(ArrayList<Obstacle> obstacles) {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        //déplacement ---->
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("haut")) {
            direction = "haut";
        }

        for (Obstacle obstacle : obstacles) {
            if (estEnCollisionObstacle(obstacle)) {
                setLayoutX(x);
                setLayoutY(y);
            }
        }

        x = getLayoutX();
        y = getLayoutY();
    }

    boolean estEnCollision(Personnage autrePersonnage) {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

    boolean estEnCollisionObstacle(Obstacle obstacle) {
        return getBoundsInParent().contains(obstacle.getBoundsInParent())
                || obstacle.getBoundsInParent().contains(getBoundsInParent());
    }

}
