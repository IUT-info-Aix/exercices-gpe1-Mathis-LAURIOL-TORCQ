package fr.amu.iut.exercice13;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;
    private static ListChangeListener<Personne> plusieursChangementsListener;

    public static void main(String[] args) {
        // Initialisation de la liste observable avec un extracteur pour suivre les changements de propriétés
        lesPersonnes = FXCollections.observableArrayList(personne -> new javafx.beans.Observable[] {personne.ageProperty()});

        // Implémentation du listener pour gérer un changement à la fois
        unChangementListener = new ListChangeListener<Personne>() {
            @Override
            public void onChanged(Change<? extends Personne> change) {
                // On traite le premier changement seulement
                if (change.next()) {
                    // Si c'est un ajout
                    if (change.wasAdded()) {
                        // On récupère la sous-liste des éléments ajoutés
                        for (Personne personne : change.getAddedSubList()) {
                            System.out.println("Ajout : " + personne.getNom());
                        }
                    }
                    // Si c'est une suppression
                    if (change.wasRemoved()) {
                        // On récupère la liste des éléments supprimés
                        for (Personne personne : change.getRemoved()) {
                            System.out.println("Suppression : " + personne.getNom());
                        }
                    }
                    // Si c'est une mise à jour (pour les propriétés observées comme l'âge)
                    if (change.wasUpdated()) {
                        // Pour chaque index mis à jour
                        for (int i = change.getFrom(); i < change.getTo(); i++) {
                            Personne personne = lesPersonnes.get(i);
                            System.out.println(personne.getNom() + " a maintenant " + personne.getAge() + " ans");
                        }
                    }
                }
            }
        };

        // Implémentation du listener pour gérer plusieurs changements à la fois
        plusieursChangementsListener = new ListChangeListener<Personne>() {
            @Override
            public void onChanged(Change<? extends Personne> change) {
                // On parcourt tous les changements
                while (change.next()) {
                    // Si c'est un ajout
                    if (change.wasAdded()) {
                        for (Personne personne : change.getAddedSubList()) {
                            System.out.println("Ajout : " + personne.getNom());
                        }
                    }
                    // Si c'est une suppression
                    if (change.wasRemoved()) {
                        for (Personne personne : change.getRemoved()) {
                            System.out.println("Suppression : " + personne.getNom());
                        }
                    }
                    // Si c'est une mise à jour
                    if (change.wasUpdated()) {
                        for (int i = change.getFrom(); i < change.getTo(); i++) {
                            Personne personne = lesPersonnes.get(i);
                            System.out.println(personne.getNom() + " a maintenant " + personne.getAge() + " ans");
                        }
                    }
                }
                System.out.println("Fin d'une série de changements");
            }
        };

        // Ajout du listener à la liste (décommenter celui qu'on souhaite utiliser)
        lesPersonnes.addListener(unChangementListener);
        // Remplacer par celle-ci pour tester la question 5
        // lesPersonnes.addListener(plusieursChangementsListener);

        // Appel de la méthode pour tester
        question3();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge()+10);
        lesPersonnes.removeAll(paul, pierre);
    }
}