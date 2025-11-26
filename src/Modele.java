import java.sql.*;
import java.util.ArrayList;

/**
 * Classe Modèle pour gérer la connexion à la base de données hebsio
 * et fournir les méthodes d'accès aux données (CRUD)
 */
public class Modele {

    private Connection connexion;
    private ResultSet rs;
    private PreparedStatement pst;

    /**
     * Charge le pilote JDBC et établit la connexion
     */
    public Modele() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connexion = DriverManager.getConnection(
                "jdbc:mysql://172.16.203.111/fripouilles?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "sio", "slam");
            System.out.println("Connexion réussie à la base de données fripouilles");
        } catch (ClassNotFoundException erreur) {
            System.out.println("Driver non chargé " + erreur);
        } catch (SQLException erreur) {
            System.out.println("La connexion à la base a échoué : " + erreur);
        }
    }
}