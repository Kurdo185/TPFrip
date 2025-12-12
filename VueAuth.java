import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vue d'authentification (Login).
 * Premier écran affiché pour sécuriser l'accès à l'application.
 * @author sreyhan
 */
public class VueAuth extends JPanel implements ActionListener {
    
    // --- Attributs ---
    private Modele modele;
    private Fenetre fenetre;
    
    private JTextField txtLogin;
    private JPasswordField txtMdp;
    private JButton btnValider;

    // --- Constructeur ---
    public VueAuth(Modele modele, Fenetre fenetre) {
        this.modele = modele;
        this.fenetre = fenetre;
        
        // Utilisation de GridBagLayout pour centrer le formulaire
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Marges entre les éléments (Haut, Gauche, Bas, Droite)
        gbc.insets = new Insets(10, 10, 10, 10); 

        // --- Ligne 1 : Login ---
        gbc.gridx = 0; 
        gbc.gridy = 0;
        add(new JLabel("Identifiant :"), gbc);

        gbc.gridx = 1;
        txtLogin = new JTextField(15);
        add(txtLogin, gbc);

        // --- Ligne 2 : Mot de passe ---
        gbc.gridx = 0; 
        gbc.gridy = 1;
        add(new JLabel("Mot de passe :"), gbc);

        gbc.gridx = 1;
        txtMdp = new JPasswordField(15); // JPasswordField cache les caractères
        add(txtMdp, gbc);

        // --- Ligne 3 : Bouton ---
        gbc.gridx = 1; 
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST; // Aligner le bouton à droite
        
        btnValider = new JButton("Se connecter");
        btnValider.addActionListener(this); // C'est cette classe qui gère le clic
        add(btnValider, gbc);
    }

    // --- Gestion des événements (Clic bouton) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        String login = txtLogin.getText();
        String mdp = new String(txtMdp.getPassword());
        
        // Vérification dans la base de données via le Modèle
        Utilisateur u = modele.authentifier(login, mdp);
        
        if (u != null) {
            // Succès : On affiche un message de bienvenue
            JOptionPane.showMessageDialog(this, "Bienvenue " + u.getPrenom() + " (" + u.getRole() + ")");
            
            // On vide les champs pour la sécurité (si quelqu'un passe derrière)
            txtLogin.setText("");
            txtMdp.setText("");
            
            // On demande à la fenêtre de changer de vue selon le rôle
            fenetre.setUtilisateurConnecte(u);
            
        } else {
            // Echec : Message d'erreur
            JOptionPane.showMessageDialog(this, "Erreur : Identifiant ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}