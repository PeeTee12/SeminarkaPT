package semestralka;


public class Planet extends Entity {
	  															/* konstruktor */
	     
	  /**
	   * Vytvori planetu se zadanym id a souradnicemi, poctem sousedu
	   * @param id 
	   * @param xSour, ySour
	   * @param pocetSousC
	   */ 
	  public Planet(int id, double xAxis, double yAxis, int neighbourCount) {
		  super(id, xAxis, yAxis, neighbourCount);
	  }	   
}