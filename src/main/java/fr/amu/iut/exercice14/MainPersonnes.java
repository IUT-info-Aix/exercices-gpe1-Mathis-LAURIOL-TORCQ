package fr.amu.iut.exercice14;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;
    private static DoubleProperty ageMoyen = new SimpleDoubleProperty(0);
    private static IntegerProperty nbParisiens = new SimpleIntegerProperty(0);

    private static DoubleBinding calculAgeMoyen;
    private static IntegerBinding calculNbParisiens;

    public static void main(String[] args) {
        // Initialisation de la liste observable avec un extracteur pour suivre les changements de propriétés
        lesPersonnes = FXCollections.observableArrayList(personne ->
                new javafx.beans.Observable[] {personne.ageProperty(), personne.lieuNaissanceProperty()});

        // 1. Création du binding pour calculer l'âge moyen
        calculAgeMoyen = new DoubleBinding() {
            {
                // Liaison avec la liste des personnes
                this.bind(lesPersonnes);
            }

            @Override
            protected double computeValue() {
                if (lesPersonnes.isEmpty()) {
                    return 0;
                }

                double sommeAges = 0;
                for (Personne personne : lesPersonnes) {
                    sommeAges += personne.getAge();
                }

                return sommeAges / lesPersonnes.size();
            }
        };

        // Liaison de la propriété ageMoyen au binding
        ageMoyen.bind(calculAgeMoyen);

        // 2. Création du binding pour calculer le nombre de parisiens
        calculNbParisiens = new IntegerBinding() {
            {
                // Liaison avec la liste des personnes
                this.bind(lesPersonnes);
            }

            @Override
            protected int computeValue() {
                int nbParis = 0;
                for (Personne personne : lesPersonnes) {
                    if (personne.estParisien()) {
                        nbParis++;
                    }
                }

                return nbParis;
            }
        };

        // Liaison de la propriété nbParisiens au binding
        nbParisiens.bind(calculNbParisiens);

        // Implémentation d'un listener pour afficher les mises à jour des statistiques
        lesPersonnes.addListener(new ListChangeListener<Personne>() {
            @Override
            public void onChanged(Change<? extends Personne> change) {
                System.out.println("Statistiques mises à jour :");
                System.out.println("Âge moyen : " + ageMoyen.get());
                System.out.println("Nombre de parisiens : " + nbParisiens.get());
                System.out.println("--------------------");
            }
        });

        // Appel de la méthode pour tester
        question2();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20, "Paris");
        Personne paul = new Personne("Paul", 40, "Lyon");
        Personne jacques = new Personne("Jacques", 60, "Marseille");

        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);

        // Modification de l'âge pour tester la mise à jour automatique
        pierre.setAge(25);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20, "Paris");
        Personne paul = new Personne("Paul", 40, "Lyon");
        Personne jacques = new Personne("Jacques", 60, "Marseille");
        Personne marie = new Personne("Marie", 30, "Paris");

        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.add(marie);

        // Modification du lieu de naissance pour tester la mise à jour automatique
        paul.setLieuNaissance("Paris");

        // Suppression d'une personne pour tester la mise à jour automatique
        lesPersonnes.remove(jacques);
    }
}