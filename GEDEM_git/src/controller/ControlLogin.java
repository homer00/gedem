package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import tools.ControlConnection;
import view.App;
import view.ViewConnexion;
import view.ViewFormCreneau;
import view.ViewFormPersonne;
import view.ViewListeFormation;
import view.ViewListeStagiaire;
import view.ViewLog;
//import view.Fenetre;
//import view.ViewListeStagiaire;



public class ControlLogin {

	private ControlConnection cc; // type DAO Acces, package "tools"
	//ArrayList<Login> listeLogin;
	//private ViewListeStagiaire vls;
	private ViewFormPersonne vfp;
	private ViewLog vl;
	private ViewListeFormation vform;
	private ViewListeStagiaire vls;
	private ViewFormCreneau vfc;
	//private Fenetre f;
	private int flag;

	// /** CONSTRUCTEUR =========================
	/**
	 *
	 * @param cn1
	 * @param app2
	 */
	public ControlLogin(ViewConnexion cn,App appli) {

		 cc = new ControlConnection();
		// ViewConnexion et App en paramètres, pour pouvoir échanger
		// des infos entre les vues et le controleur - 	cn : JPanel		appli : JFrame
		//this.app = appli;

		String loginStr = cn.getULogin().getText();
		// recupère le login avec le getter de la classe ViewConnexion
		// on transforme l'objet TextField en String (getText), pour le passer en paramètre de la requête SQL
		JTextField password = cn.getUPassword();
		String passwordStr = password.getText();
		// idem pour le mot de passe

		this.flag=0;

	try {

		String req2="SELECT idRole,login,password from personne where login='"+loginStr+"' AND password='"+passwordStr+"' ; ";
		//la requete récupère les infos dans la BDD en sélectionnant les champs qui correspondent aux
		//logins, pasword et role de la vue "ViewConnexion"
		// table : personne

		ResultSet rs2 = cc.getStatement().executeQuery(req2);
		// on fait un get pour recup le statement de ControlerConnection

		if (rs2.next()) { // si rs2 contient une donnée, donc si login et mot de passe correspondent
			System.out.println("LOGIN ET MOT DE PASSE OK");
			int monIdRole = rs2.getInt(1); // on recup le 1er champ du resultset, qui est idRole
			System.out.println("int monIdRole : "+rs2.getInt(1));
			String req3="SELECT nomRole FROM role WHERE idRole='"+monIdRole+"';";
			ResultSet rs3 = cc.getStatement().executeQuery(req3);


			if (rs3.next()) { // si le resultSet retourne nomRole


				System.out.println(rs3.getString(1));
				//================================================= Traitement des resultats dans la console
				if (rs3.getString(1).equals("User")) {
					System.out.println("Vous etes stagiaire.");
					flag = 1;

				}
				else if (rs3.getString(1).equals("Admin")) {
					System.out.println("Vous etes admin.");
					flag = 2;
				}
				else if (rs3.getString(1).equals("Formateur")) {
					System.out.println("Vous etes formateur/trice.");
					flag = 3;
				}
				else System.out.println("Valeur de rs3.getString(1): "+rs3.getString(1));
				//============================================ Traitement des resultats vers la JFrame
				appli.getContentPane().removeAll();
				// on supprime ce qu'il y avait précédemment sur le JPanel
				switch (flag) {
				case 1:
					// cas "User" --> Stagiaire
					vls = new ViewListeStagiaire(appli);
					appli.getContentPane().add(vls);
					break;
				case 2:
					// cas "Admin"
					
					// ========================================================== LOG	
				//	vfp = new ViewFormPersonne(appli);
					vl = new ViewLog(appli);
					
					//f = new Fenetre();
					appli.getContentPane().add(vl);
					break;
				case 3:
					// cas "Formateur"
					vform = new ViewListeFormation(appli);
					appli.getContentPane().add(vform);
					break;

				}

				appli.getContentPane().repaint();
				appli.getContentPane().revalidate();

						}
			else { // Affichage console si login et password ne "match" pas avec la BDD
				System.out.println("ACCESS REFUSE console");

				//appli.getContentPane().add(cn.getMessage2());

			}
		} // Fin if (rs2.next())
		// ========================================================== LOG
		// Traitement de l'échec de connexion
		else {
			String req_log = "INSERT INTO log (login_log, password_log,date_log) VALUES ('"+loginStr+"', '"+passwordStr+"',NOW());";
			cc.getStatement().executeUpdate(req_log);
			System.out.println("Execution requete pour les logs");
		}
		
		if (loginStr.equals("test")) { // ========================== CAS OU login = "test" ----> formulaire créneaux
			appli.getContentPane().removeAll();
			// on supprime ce qu'il y avait précédemment sur le JPanel
			vfc = new ViewFormCreneau(appli);
			appli.getContentPane().add(vfc); // on affiche le formulaire création de créneaux pour Login quelconque. 
			appli.getContentPane().repaint();
			appli.getContentPane().revalidate();
		}
	}

		catch (SQLException efc){
			System.out.println("ControlLogin : Erreur ControlLogin");
			efc.printStackTrace();
			}

}

	
	
}




