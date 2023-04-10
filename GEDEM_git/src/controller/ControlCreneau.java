package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Creneau;
import tools.ControlConnection;

public class ControlCreneau {

	private String debutFormationStr;
	private String finFormationStr;
	private String duree = "3h30";
	private String heureDebut_matin = "8h30";
	private String heureFin_matin = "12h00";
	private String heureDebut_aprem = "13h30";
	private String heureFin_aprem = "17h00";
	private Date debutFormation;
	private Date finFormation;
	
	
	//======================================== CONSTRUCTEUR 2
		public ControlCreneau(Date deb, Date fin) { 
			this.debutFormation=deb;
			this.finFormation=fin;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
			SimpleDateFormat sdf_SQL = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
			
		}
		
	//======================================== CONSTRUCTEUR 3	
	public ControlCreneau(String deb, String fin) throws ParseException {

	this.debutFormationStr=deb;
	this.finFormationStr=fin;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDateTime date1 = LocalDate.parse(debutFormationStr, dtf).atStartOfDay();
	LocalDateTime date2 = LocalDate.parse(finFormationStr, dtf).atStartOfDay();
	System.out.println("date 1 : "+ date1.getDayOfMonth()+"/"+date1.getMonthValue()+"/"+date1.getYear());
	long daysBetween = Duration.between(date1, date2).toDays();
	System.out.println ("Days: " + daysBetween);
	int nbCreneaux = (int) daysBetween *2; // on double le nombre de creneaux (matin / aprem)

	
	ArrayList<Creneau>ligneCreneauAm = new ArrayList<Creneau>();
	ArrayList<Creneau>ligneCreneauPm = new ArrayList<Creneau>();
	int cpt=1;
	for (int i=0; i<daysBetween; i++) {
		
		//dateCreneau += dateCreneau;
			LocalDateTime nextCren_matin = date1.plusDays(i);
			LocalDateTime nextCren_aprem = date1.plusDays(i);
			//ligneCreneauAm.add(nextCren_matin,);
			//(nextCren_matin,heureDebut,heureFin,duree,am_pm);
			
			//String duree_matin = "3h30";
			//String duree_aprem = "3h30";
			
			String requete1="INSERT INTO creneau (dateCreneau,heureDebut,heureFin,duree,am_pm)"
					+ " VALUES ("+nextCren_matin+","+heureDebut_matin+","+heureFin_matin+","+duree+","+1+");";
			
			String requete2="INSERT INTO creneau (dateCreneau,heureDebut,heureFin,duree,am_pm)"
					+ " VALUES ("+nextCren_aprem+","+heureDebut_aprem+","+heureFin_aprem+","+duree+","+2+");";
			ControlConnection cc = new ControlConnection();
			try {
				cc.getStatement().executeUpdate(requete1);
			} catch (SQLException e) {
				System.out.println("Erreur d'execution de la requete1, creneau");
				e.printStackTrace();
			}
			try {
				cc.getStatement().executeUpdate(requete2);
			} catch (SQLException e) {
				System.out.println("ControlCreneau : Erreur d'execution de la requete2, creneau");
				e.printStackTrace();
			}
			
			System.out.println("Creneau "+cpt+" : " +nextCren_matin.format(dtf));
			cpt++;
			System.out.println("Creneau "+cpt+" : " +nextCren_aprem.format(dtf) );
			cpt++;
			
	}
	
	
	
}
	/*
	 * //================================== CONSTRUCTEUR1
	public Creneau() {
		
		System.out.println("CREATION DE CRENEAUX - PLANNING DE FORMATION");
		Scanner lecture = new Scanner(System.in);
		sop("Saisir une date pour le creneau (jj/MM/YYYY) :");
		// utilisation de la méthode java.util.Scanner next(Pattern pattern)
		// il faut indiquer un regex qui corresponde au pattern recherché
		// on boucle tant que cette condition n'a pas été remplie (tant que l'utilisateur n'a pas rentré la date avec 
		// le bon format, comme jj/mm/AAAA
		// regex : [0-9]{1,2}\/[0-9]{1,2}\/\d{2,4}
		// soit : tout chiffre entre 0 et 9 (1 occurence mini, 2 au maximum) + symbole "/" + 1 ou 2 chiffres entre 0 et 9
		// + symbole "/" + un nombre (entre 2 et 4 caractères - pour l'année)
		String pattern = "[0-9]{1,2}\\/[0-9]{1,2}\\/\\d{2,4}";
		try {
		if(lecture.hasNext()){


			String[] patternSplit = lecture.next(pattern).split("/");	// on sépare la date saisie en 3 parties, on range le résultat dans un tableau.
			patternSplit[0] = patternSplit[0].length()<2 ? "0"+patternSplit[0] : patternSplit[0]; // on rajoute un 0 au jour si moins de 2 chiffres (opérateur ternaire)
			patternSplit[1] = patternSplit[1].length()<2 ? "0"+patternSplit[0] : patternSplit[1]; // idem pour le mois
			patternSplit[2] = patternSplit[0].length()<4 ? "20"+patternSplit[0] : patternSplit[2]; // on rajoute "20" devant l'année si elle fait moins de 4 caractères
			String dateCreneauStr = patternSplit[0]+"/"+patternSplit[1]+"/"+patternSplit[2];  // on déclare une chaine qui récupère la nouvelle valeur de dateCreneau
			
			try {
				Date dateCreneau = new SimpleDateFormat("dd/MM/yyyy").parse(dateCreneauStr); // fait une conversion de String vers Date
				this.dateCreneau = dateCreneau;
			} 
			 catch (ParseException e) {
				sop("Erreur dans le traitement de la conversion String 2 date");
				e.printStackTrace();
			}  
			 System.out.println();
			 
		}
	
		}
		catch (NoSuchElementException e1) {
			sop("Erreur : pas d'element correspondant : mauvais format de saisie.");
			e1.printStackTrace();
		}
		catch (IllegalStateException e2) {
			sop("This exception is used to signal that a method is called at an illegal or inappropriate time.");
			e2.printStackTrace();
		}
		sop("Saisir une durée (par defaut : 3h30) : ");
		duree = lecture.next();
		if (duree == "") {
			this.duree = "3h30"; // si aucune donnée entrée > on met 3h30 par défaut.
		}
		
	}
	 */
	
}
