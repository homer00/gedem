package model;

import java.time.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.*;

import tools.ControlConnection;

public class Creneau {


	private int idCreneau,idMatiere;
	private Date dateCreneau;
	private int am_pm;
	//private Boolean momentCreneau;
	private String heureDebut;
	private String heureFin;
	private Date duree;
	private ArrayList<Creneau> listeCreneau;
	//private Date debutFormation;
	//private Date finFormation;
	public static String dureeStr = "03:30";
	public static String heureDebut_matin = "08:30";
	public static String heureFin_matin = "12:00";
	public static String heureDebut_aprem = "13:30";
	public static String heureFin_aprem = "17:00";

public Creneau(Date dateCreneau, String heureDebut, String heureFin, Date duree) {
	this.dateCreneau = dateCreneau;
	this.heureDebut = heureDebut;
	this.heureFin = heureFin;
	this.duree = duree;
	this.listeCreneau = new ArrayList<Creneau>();
}

public int getIdCreneau() {
	return idCreneau;
}

public void setIdCreneau(int idCreneau) {
	this.idCreneau = idCreneau;
}

public int getIdMatiere() {
	return idMatiere;
}

public void setIdMatiere(int idMatiere) {
	this.idMatiere = idMatiere;
}

public Date getDateCreneau() {
	return dateCreneau;
}

public void setDateCreneau(Date dateCreneau) {
	this.dateCreneau = dateCreneau;
}

public int getAm_pm() {
	return am_pm;
}

public void setAm_pm(int am_pm) {
	this.am_pm = am_pm;
}

public String getHeureDebut() {
	return heureDebut;
}

public void setHeureDebut(String heureDebut) {
	this.heureDebut = heureDebut;
}

public String getHeureFin() {
	return heureFin;
}

public void setHeureFin(String heureFin) {
	this.heureFin = heureFin;
}

public Date getDuree() {
	return duree;
}

public void setDuree(Date duree) {
	this.duree = duree;
}






	// mon Garbage
	
	/*
	 *  Date firstDate = sdf.parse("04/22/2020");
    Date secondDate = sdf.parse("04/27/2020");

    long diff = secondDate.getTime() - firstDate.getTime();

    TimeUnit time = TimeUnit.DAYS; 
    long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
    System.out.println("The difference in days is : "+diffrence);
	 */
	
	//Date firstDate = sdf.format(debutFormation);
	//Date aujourdhui = (Date) Calendar.getInstance().
	//long daysBetween = Duration.between(finFormation, debutFormation).toDays();
	
	
	// conversion de date1 qui utilise le type LocalDateTime vers le type Date
			// on affecte le résultat à dateCreneau, attribut de la classe Creneau
			//dateCreneau = Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
			
			// Utilisation de la classe calendar
			//Calendar gc = new GregorianCalendar(dateCreneau.getDay(),dateCreneau.getMonth(),dateCreneau.getYear());
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			/*
			 * 		Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					cal.setTime(sdf.parse("Mon Mar 14 16:02:37 GMT 2011"));// all done

			 */
			// String formattedDate = myDateObj.format(myFormatObj);
			
			//Calendar laDate1 = gc.setTime(sdf.parse(debutFormationStr));
			//System.out.println("gc : "+sdf.format(gc.getTime()));
}
