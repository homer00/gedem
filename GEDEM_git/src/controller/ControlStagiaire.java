package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Stagiaire_old;
import tools.ControlConnection;
//extends ControlConnection
public class ControlStagiaire  {
	// comme on hérite de ControlerConnection, on peut récupérer les attributs
	// et les méthodes de la classe mère, comme les connection, statement,...
	private ArrayList<Stagiaire_old> listeStagiaire;
	public ControlConnection ccs; // on déclare l'instance de ControllerConnection
	// qui permet de se connecter à la BDD
	
	/**
	 * =========================================== CONSTRUCTEUR
	 *
	 */
	public ControlStagiaire() {
	ccs = new ControlConnection("gedem","root","root"); 
	listeStagiaire = new ArrayList<Stagiaire_old>();
	}
	/**
	 * =========================================== CONSTRUCTEUR2
	 * @param db
	 * @param login
	 * @param pass
	 */
	public ControlStagiaire(String db, String login, String pass) {
	ccs = new ControlConnection(db,login,pass); 
	listeStagiaire = new ArrayList<Stagiaire_old>();
	}
	
	
	//===============Méthode============================ listerStagiaireStr()	

	public String listerStagiaireStr() { // pour créer une liste de stagiaire sous forme de String
	
	String nomTable = "personne";	

	String monEspace="";
	String monEspace2="";
	String listeStagStr="";
	
		try {
			if (!ccs.getConn().isClosed()) {
				// ccs.getConn() : on utilise le getter sur l'instance de ControlConnection  
			String req="SELECT * FROM "+nomTable+" WHERE idRole=3;";
			// idRole=3 -> correspond au rôle User (Stagiaire)
			ResultSet rs = ccs.getStatement().executeQuery(req);
			
			while (rs.next()) {
				
		listeStagiaire.add(new Stagiaire_old(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
					
		for (int i=0;i<listeStagiaire.size(); i++) {
			// formatage du texte en utilisant des tabulations
			if (listeStagiaire.get(i).getNomStagiaire().length()<8) {
				monEspace="\t\t";
				monEspace2="\t";
			}
			else {
				monEspace="\t";
				monEspace2="\t\t";
			}
			
		listeStagStr += listeStagiaire.get(i).getNomStagiaire()+monEspace
		+listeStagiaire.get(i).getPrenomStagiaire()+monEspace2
		+listeStagiaire.get(i).getTelStagiaire()+"\t"
		+listeStagiaire.get(i).getMailStagiaire()+"\t"
		+listeStagiaire.get(i).getRoleStagiaire()+"\n"	;
		
			}
			}
			ccs.fermerConn(); // appel de la prodédure de ControlConnection qui ferme la connexion
		}
		catch (SQLException efc){
			System.out.println("ControlStagiaire : erreur listerStagiaire");
			efc.printStackTrace();
			}
		return listeStagStr;
		
	}
	
	
}
