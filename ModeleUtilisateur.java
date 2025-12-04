import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeleUtilisateur {

    // Méthode pour qu'une secrétaire puisse enregistrer un nouveau bénévole
    public void creerBenevole(Utilisateur unUtilisateur) {
        
        String url = "jdbc:mysql://localhost/fripouilles";
        String login = "root";
        String mdp = "";

        try {
            Connection maConnexion = DriverManager.getConnection(url, login, mdp);
            Statement monStatut = maConnexion.createStatement();

           
            String requete = "INSERT INTO utilisateur VALUES (null, '"
                    + unUtilisateur.getLogin() + "', '"
                    + unUtilisateur.getMdp() + "', 'Benevole', '"
                    + unUtilisateur.getNom() + "', '"
                    + unUtilisateur.getPrenom() + "', '"
                    + unUtilisateur.getEmail() + "', '"
                    + unUtilisateur.getTelephone() + "')";

            monStatut.executeUpdate(requete);
            maConnexion.close();
            System.out.println("Le bénévole " + unUtilisateur.getNom() + " a été enregistré !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du bénévole : " + e.getMessage());
        } // je commente 
    }
}