package semestralka;


public class Planeta extends Entita {
	  															/* konstruktor */
	     
	  /**
	   * Vytvori planetu se zadanym id a souradnicemi, poctem sousedu
	   * @param id 
	   * @param xSour, ySour
	   * @param pocetSousC
	   */ 
	  public Planeta(int id, double xSour, double ySour, int pocetSous) {
		  super(id, xSour, ySour, pocetSous);
	  }	   
}