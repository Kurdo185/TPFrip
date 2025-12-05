import java.sql.Date;

/**
 * @author sreyhan
 * Classe don information 
 * Le donneur fait le don on peut edit ses info perso
 * 
 */
public class Don {
	
	// Attributs
	private int idDon;
	private String nomDonneur;
	private String emailDonneur;
	private Date dateDon;
	
	// Constructeur
	public Don(int idDon, String nomDonneur, String emailDonneur, Date dateDon) {
		this.idDon = idDon;
		this.nomDonneur = nomDonneur;
		this.emailDonneur = emailDonneur;
		this.dateDon = dateDon;
	}
	
    // Getteurs/Setteurs
	public int getIdDon() {
		return idDon;
	}

	public void setIdDon(int idDon) {
		this.idDon = idDon;
	}

	public String getNomDonneur() {
		return nomDonneur;
	}

	public void setNomDonneur(String nomDonneur) {
		this.nomDonneur = nomDonneur;
	}

	public String getEmailDonneur() {
		return emailDonneur;
	}

	public void setEmailDonneur(String emailDonneur) {
		this.emailDonneur = emailDonneur;
	}

	public Date getDateDon() {
		return dateDon;
	}

	public void setDateDon(Date dateDon) {
		this.dateDon = dateDon;
	}
	
	@Override
	public String toString() {
		return nomDonneur + " (" + dateDon + ")";
	}
}	}
}