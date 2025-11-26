public class Utilisateur {
	
    private int idUtilisateur;
    private String login;
    private String mdp;
    private String role;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public Utilisateur (int unIdUtilisateur, String unLogin, String unRole, String unNom, String unPrenom, String unEmail, String unTelephone) {
    	this.idUtilisateur = unIdUtilisateur;
    	this.login = unLogin;
    	this.role = unRole;
    	this.nom = unNom;
    	this.prenom = unPrenom;
    	this.email = unEmail;
    	this.telephone = unTelephone;
    	
    }
    
    public void setidUtilisateur (int unIdUtilisateur) {
    	this.idUtilisateur = unIdUtilisateur;
    }
    
	public int getidUtilisateur() {
		return this.idUtilisateur;
	}
	
	public void setLogin (String unLogin) {
		this.login = unLogin;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setRole (String unRole) {
		this.role = unRole;
	}
	
	public String getRole () {
		return this.role;
	}
	
	public void setNom (String unNom) {
		this.nom = unNom;
	}
	
	public String getNom () {
		return this.nom;
	}
	
	public void getPrenom (String unPrenom) {
		this.prenom = unPrenom;
	}
	
	public String setPrenom () {
		return this.prenom;
	}
	
	public void setEmail (String unEmail) {
		this.email = unEmail;
	}
	
	public String getEmail (){
		return this.email;
	}
	
	public void setTelephone (String unTelephone) {
		this.telephone = unTelephone;
	}
	
	public String getTelephone () {
		return this.telephone;
	}
		
}