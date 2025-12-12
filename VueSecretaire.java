import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Vue destinée à la Secrétaire.
 * Permet de créer de nouvelles ventes et de visualiser la liste existante.
 * @author sreyhan
 */
public class VueSecretaire extends JPanel {
    
    // --- Attributs ---
    private Modele modele;
    
    // Champs du formulaire
    private JTextField txtTitre;
    private JTextField txtDate;
    private JTextField txtLieu;
    
    // Tableau
    private JTable table;
    private DefaultTableModel modelTable;

    // --- Constructeur ---
    public VueSecretaire(Modele modele) {
        this.modele = modele;
        setLayout(new BorderLayout());

        // 1. HAUT : Formulaire d'ajout de vente
        // GridLayout(4, 2, 5, 5) -> 4 lignes, 2 colonnes, et 5px d'écart entre les cases
        JPanel panelHaut = new JPanel(new GridLayout(4, 2, 5, 5));
        
        // On ajoute une petite bordure pour faire joli
        panelHaut.setBorder(BorderFactory.createTitledBorder("Nouvelle Vente"));

        panelHaut.add(new JLabel("Titre de l'événement :"));
        txtTitre = new JTextField();
        panelHaut.add(txtTitre);

        panelHaut.add(new JLabel("Date (AAAA-MM-JJ) :"));
        txtDate = new JTextField();
        panelHaut.add(txtDate);

        panelHaut.add(new JLabel("Lieu :"));
        txtLieu = new JTextField();
        panelHaut.add(txtLieu);

        // Case vide pour aligner le bouton à droite
        panelHaut.add(new JLabel("")); 
        
        JButton btnAjout = new JButton("Créer la vente");
        // Action directe : on appelle la méthode actionAjouter()
        btnAjout.addActionListener(e -> actionAjouter());
        panelHaut.add(btnAjout);

        add(panelHaut, BorderLayout.NORTH);

        // 2. CENTRE : Liste des ventes
        modelTable = new DefaultTableModel();
        String[] colonnes = {"ID", "Titre", "Date", "Lieu", "Statut"};
        modelTable.setColumnIdentifiers(colonnes);
        
        table = new JTable(modelTable);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Met à jour le tableau en récupérant les données de la base.
     */
    public void rafraichir() {
        // On vide le tableau
        modelTable.setRowCount(0);
        
        ArrayList<Vente> lesVentes = modele.getLesVentes();
        
        for (Vente v : lesVentes) {
            // On crée la ligne
            Object[] ligne = {
                v.getIdVente(), 
                v.getTitre(), 
                v.getDateVente(), 
                v.getLieu(), 
                v.getStatut()
            };
            // On ajoute la ligne
            modelTable.addRow(ligne);
        }
    }

    /**
     * Méthode déclenchée par le clic sur le bouton "Créer".
     */
    private void actionAjouter() {
        String titre = txtTitre.getText();
        String date = txtDate.getText();
        String lieu = txtLieu.getText();

        // Petite sécurité pour ne pas envoyer de vide
        if (titre.isEmpty() || date.isEmpty() || lieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        boolean ok = modele.ajouterVente(titre, date, lieu);
        
        if (ok) {
            JOptionPane.showMessageDialog(this, "Vente créée avec succès !");
            // On vide les champs pour que ce soit propre
            txtTitre.setText("");
            txtDate.setText("");
            txtLieu.setText("");
            
            // On met à jour le tableau tout de suite
            rafraichir();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur : Vérifiez le format de la date (AAAA-MM-JJ).");
        }
    }
}