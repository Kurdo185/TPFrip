import java.sql.*;
import java.util.ArrayList;

/**
 * Classe Modèle qui gère toutes les interactions avec la base de données.
 * Elle contient les méthodes pour lire (SELECT) et écrire (INSERT) les données.
 * @author sreyhan
 */
public class Modele {

    // Attribut unique : la connexion
    private Connection connexion;

    /**
     * Constructeur : Établit la connexion à la base de données Fripouilles.
     */
    public Modele() {
        try {
            // Chargement du driver (optionnel sur les versions récentes mais conseillé en cours)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Paramètres de connexion
            String url = "jdbc:mysql://172.16.203.112/fripouilles?serverTimezone=UTC";
            String user = "sio";
            String mdp = "slam";

            this.connexion = DriverManager.getConnection(url, user, mdp);
            System.out.println("Connexion réussie à la base Fripouilles");

        } catch (ClassNotFoundException e) {
            System.out.println("Erreur Driver : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur Connexion BDD : " + e.getMessage());
        }
    }

    /**
     * Ferme proprement la connexion si elle est ouverte.
     */
    public void fermerConnexion() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Erreur fermeture : " + e.getMessage());
        }
    }

    // --- AUTHENTIFICATION ---

    /**
     * Vérifie le login et le mot de passe dans la base de données.
     * @param login L'identifiant saisi
     * @param mdp Le mot de passe saisi
     * @return Un objet Utilisateur si OK, sinon null.
     */
    public Utilisateur authentifier(String login, String mdp) {
        Utilisateur user = null;
        String sql = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ?";

        // Le try(...) ferme automatiquement le PreparedStatement et le ResultSet
        try (PreparedStatement pst = connexion.prepareStatement(sql)) {
            pst.setString(1, login);
            pst.setString(2, mdp);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    user = new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("login"),
                        rs.getString("role"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur authentification : " + e.getMessage());
        }
        return user;
    }

    // --- GESTION DES VENTES (Maire / Secrétaire) ---

    /**
     * Récupère la liste de toutes les ventes enregistrées.
     * @return Une ArrayList d'objets Vente.
     */
    public ArrayList<Vente> getLesVentes() {
        ArrayList<Vente> liste = new ArrayList<>();
        String sql = "SELECT * FROM vente";

        try (PreparedStatement pst = connexion.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                liste.add(new Vente(
                    rs.getInt("idVente"),
                    rs.getString("titre"),
                    rs.getDate("date_vente"),
                    rs.getString("lieu"),
                    rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur getLesVentes : " + e.getMessage());
        }
        return liste;
    }

    /**
     * Ajoute une nouvelle vente dans la base (statut par défaut : EN_PREPARATION).
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean ajouterVente(String titre, String dateStr, String lieu) {
        String sql = "INSERT INTO vente (titre, date_vente, lieu, statut) VALUES (?, ?, ?, 'EN_PREPARATION')";
        
        try (PreparedStatement pst = connexion.prepareStatement(sql)) {
            pst.setString(1, titre);
            pst.setString(2, dateStr);
            pst.setString(3, lieu);
            
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur ajouterVente : " + e.getMessage());
            return false;
        }
    }

    // --- GESTION DES CATEGORIES (Utile pour les listes déroulantes) ---

    /**
     * Récupère toutes les catégories de vêtements possibles.
     * @return Une ArrayList d'objets Categorie.
     */
    public ArrayList<Categorie> getLesCategories() {
        ArrayList<Categorie> liste = new ArrayList<>();
        String sql = "SELECT * FROM categorie";

        try (PreparedStatement pst = connexion.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                liste.add(new Categorie(
                    rs.getInt("idCategorie"),
                    rs.getString("nom"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur getLesCategories : " + e.getMessage());
        }
        return liste;
    }

    // --- GESTION DES ARTICLES (Bénévoles) ---

    /**
     * Processus complexe : Crée un Don, récupère son ID, puis crée l'Article lié.
     * @return true si tout s'est bien passé.
     */
    public boolean ajouterArticleComplet(String nomArticle, double prix, String taille, int idCateg, int idVente, int idUser, String nomDonneur) {
        String sqlDon = "INSERT INTO don (nom_donneur, date_don) VALUES (?, NOW())";
        String sqlArt = "INSERT INTO article (nom, prix, taille, idCategorie, idVente, idDon, idUtilisateur) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        int idDonCree = -1;

        try {
            // Etape 1 : Création du don avec récupération de l'ID généré
            try (PreparedStatement pstDon = connexion.prepareStatement(sqlDon, Statement.RETURN_GENERATED_KEYS)) {
                pstDon.setString(1, nomDonneur);
                pstDon.executeUpdate();
                
                try (ResultSet rsKeys = pstDon.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idDonCree = rsKeys.getInt(1);
                    }
                }
            }

            // Si le don a échoué, on arrête tout (pas de break ici, juste un if)
            if (idDonCree != -1) {
                // Etape 2 : Création de l'article lié au don
                try (PreparedStatement pstArt = connexion.prepareStatement(sqlArt)) {
                    pstArt.setString(1, nomArticle);
                    pstArt.setDouble(2, prix);
                    pstArt.setString(3, taille);
                    pstArt.setInt(4, idCateg);
                    pstArt.setInt(5, idVente);
                    pstArt.setInt(6, idDonCree);
                    pstArt.setInt(7, idUser);
                    
                    pstArt.executeUpdate();
                    return true; // Succès total
                }
            } else {
                return false; // Echec création don
            }

        } catch (SQLException e) {
            System.out.println("Erreur ajouterArticleComplet : " + e.getMessage());
            return false;
        }
    }

    /**
     * Récupère la liste des articles liés à une vente spécifique.
     * @param idVente L'identifiant de la vente sélectionnée.
     * @return Une liste d'articles.
     */
    public ArrayList<Article> getCatalogue(int idVente) {
        ArrayList<Article> liste = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE idVente = ?";

        try (PreparedStatement pst = connexion.prepareStatement(sql)) {
            pst.setInt(1, idVente);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    liste.add(new Article(
                        rs.getInt("idArticle"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getString("taille"),
                        rs.getInt("idCategorie"),
                        rs.getInt("idVente"),
                        rs.getInt("idDon"),
                        rs.getInt("idUtilisateur")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur getCatalogue : " + e.getMessage());
        }
        return liste;
    }
}