package fripouilles;

public class Article {

    private int idArticle;
    private String nom;
    private String description;
    private double prix;
    private String taille;
    private int idCategorie;
    private int idVente;
    private int idDon;
    private int idUtilisateur;

    public Article(int idArticle, String nom, String description, double prix, String taille, int idCategorie, int idVente, int idDon, int idUtilisateur) {
        this.idArticle = idArticle;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.taille = taille;
        this.idCategorie = idCategorie;
        this.idVente = idVente;
        this.idDon = idDon;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdArticle() {
        return this.idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTaille() {
        return this.taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getIdCategorie() {
        return this.idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdVente() {
        return this.idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getIdDon() {
        return this.idDon;
    }

    public void setIdDon(int idDon) {
        this.idDon = idDon;
    }

    public int getIdUtilisateur() {
        return this.idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return this.nom + " (" + this.taille + ")";
    }
}