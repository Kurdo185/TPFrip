import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Vue destinée aux bénévoles.
 * Permet d'enregistrer un don et les articles associés pour une vente spécifique.
 * @author sreyhan
 */
public class VueBenevole extends JPanel implements ActionListener {

    // --- Attributs ---
    private Modele modele;
    private Utilisateur utilisateur; // L'utilisateur connecté (pour la traçabilité)

    // Composants du formulaire
    private JTextField txtNomArticle;
    private JTextField txtPrix;
    private JTextField txtTaille;
    private JTextField txtDonneur;
    
    private JComboBox<Categorie> cbCategorie;
    private JComboBox<Vente> cbVente;
    
    private JButton btnAjouter;

    // --- Constructeur ---
    public VueBenevole(Modele modele) {
        this.modele = modele;
        
        // Organisation principale en BorderLayout
        setLayout(new BorderLayout());

        // 1. Titre en haut
        JLabel lblTitre = new JLabel("Enregistrement Article & Don", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitre, BorderLayout.NORTH);

        // 2. Formulaire au centre (Grille de 6 lignes, 2 colonnes)
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        
        // Marge autour du formulaire pour que ce soit plus joli
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Ajout des champs
        panelForm.add(new JLabel("Nom du Donneur :"));
        txtDonneur = new JTextField();
        panelForm.add(txtDonneur);

        panelForm.add(new JLabel("Vente concernée :"));
        cbVente = new JComboBox<>(); // Sera rempli par la méthode rafraichir()
        panelForm.add(cbVente);

        panelForm.add(new JLabel("Nom de l'article :"));
        txtNomArticle = new JTextField();
        panelForm.add(txtNomArticle);

        panelForm.add(new JLabel("Catégorie :"));
        cbCategorie = new JComboBox<>(); // Sera rempli par la méthode rafraichir()
        panelForm.add(cbCategorie);

        panelForm.add(new JLabel("Taille :"));
        txtTaille = new JTextField();
        panelForm.add(txtTaille);

        panelForm.add(new JLabel("Prix de vente (€) :"));
        txtPrix = new JTextField();
        panelForm.add(txtPrix);

        add(panelForm, BorderLayout.CENTER);

        // 3. Bouton en bas
        btnAjouter = new JButton("Enregistrer l'article");
        btnAjouter.addActionListener(this);
        
        // On met le bouton dans un petit panel pour qu'il ne prenne pas toute la largeur
        JPanel panelBas = new JPanel();
        panelBas.add(btnAjouter);
        add(panelBas, BorderLayout.SOUTH);
    }

    /**
     * Stocke l'utilisateur connecté.
     * Important : On a besoin de son ID pour savoir QUI a enregistré l'article dans la BDD.
     */
    public void setUtilisateur(Utilisateur u) {
        this.utilisateur = u;
    }

    /**
     * Met à jour les listes déroulantes (Ventes et Catégories) depuis la base de données.
     * Appelé par la Fenetre quand on arrive sur cette vue.
     */
    public void rafraichir() {
        // On vide les listes avant de les remplir pour éviter les doublons
        cbVente.removeAllItems();
        cbCategorie.removeAllItems();

        // Chargement des ventes
        ArrayList<Vente> lesVentes = modele.getLesVentes();
        for (Vente v : lesVentes) {
            cbVente.addItem(v);
        }

        // Chargement des catégories
        ArrayList<Categorie> lesCats = modele.getLesCategories();
        for (Categorie c : lesCats) {
            cbCategorie.addItem(c);
        }
    }

    // --- Gestion des événements ---
    @Override
    public void actionPerformed(ActionEvent e) {
        // Vérification basique : est-ce que les champs sont remplis ?
        if (txtDonneur.getText().isEmpty() || txtNomArticle.getText().isEmpty() || txtPrix.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Récupération des données du formulaire
            String donneur = txtDonneur.getText();
            String nomArt = txtNomArticle.getText();
            String taille = txtTaille.getText();
            
            // Gestion du prix : on remplace la virgule par un point pour éviter le crash
            String prixStr = txtPrix.getText().replace(",", ".");
            double prix = Double.parseDouble(prixStr);

            // Récupération des objets sélectionnés dans les listes
            Vente v = (Vente) cbVente.getSelectedItem();
            Categorie c = (Categorie) cbCategorie.getSelectedItem();

            // Sécurité : Si les listes sont vides
            if (v == null || c == null) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une vente et une catégorie.");
                return;
            }

            // Appel au modèle pour l'insertion en base
            boolean ok = modele.ajouterArticleComplet(
                nomArt, 
                prix, 
                taille, 
                c.getIdCategorie(), 
                v.getIdVente(), 
                utilisateur.getIdUtilisateur(), 
                donneur
            );
            
            if (ok) {
                JOptionPane.showMessageDialog(this, "Article enregistré avec succès !");
                // On vide les champs articles pour enchainer rapidement, mais on garde le donneur et la vente
                txtNomArticle.setText("");
                txtPrix.setText("");
                txtTaille.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur technique lors de l'enregistrement.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide (ex: 5.50)", "Erreur Format", JOptionPane.ERROR_MESSAGE);
        }
    }
}