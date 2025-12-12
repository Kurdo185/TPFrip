import java.sql.Date;

/**
 * Représente une vente éphémère
 * Une vente possède un statut (ex: "EN_PREPARATION") qui définit si les bénévoles peuvent y ajouter des articles.
 * @author sreyhan
 */
public class Vente {
    
    // --- Attributs ---
    private int idVente;
    private String titre;
    private Date dateVente;
    private String lieu;
    private String statut; // Ex: "EN_PREPARATION", "OUVERTE", "TERMINEE"

    // --- Constructeur ---
    public Vente(int idVente, String titre, Date dateVente, String lieu, String statut) {
        this.idVente = idVente;
        this.titre = titre;
        this.dateVente = dateVente;
        this.lieu = lieu;
        this.statut = statut;
    }

    // --- Getters et Setters ---
    
    public int getIdVente() {
        return this.idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateVente() {
        return this.dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public String getLieu() {
        return this.lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Affiche le titre et la date.
     * Utile pour l'affichage dans les listes de l'interface graphique.
     */
    @Override
    public String toString() {
        return this.titre + " (le " + this.dateVente + ") - " + this.statut;
    }
}