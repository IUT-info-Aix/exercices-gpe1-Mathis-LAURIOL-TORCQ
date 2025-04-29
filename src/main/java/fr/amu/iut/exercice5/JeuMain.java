package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    static ArrayList<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        Obstacle obstacle = new Obstacle(100, 100, 100, 21);
        obstacles.add(obstacle);

        // on positionne le fantôme 20 positions vers la droite

        fantome.setLayoutX(20 * 10);

        //panneau du jeu

        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        jeu.getChildren().add(obstacle);
        root.setCenter(jeu);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome, primaryStage, obstacles);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     * @param stage
     * @param obstacles
     */
    private void deplacer(Personnage j1, Personnage j2, Stage stage, ArrayList<Obstacle> obstacles) {

        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche(obstacles);
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth(), obstacles);
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight(), obstacles);
                    break;
                case UP:
                    j1.deplacerEnHaut(obstacles);
                    break;
                case Z:
                    j2.deplacerEnHaut(obstacles);
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight(), obstacles);
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth(), obstacles);
                    break;
                case Q:
                    j2.deplacerAGauche(obstacles);
                    break;

            }
            if (j1.estEnCollision(j2))
                stage.close();
        });
    }


}
