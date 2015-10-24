package SeminarkaPT;

public class Planet extends Entity {
	
	/** promenna uchovavajici informaci o tom zda se na planetu budou nadale dovazet leky  */
	private boolean status;
	/** promenna uchovavajici informaci o poctu obyvatel */
	private int populCount;
	/** promenna uchovajici informaci o vlastni produkci leku*/
	private double drugProduction;
	
	  															/* konstruktor */	     
	/**
	* Vytvori planetu se zadanym id a souradnicemi, poctem sousedu
	* @param id 
	* @param xSour, ySour
	* @param pocetSousC
	*/ 
	public Planet(int id, double xAxis, double yAxis, int neighbourCount) {
		super(id, xAxis, yAxis, neighbourCount);
		status = true;
		//populCount = 												TADY JE TREBA DODELAT GENEROVANI OBYVATEL 100K-10M, ridi se normalnim/Gaussovo rozdelenim, stredni hodnota je 3M
	}	  
	
	/**
	 * Tato metoda urcuje vlastni produkci leku na planete
	 * @param populCount
	 * @return
	 */
	public double drugProduction(int populCount) {
		//TADY JE TREBA DODELAT GENEROVANI PRODUKCE, TJ. 20-80% KRÁT POCET OBYVATEL
  		return drugProduction;
  	}
	
	/**
	 * Tato metoda zjisti zda je dostatek leku, pokud ne, snizi prislusny pocet obyvatel
	 * @param populCount
	 * @return
	 */
	public int enoughDrugProduction(int populCount) {
		if(drugProduction < populCount){
			this.populCount = (int) drugProduction - populCount;
		}
  		return this.populCount;
  	}
	
	/**
	 * Tato metoda vraci informaci o to zda se na planetu budou nadale dovazet leky
	 * @param populCount
	 * @return
	 */
	public boolean planetStatus(int populCount) {
		if(populCount < 40000){
			status = false;
		}
  		return status;
  	}
}