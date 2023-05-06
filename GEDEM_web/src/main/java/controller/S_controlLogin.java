package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import tools.Connexion;
import view.VueConnexion;

/**
 * Servlet implementation class S_controlLogin
 */
@WebServlet("/S_controlLogin")
public class S_controlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connexion cc; // type DAO Acces
	private String str_login_session;
	
	//private ViewFormPersonne vfp;
	//private ViewListeFormation vform;
	private int flag;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String str_login = request.getParameter("uLogin");
		String str_pass = request.getParameter("uPassword");
		HttpSession session = request.getSession();
		
		session.setAttribute(str_login_session, str_login);
		session.setAttribute(str_pass_session, str_pass);
		
		response.setCharacterEncoding("utf8");
		response.setContentType("text/html");
		//getServletContext().getRequestDispatcher("/VueStagiaire").forward(request,response);
		//package controller;

			// /** CONSTRUCTEUR =========================
			/**
			 *
			 * @param cn1
			 * @param app2
			 */
			//public ControlLogin(ViewConnexion cn,App appli) {
			public S_controlLogin(VueConnexion vc) {
				 cc = new Connexion();
				// VueConnexion et App en paramètres, pour pouvoir échanger
				// des infos entre les vues et le controleur - 
				 
				String loginStr = vc.getULogin().getText();
				// recupère le login avec le getter de la classe VueConnexion
				// on transforme l'objet TextField en String (getText), pour le passer en paramètre de la requête SQL
				JTextField password = cc.getUPassword();
				String passwordStr = password.getText();
				// idem pour le mot de passe

				this.flag=0;

			try {

				String req2="SELECT idRole,login,password from personne where login='"+loginStr+"' AND password='"+passwordStr+"' ; ";
				//la requete récupère les infos dans la BDD en sélectionnant les champs qui correspondent aux
				//logins, pasword et role de la vue "VueConnexion"
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
			//vls = new ViewListeStagiaire(appli);
			//appli.getContentPane().add(vls);
							break;
						case 2:
							// cas "Admin"
							vfp = new ViewFormPersonne(appli);
							appli.getContentPane().add(vfp);
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

						appli.getContentPane().add(cn.getMessage2());
						appli.getContentPane().repaint();
						appli.getContentPane().revalidate();
					}
				} // Fin if (rs2.next())
			}
				catch (SQLException efc){
					System.out.println("ControlLogin : Erreur ControlLogin");
					efc.printStackTrace();
					}

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
