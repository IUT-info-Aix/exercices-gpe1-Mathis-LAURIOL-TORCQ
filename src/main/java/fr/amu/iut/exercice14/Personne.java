package fr.amu.iut.exercice14;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {
    private final StringProperty nom = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty lieuNaissance = new SimpleStringProperty();

    public Personne(String nom, int age) {
        this.nom.set(nom);
        this.age.set(age);
        this.lieuNaissance.set("Inconnu");
    }

    public Personne(String nom, int age, String lieuNaissance) {
        this.nom.set(nom);
        this.age.set(age);
        this.lieuNaissance.set(lieuNaissance);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getLieuNaissance() {
        return lieuNaissance.get();
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance.set(lieuNaissance);
    }

    public StringProperty lieuNaissanceProperty() {
        return lieuNaissance;
    }

    public boolean estParisien() {
        return "Paris".equals(getLieuNaissance());
    }
}