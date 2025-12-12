import java.sql.Date;

/**
 * Représente un don effectué par un habitant.
 * Contient les informations du donneur et la date de réception.
 * @author sreyhan
 */
public class Don {
    
    // --- Attributs ---
    private int idDon;
    private String nomDonneur;
    private String emailDonneur;
    private Date dateDon;
    
    // --- Constructeur ---
    public Don(int idDon, String nomDonneur, String emailDonneur, Date dateDon) {
        this.idDon = idDon;
        this.nomDonneur = nomDonneur;
        this.emailDonneur = emailDonneur;
        this.dateDon = dateDon;
    }
    
    // --- Getters et Setters ---
    
    public int getIdDon() { 
        return this.idDon; 
    }
    
    public void setIdDon(int idDon) { 
        this.idDon = idDon; 
    }

    public String getNomDonneur() { 
        return this.nomDonneur; 
    }
    
    public void setNomDonneur(String nomDonneur) { 
        this.nomDonneur = nomDonneur; 
    }

    public String getEmailDonneur() { 
        return this.emailDonneur; 
    }
    
    public void setEmailDonneur(String emailDonneur) { 
        this.emailDonneur = emailDonneur; 
    }

    public Date getDateDon() { 
        return this.dateDon; 
    }
    
    public void setDateDon(Date dateDon) { 
        this.dateDon = dateDon; 
    }
    
    @Override
    public String toString() {
        return this.nomDonneur + " (le " + this.dateDon + ")";
    }
}