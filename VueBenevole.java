import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VueBenevole extends JPanel implements ActionListener {

    private Modele modele;
    private Utilisateur utilisateur;

    private JTextField txtNomArticle, txtPrix, txtTaille, txtDonneur;
    private JComboBox<Categorie> cbCategorie;
    private JComboBox<Vente> cbVente;
    private JButton btnAjouter;

    public VueBenevole(Modele modele) {
        this.modele = modele;
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Enregistrement Article & Don", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        add(lbl, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Nom Donneur :"));
        txtDonneur = new JTextField();
        form.add(txtDonneur);

        form.add(new JLabel("Vente concernée :"));
        cbVente = new JComboBox<>();
        form.add(cbVente);

        form.add(new JLabel("Nom Article :"));
        txtNomArticle = new JTextField();
        form.add(txtNomArticle);

        form.add(new JLabel("Catégorie :"));
        cbCategorie = new JComboBox<>();
        form.add(cbCategorie);

        form.add(new JLabel("Taille :"));
        txtTaille = new JTextField();
        form.add(txtTaille);

        form.add(new JLabel("Prix :"));
        txtPrix = new JTextField();
        form.add(txtPrix);

        add(form, BorderLayout.CENTER);

        btnAjouter = new JButton("Enregistrer");
        btnAjouter.addActionListener(this);
        add(btnAjouter, BorderLayout.SOUTH);
    }

    public void setUtilisateur(Utilisateur u) {
        this.utilisateur = u;
    }

    public void rafraichir() {
        // Charger les combos
        cbVente.removeAllItems();
        ArrayList<Vente> lesVentes = modele.getLesVentes();
        for (Vente v : lesVentes) cbVente.addItem(v);

        cbCategorie.removeAllItems();
        ArrayList<Categorie> lesCats = modele.getLesCategories();
        for (Categorie c : lesCats) cbCategorie.addItem(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String donneur = txtDonneur.getText();
            String nomArt = txtNomArticle.getText();
            String taille = txtTaille.getText();
            double prix = Double.parseDouble(txtPrix.getText());
            Vente v = (Vente) cbVente.getSelectedItem();
            Categorie c = (Categorie) cbCategorie.getSelectedItem();

            if (v == null || c == null) return;

            boolean ok = modele.ajouterArticleComplet(nomArt, prix, taille, c.getIdCategorie(), v.getIdVente(), utilisateur.getIdUtilisateur(), donneur);
            
            if (ok) {
                JOptionPane.showMessageDialog(this, "Article enregistré avec succès !");
                txtNomArticle.setText("");
                txtPrix.setText("");
                txtTaille.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Prix invalide");
        }
    }
}