
public class article {
	
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
	
	public article (int unIdArticle, String unNom, String uneDescription, String uneTaille, String uneCouleur, String unEtat, double unPrix, String unePhoto_url, int unIdBenevole, int unIdVente, Date uneDate_enregistrement) {
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
	
}
