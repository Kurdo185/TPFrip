import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException; // Important pour gérer les erreurs

public class ModeleVente {

    public void creerUneVente(Vente uneVente) {

        String url = "jdbc:mysql://localhost/fripouilles";
        String login = "root";
        String mdp = "";

        try {
            Connection maConnexion = DriverManager.getConnection(url, login, mdp);
            Statement monStatut = maConnexion.createStatement();

           
            String requete = "INSERT INTO vente VALUES (null, '"
                    + uneVente.getTitre() + "', '"
                    + uneVente.getDate_vente() + "', '"
                    + uneVente.getDescription() + "', '"
                    + uneVente.getStatus() + "')";

            // On exécute 
            monStatut.executeUpdate(requete);
            
            // C'est propre de fermer la connexion
            maConnexion.close(); 
            System.out.println("Vente créée avec succès !");

        } catch (SQLException e) {
            // En cas d'erreur, on affiche le message
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }
}