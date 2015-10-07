package semestralka;

import java.util.Random;

public class Entita {
																	/* deklarace promennych */
	Random r = new Random();
	/** identifikacni cislo objektu */
	protected int id;
	/** x souradnice objektu */
	protected double xSour;
	/** y souradnice objektu */
	protected double ySour;
	/** pocet sousedu objektu */
	protected int pocetSous;
	
	
				
	 																	/* konstruktor */
	 public Entita(int id, double xSour, double ySour, int pocetSous) {
		 this.id = id;
		 this.xSour = xSour;
		 this.ySour = ySour;
		 this.pocetSous = pocetSous;
	}
	 
																		 /* gettery */
	 public int getId() {
		 return id;
	 }
	  
	 public double getXSour() {
		 return xSour;
	 }
	  
	 public double getYSour() {
		 return ySour;
	 }

	 public int getPocetSous() {
		 return pocetSous;
	 }
}