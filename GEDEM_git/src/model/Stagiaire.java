package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import tools.ControlConnection;

/**
 *  Sous-classe de "Personne"
 *  Mysql : h_personne_stagiaire
 * @author hs
 *
 */
public class Stagiaire extends Personne{
	private int idStagiaire;
	private int idPersonne;
	private int idRole;
	/*
	private String nom = super.getNom();
	private String prenom = super.getPrenom();
	private String mail = super.getMail();
	private String tel = super.getTel();
	private String login = super.getLogin();
	private String password = super.getPassword();
	*/
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	private String login;
	private String password;
	
	ControlConnection cc;
/**
 * Constructeur 1 paramètre : idPersonne (attribut commun à la classe Personne)
 * @param idPersonne
 */
	public Stagiaire(int idPersonne) {
		super(); // appel du constructeur vide parent
		this.idPersonne = idPersonne;
		// test d'appel d'une méthode de la Classe mère (Personne) :
		show1Personn();
	}
/**
 * Constructeur 2 paramètres (les 2 champs de la table stagiaire)
 * @param idStagiaire
 * @param idPersonne
 */
	public Stagiaire(int idStagiaire, int idPersonne) {
		super(); // appel du constructeur vide parent
		this.idStagiaire = idStagiaire;
		this.idPersonne = idPersonne;
		// test d'appel d'une méthode de la Classe mère (Personne) :
		show1Personn();

	}
	/**
	 * 
	 * @param i1
	 * @param i2
	 * @param n
	 * @param p
	 * @param mail
	 * @param tel
	 * @param login
	 * @param password
	 */
	public Stagiaire (int i1, int i2, String n, String p, String mail, String tel, String login, String password) {
		super();
		//this.idStagiaire = i1;
		//this.idPersonne = i2;
		this.idPersonne=i1;
		this.idRole=i2;
		this.nom = n; // issu de la classe mère (Personne) grâce à la déclaration private String nom = super.getNom();
		this.prenom = p;
		this.mail = mail;
		this.tel = tel;
		this.login = login;
		this.password = password;
		
	}

	public void afficheStagiaires() {

		// requete SQL pour sélectionner l'ensemble des stagiaires
		String reqAS = "SELECT p.nom,p.prenom,s.idStagiaire FROM Personne p JOIN h_personne_stagiaire s USING (idPersonne) WHERE idRole='3'";
		// Dans la table role, idRole=3 correspond à "User", qui est le rôle correspondant aux stagiaires.

		try {
			cc = new ControlConnection();
			ResultSet rs = cc.getStatement().executeQuery(reqAS);
			while (rs.next()) {
				System.out.println("Nom : "+rs.getString(1)+"\tPrenom : "+rs.getString(2)+"\tid Stagiaire : "+rs.getInt(3));
			}
		}
		catch (SQLException e){
			System.out.println("Erreur SQL - Classe Stagiaire");
			e.printStackTrace();

		}
	}

	//=========================================  ACCESSEURS
	public int getIdStagiaire() {
		return this.idStagiaire;
	}
	@Override
	public int getIdPersonne() {
		return this.idPersonne;
	}
	public void setIdStagiaire(int ids) {
		this.idStagiaire = ids;
	}
	@Override
	public void setIdPersonne(int idp) {
		this.idPersonne = idp;
	}

}
