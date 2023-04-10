package view;

import java.text.ParseException;

import controller.ControlCreneau;
import model.Creneau;

public class TestDate {
//private Creneau cren;
//private ControlCreneau ccren;

	public static void main(String[] args) {

try {
	ControlCreneau cren = new ControlCreneau("29-08-2022","22-06-2023");
} catch (ParseException e) {

	e.printStackTrace();
}
	}

}
