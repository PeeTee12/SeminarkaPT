package semestralka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Data {
	/** pole se vzdalenostmi planet */
	public static double[][] mVzdal;		
	/** pole se 6(5) nejblizsimi vzdalenostmi */
	public static double[][] mSousVzd;		
	/** pole se 6(5) Id nejblizsich vzdalenosti */
	public static int[][] mSousId;
	static int id;
	static double xSour, ySour;
	 
	
	 /** Tato metoda vytvori centraly na pozadovanych(vyhodnych) souradnicich */ 
	 public static ArrayList<Entita> rozlozeniCentral(int pocetCent, int pocetPlan, ArrayList<Entita> entity, int pocetSousC) {
		 mVzdal = new double[pocetPlan+pocetCent][pocetPlan+pocetCent];
		 
		 /** vytvori centraly rozlozene pravidelne na kruznici */
		 for (int i = 0; i < pocetCent; i++) {			  
			 xSour = 200*Math.cos(Math.toRadians(72*i))+400;
			 ySour = (-1*200*Math.sin(Math.toRadians(72*i)))+400;
			 Centrala centrala = new Centrala(i, xSour, ySour, pocetSousC);
			 entity.add(centrala);
		  }
		  
		  /** nastavi vzdalenost central 10000(inf), aby nebyly voleny za sousedy */
		 for (int j = 0; j < pocetCent; j++) {
			 for (int j2 = 0; j2 < pocetCent; j2++) {
				 mVzdal[j][j2] = 10000;
				 mVzdal[j2][j] = 10000;
			 }
		 }
		  
		  return entity;
	  }
	 
	
	  /** Generuje random rozlozeni planet na zadanem uzemi.*/
	 public static ArrayList<Entita> rozlozeniPlanet(int pocetCent,int pocetPlan, ArrayList<Entita> entity, int pocetSousP) {
		 mSousVzd = new double[pocetPlan+pocetCent][pocetSousP]; 
		 mSousId = new int[pocetPlan+pocetCent][pocetSousP];  

		  
		 int mezX = 800+2,  mezY = 800+2;
		 double vzd, vzdAkt = 0;
		 /** pomocny ArrayList, ktery slouzi k ukladani vzdalenosti planet a nasledne kontroly splneni podminky min. vzdalenosti2 */
		 ArrayList<Double> vzdPom = new ArrayList<Double>();		   
		 Random rX = new Random(), rY = new Random();
	

		 int i = 0;
		 /** cyklus, ktery bezi, tak dlouho, dokud nejsou vytvoreny vsechny pozadovane planety */ 
		 while(i < pocetPlan) {
			 vzdPom.clear();																// vycisti AL s pomocnymi vzdalenostmi, aby se nepletli s novymi(AL by se nafoukl do nekonecna)
			 xSour = rX.nextInt(mezX);													    // vygeneruje nahodne X, Y souradnice v zadanem rozmezi
	 		 ySour = rY.nextInt(mezY); 	
	 		 	for(int j = 0; j < entity.size(); j++) {									// spocte zda  nove vytvarena planeta je dostatecne daleko od vdech vytvorenych entit
	 																// pro centraly, tj. j=0;1;2;3;4
	 		 			vzdAkt = Math.sqrt( Math.pow( (entity.get(j).getXSour() - xSour), 2) + Math.pow((entity.get(j).getYSour() - ySour), 2) );
		 		 		vzdPom.add(vzdAkt);	
		 		 	  	mVzdal[j][i+pocetCent] = vzdAkt;
		 		 	  	mVzdal[i+pocetCent][j] = vzdAkt;
	 		 		
	 		 	}	
	 		 vzd = Collections.min(vzdPom); 												// vybere nejmensi vzdaenost prave vytvarene planety a nejblizsi entity
	 		 if(vzd > 2) { 																	// overi, zda dana planeta je dostatecne daleko, pkud ano, tak ji vytvori a prida do AL entit
	 		 		Planeta pl = new Planeta( i, xSour, ySour, pocetSousP);
	 		 		entity.add(pl);
	 		 		i++;
	 		 }
		  }
		
	 	return entity;
	  }
	  
	 
	 /** metoda na zjisteni Id sousedu */
	 public static int [][] idSousedu(int pocetPlan, int pocetCent, int pocetSousP) throws IOException {	
		 double[] seraz = new double[pocetPlan+pocetCent];									// pomocne pole pro serazeni vzdalenosti
		 BufferedWriter bw1 = new BufferedWriter(new FileWriter("seznamVzdal.txt"));		// vypise do textaku vzdalenost entit
		 BufferedWriter bw2 = new BufferedWriter(new FileWriter("seznamVzdalSeraz.txt"));	// vypise do textaku serazene vzdalenosti entit
		 BufferedWriter bw3 = new BufferedWriter(new FileWriter("seznamId.txt"));			// vypise do textaku Id sousedu
		  
		 for (int k = 0; k < pocetPlan+pocetCent; k++) {
			 for (int l = 0; l < pocetPlan+pocetCent; l++) {
				 seraz [l] = mVzdal[k][l];													//ulozi prislusny radek do pole, ktere se pak seradi		
				  if(l == 0){
					  bw1.write("Entita c."+(k+1)+":  ");
				  }
				  bw1.write(Math.floor(mVzdal [k][l])+"  ");								// vypise vzdalenost entity k od entity l
				  if(l == pocetPlan+pocetCent-1){
					  bw1.newLine();
					  bw1.newLine();
				  }
			 }	
			 Arrays.sort(seraz);															//seradi prislusny radek z mVzdal
			 for (int m = 0; m < pocetSousP; m++) {
				 mSousVzd [k][m] = seraz [m] ;												//ulozi 6(5) nejblizsich sousedu pro nasledne vytvoreni cest			
				 if(m == 0){
					 bw2.write("Planeta c."+(k+1)+":  ");
				 }
				 bw2.write(Math.floor(mSousVzd [k][m])+"  ");								// vypise vzdalenosti  6(5) nejblizsich entit 
				 if(m == pocetSousP-1){
					 bw2.newLine();
					 bw2.newLine();
				 }
			 }
			  
			 for (int n = 1; n < pocetSousP; n++) {
				 for (int o = 0; o < pocetPlan+pocetCent; o++) {
					 if( mSousVzd [k][n] == mVzdal [k][o]) {
						 mSousId [k][n] = o;											//zjisti id nejblizsich 6(5) sousedu
					 }
				 }
				 if(n == 1){
					 bw3.write("Planeta c."+(k+1)+":  ");
				 }
				 bw3.write(Math.floor(mSousId [k][n])+"  ");
				 if(n == pocetSousP-1){
					 bw3.newLine();
					 bw3.newLine();
				 }
			}
		}
		bw1.close();																	// uzavre vypis do souboru		
		bw2.close();
		bw3.close();
		return mSousId; 
	  }	  
}
