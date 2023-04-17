package view;

import java.text.ParseException;

import controller.ControlCreneau;
import model.Creneau;

public class TestDate {
//private Creneau cren;
//private ControlCreneau ccren;

	public static void main(String[] args) {

try {
	ControlCreneau cren = new ControlCreneau("29-08-2022","02-09-2022");
} 
catch (ParseException e) {
	System.out.println("Erreur dans la creation de l instance cd ControlCreneau");
	e.printStackTrace();
}
	}

}
