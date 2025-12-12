/**
 * Représente une catégorie d'article (ex: Pantalon, Chemise, Accessoire).
 * Utilisé pour le tri des vêtements lors de la saisie par les bénévoles.
 * @author sreyhan
 */
public class Categorie {
    
    // --- Attributs ---
    private int idCategorie;
    private String nom;
    private String description;
    
    // --- Constructeur ---
    public Categorie(int idCategorie, String nom, String description) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.description = description;
    }
    
    // --- Getters et Setters ---
    
    public int getIdCategorie() { 
        return this.idCategorie; 
    }
    
    public void setIdCategorie(int idCategorie) { 
        this.idCategorie = idCategorie; 
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

    /**
     * Retourne le nom de la catégorie pour l'affichage dans les listes déroulantes
     */
    @Override
    public String toString() {
        return this.nom;
    }
}