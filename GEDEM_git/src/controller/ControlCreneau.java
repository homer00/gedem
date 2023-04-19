package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	
	private Date debutFormation;
	private Date finFormation;
	private Date heureDebutMat;
	private Date heureDebutAprem;
	
	
	// java.time --> année bissextile ? boolean isLeap = year.isLeap();
	
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
	LocalDateTime date1 = LocalDate.parse(debutFormationStr, dtf).atStartOfDay(); // on formate date1 dd-MM-yyyy
	
	//LocalDateTime date1 = debutFormationStr.formatted(dtf);
	LocalDateTime date2 = LocalDate.parse(finFormationStr, dtf).atStartOfDay();
	System.out.println("date1 (sans formatage): "+ date1);
	System.out.println("date 1 : "+ date1.getDayOfMonth()+"/"+date1.getMonthValue()+"/"+date1.getYear());
	
	long daysBetween = Duration.between(date1, date2).toDays(); // calcul intervalle de temps (en jours)
	System.out.println ("Days: " + daysBetween);
	int nbCreneaux = (int) daysBetween *2; // on double le nombre de creneaux (matin / aprem)
	System.out.println("nbCreneaux: "+nbCreneaux);
	
	ArrayList<Creneau>ligneCreneauAm = new ArrayList<Creneau>();
	ArrayList<Creneau>ligneCreneauPm = new ArrayList<Creneau>();
	int cpt=1;
	for (int i=0; i<daysBetween; i++) {
		
			LocalDateTime nextCren_matin = date1.plusDays(i); // on crée une valeur de date pour chaque jour de la boucle
			LocalDateTime nextCren_aprem = date1.plusDays(i); // créneau matin puis après-midi
			java.sql.Date nextCren_matinSql = java.sql.Date.valueOf(nextCren_matin.toLocalDate());
			java.sql.Date nextCren_apremSql = java.sql.Date.valueOf(nextCren_aprem.toLocalDate());
			//System.out.println("sql date : "+nextCren_matinSql.toString());
			// 11/04/2023
			DateTimeFormatter format1 = DateTimeFormatter.ofPattern("HH:mm");
			
	
			//java.sql.Time timeValue = new Time(Creneau.heureDebut_matin);
			
			// heureDebut_matin : format String, attribut de la classe creneau
			LocalTime heureDebutMat = LocalTime.parse((Creneau.heureDebut_matin));
			LocalTime heureFinMat = LocalTime.parse((Creneau.heureFin_matin));
			LocalTime heureDebutAprem = LocalTime.parse((Creneau.heureDebut_aprem));
			LocalTime heureFinAprem = LocalTime.parse((Creneau.heureFin_aprem));
			
			// vérification de la présence de créneaux dans la BDD pour empêcher les doublons.
			
			String req_verif_doublons="SELECT dateCreneau FROM creneau;";
			ControlConnection cc = new ControlConnection();
			try {
				ResultSet rs1 = cc.getStatement().executeQuery(req_verif_doublons);
				while (rs1.next()) {
					//System.out.println("X\t");
					
				}
			} catch (SQLException e1) {
				System.out.println("Erreur au moment de la verif de doublons");
				e1.printStackTrace();
			}
			
			
			
			
			
			
			//==================== INSERTION ==============
			String requete1="INSERT INTO creneau (dateCreneau,heureDebut,heureFin,duree,am_pm)"
					+ " VALUES ('"+nextCren_matinSql+"','"+heureDebutMat+"','"+heureFinMat+"','"+Creneau.dureeStr+"',1);";
			String requete2="INSERT INTO creneau (dateCreneau,heureDebut,heureFin,duree,am_pm)"
					+ " VALUES ('"+nextCren_apremSql+"','"+heureDebutAprem+"','"+heureFinAprem+"','"+Creneau.dureeStr+"',2);";
			
			//ControlConnection cc = new ControlConnection();
			
			try {
				cc.getStatement().executeUpdate(requete1); // INSERTION CRENEAU MATIN
			} catch (SQLException e) {
				System.out.println("Erreur d'execution de la requete1, creneau");
				e.printStackTrace();
			}
			
			try {
				cc.getStatement().executeUpdate(requete2); // INSERTION CRENEAU APRES-MIDI
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
			patternSplit[1] = patternSplit[1].length()<2 ? "0"+patternSplit[1] : patternSplit[1]; // idem pour le mois
			patternSplit[2] = patternSplit[0].length()<4 ? "20"+patternSplit[2] : patternSplit[2]; // on rajoute "20" devant l'année si elle fait moins de 4 caractères
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
