
public class don {
	
	private int idDon;
	private String nom_donneur;
	private String prenom_donneur;
	private String email_donneur;
	private String telephone_donneur;
	private Date date_don;
	private int idBenevole;
	private Date date_enregistrement;
	
	public don(int unIdDon, String unNom_donneur, String unPrenom_donneur, String unEmail_donneur, Date uneDate_don, int unIdBenevole, Date uneDate_enregistrement) {
        this.idDon = unIdDon;
		this.nom_donneur = unNom_donneur;
        this.prenom_donneur = unPrenom_donneur;
        this.email_donneur = unEmail_donneur;
        this.date_don = uneDate_don;
        this.idBenevole = unIdBenevole;
        this.date_enregistrement = uneDate_enregistrement;
        
        
        
    }
}
