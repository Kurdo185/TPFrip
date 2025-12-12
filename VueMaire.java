import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Vue destinée au Maire.
 * Permet de sélectionner une vente et de voir le catalogue des articles associés.
 * @author sreyhan
 */
public class VueMaire extends JPanel {
    
    // --- Attributs ---
    private Modele modele;
    
    private JComboBox<Vente> cbVentes;
    private JButton btnVoir;
    
    private JTable table;
    private DefaultTableModel modelTable;

    // --- Constructeur ---
    public VueMaire(Modele modele) {
        this.modele = modele;
        setLayout(new BorderLayout());

        // 1. Panneau du haut (Choix de la vente)
        JPanel panelHaut = new JPanel();
        
        panelHaut.add(new JLabel("Choisir une vente :"));
        
        cbVentes = new JComboBox<>();
        panelHaut.add(cbVentes);
        
        btnVoir = new JButton("Voir le catalogue");
        
        // --- ACTION DU BOUTON ---
        // Ici, on dit directment : "Quand on clique (e), lance la méthode chargerCatalogue()"
        // C'est plus simple que le "implements ActionListener"
        btnVoir.addActionListener(e -> chargerCatalogue());
        
        panelHaut.add(btnVoir);
        
        add(panelHaut, BorderLayout.NORTH);

        // 2. Tableau au centre
        modelTable = new DefaultTableModel();
        // Définition des titres des colonnes
        String[] colonnes = {"Nom Article", "Taille", "Prix", "Description"};
        modelTable.setColumnIdentifiers(colonnes);
        
        table = new JTable(modelTable);
        
        // Ajout de l'ascenseur (scroll) autour du tableau
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Remplit la liste déroulante avec les ventes disponibles.
     */
    public void rafraichir() {
        cbVentes.removeAllItems();
        ArrayList<Vente> lesVentes = modele.getLesVentes();
        
        for (Vente v : lesVentes) {
            cbVentes.addItem(v);
        }
    }

    /**
     * Récupère les articles de la vente sélectionnée et remplit le tableau.
     * Cette méthode est appelée quand on clique sur le bouton.
     */
    private void chargerCatalogue() {
        // On vide le tableau avant de le remplir
        modelTable.setRowCount(0);
        
        // On récupère la vente choisie dans la liste
        Vente v = (Vente) cbVentes.getSelectedItem();
        
        if (v != null) {
            ArrayList<Article> arts = modele.getCatalogue(v.getIdVente());
            
            for (Article a : arts) {
                // On crée une ligne avec les infos de l'article
                Object[] ligne = {
                    a.getNom(), 
                    a.getTaille(), 
                    a.getPrix() + " €", 
                    a.getDescription()
                };
                // On ajoute la ligne au tableau
                modelTable.addRow(ligne);
            }
        }
    }
}