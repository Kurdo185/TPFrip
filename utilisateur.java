public class utilisateur {
	
    private int idUtilisateur;
    private String login;
    private String mdp;
    private String role;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public utilisateur(int unIdUtilisateur, String unLogin, String unMdp, String unRole, String unNom, String unPrenom, String unEmail, String unTelephone) {
    	this.idUtilisateur = unIdUtilisateur;
    	this.login = unLogin;
    	this.mdp = unMdp;
    	this.role = unRole;
    	this.nom = unNom;
    	this.prenom = unPrenom;
    	this.email = unEmail;
    	this.telephone = unTelephone;
    }
}