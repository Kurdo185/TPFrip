import java.sql.Date;

public class Article {
	
	private int idArticle;
	private String nom;
	private String description;
	private String taille;
	private String couleur;
	private String etat;
	private double prix;
	private String photo_url;
	private int id_benevole;
	private int id_vente;
	private Date date_enregistreament;
	private int test;
	
	public Article (int unIdArticle, String unNom, String uneDescription, String uneTaille, String uneCouleur, String unEtat, double unPrix, String unePhoto_url, int unIdBenevole, int unIdVente, Date uneDate_enregistrement) {
		this.idArticle = unIdArticle;
		this.nom = unNom;
		this.description = uneDescription;
		this.taille = uneTaille;
		this.couleur = uneCouleur;
		this.etat = unEtat;
		this.prix = unPrix;
		this.photo_url = unePhoto_url;
		this.id_benevole = unIdBenevole;
		this.id_vente = unIdVente;
		this.date_enregistreament = uneDate_enregistrement;
	}
		public int getidArticle() {
			return idArticle;
		}

		public String getnom() {
			return nom;
		}
		public String getdescription() {
			return description;
		}
		public String gettaille() {
			return taille;
		}
		public String getcouleur() {
			return couleur;
		}
		public String getetat() {
			return etat;
		}
		public double getprix() {
			return prix;
		}
		public String getphoto_url() {
			return photo_url;
		}
		public int getid_benevole() {
			return id_benevole;
		}
		public int getid_vente() {
			return id_vente;
		}
		public Date getdate_enregistreament() {
			return date_enregistreament;
		}
	 public void setidArticle(int unIdArticle) {
		 this.idArticle = unIdArticle;
	 }
	 public void setnom(String unNom) {
		 this.nom = unNom;
	 }
	 public void setdescription(String uneDescription) {
		 this.description = uneDescription;
	 }
	 public void settaille(String uneTaille) {
		 this.taille = uneTaille;
	 }
	 public void setcouleur(String uneCouleur) {
		 this.couleur = uneCouleur;
	 }
	 public void setetat(String unEtat) {
		 this.etat = unEtat;
	 }
	 public void setprix(double unPrix) {
		 this.prix = unPrix;
	 }
	 public void setphoto_url(String unePhoto_url) {
		 this.photo_url = unePhoto_url;
	 }
	 public void setid_benevole(int unIdBenevole) {
		 this.id_benevole = unIdBenevole;
	 }
	 public void setid_vente(int unIdVente) {
		 this.id_vente = unIdVente;
	 }
	 public void setdate_enregistreament(Date uneDate_enregistrement) {
		 this.date_enregistreament = uneDate_enregistrement;
	 
	}

	
}
