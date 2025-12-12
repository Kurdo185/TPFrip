import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenêtre principale de l'application Fripouilles.
 * Gestion de la navigation par suppression/ajout de panneaux (sans CardLayout).
 * @author sreyhan
 */
public class Fenetre extends JFrame {

    // --- Attributs ---
    private Modele modele;
    private Utilisateur utilisateurConnecte;

    // --- Vues (Panneaux) ---
    private VueAuth vueAuth;
    private VueSecretaire vueSecretaire;
    private VueBenevole vueBenevole;
    private VueMaire vueMaire;

    public Fenetre() {
        setTitle("Fripouilles - Gestion Ventes Éphémères");
        setSize(1000, 700);
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instanciation du modèle
        modele = new Modele();

        // Initialisation des différentes vues
        vueAuth = new VueAuth(modele, this);
        vueSecretaire = new VueSecretaire(modele);
        vueBenevole = new VueBenevole(modele);
        vueMaire = new VueMaire(modele);

        // On affiche la vue de connexion au démarrage
        changerVue("AUTH");
        setVisible(true);
    }

    /**
     * Change la vue affichée en vidant la fenêtre et en ajoutant la nouvelle vue.
     * Utilisation de if/else à la place de switch/break.
     * @param nomVue Le nom de la vue à afficher
     */
    public void changerVue(String nomVue) {
        // 1. On vide le contenu actuel de la fenêtre
        this.getContentPane().removeAll();

        // 2. On choisit la nouvelle vue à afficher
        if (nomVue.equals("AUTH")) {
            this.add(vueAuth);
        
        } else if (nomVue.equals("SECRETAIRE")) {
            this.add(vueSecretaire);
            vueSecretaire.rafraichir(); // Mise à jour des données
        
        } else if (nomVue.equals("BENEVOLE")) {
            this.add(vueBenevole);
            vueBenevole.rafraichir();
        
        } else if (nomVue.equals("MAIRE")) {
            this.add(vueMaire);
            vueMaire.rafraichir();
        }

        // 3. IMPORTANT : On demande à la fenêtre de se redessiner
        this.revalidate();
        this.repaint();
    }

    /**
     * Gère la connexion de l'utilisateur et la redirection selon son rôle.
     * @param u L'utilisateur authentifié
     */
    public void setUtilisateurConnecte(Utilisateur u) {
        this.utilisateurConnecte = u;
        
        if (u != null) {
            String role = u.getRole();

            // Redirection selon le rôle (Sans switch/break)
            if (role.equals("MAIRE")) {
                construireMenu("Menu Maire", "MAIRE");
                changerVue("MAIRE");
            
            } else if (role.equals("SECRETAIRE")) {
                construireMenu("Menu Secrétaire", "SECRETAIRE");
                changerVue("SECRETAIRE");
            
            } else if (role.equals("BENEVOLE")) {
                // Le bénévole doit être passé à sa vue pour l'enregistrement des articles
                vueBenevole.setUtilisateur(u);
                construireMenu("Menu Bénévole", "BENEVOLE");
                changerVue("BENEVOLE");
            
            } else {
                // Rôle inconnu ou erreur
                System.out.println("Erreur : Rôle inconnu");
                deconnexion();
            }
        }
    }

    /**
     * Construit le menu supérieur adapté à la vue.
     */
    private void construireMenu(String titreMenu, String vueCible) {
        JMenuBar barre = new JMenuBar();
        JMenu menu = new JMenu(titreMenu);
        
        JMenuItem itemPrincipal = new JMenuItem("Accueil");
        JMenuItem itemQuitter = new JMenuItem("Déconnexion");

        // Action pour retourner à l'accueil du rôle
        itemPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changerVue(vueCible);
            }
        });

        // Action pour se déconnecter
        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deconnexion();
            }
        });

        menu.add(itemPrincipal);
        menu.addSeparator(); // Petite ligne de séparation
        menu.add(itemQuitter);
        
        barre.add(menu);
        setJMenuBar(barre);
        
        // Force l'affichage du menu
        revalidate();
    }

    /**
     * Déconnecte l'utilisateur et renvoie à l'écran de login.
     */
    private void deconnexion() {
        utilisateurConnecte = null;
        setJMenuBar(null); // On enlève le menu
        changerVue("AUTH");
    }
}