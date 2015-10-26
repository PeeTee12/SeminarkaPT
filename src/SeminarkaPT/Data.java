package SeminarkaPT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Data {
	/** pole se vzdalenostmi entit */
	private static double[][] distance;		
	/** pole se 5 nejblizsimi vzdalenostmi */
	private static double[][] adjDistance;		
	/** pole se 5 Id nejblizsich vzdalenosti */
	private static int[][] adjId;
	/** x-ove a y-ove souradnice objektu */
	private static double xAxis, yAxis;
	private static double actDist = 0;
	 
	
	/**
	 * Tato metoda vytvori centraly na pozadovanych(vyhodnych) souradnicich
	 * @param factoriesCount
	 * @param planetsCount
	 * @param entity
	 * @param adjFactoriesCount
	 * @return
	 */
	 public static ArrayList<Entity> factoriesDistribution(int factoriesCount, int planetsCount, int neighbourCountF, ArrayList<Entity> entities) {
		 distance = new double[planetsCount+factoriesCount][planetsCount+factoriesCount];     		//deklaruje velikost matice uchvavajici vzdalenost objektu
		 
		 for (int i = 0; i < factoriesCount; i++) {													// vytvori centraly rozlozene pravidelne na kruznici se stredem ve stredu souradneho systemu	  
			 xAxis = 200*Math.cos(Math.toRadians(72*i))+400;                                  		// vypocte x-ovou souradnici
			 yAxis = (-1*200*Math.sin(Math.toRadians(72*i)))+400;                             		// vypocte y-ovou souradnici
			 Factory centrala = new Factory(i, xAxis, yAxis, neighbourCountF);              		// vytvori objekt centrala s pozadovanymi parametry
			 entities.add(centrala);                                                         		// prida objekt centraly do AL entity
		  }
		
		 /* novejsi zpusob by melbyt presnejsi
		 for (int i = 0; i < factoriesCount; i++) {													// nastavi vzdalenost mezi centralami 100000(inf), aby se nevolily za sousedy
			 for (int j = 0; j < factoriesCount; j++) {
				 distance[i][j] = 100000;
				 distance[j][i] = 100000;
			 }
		 }  */
		 for (int i = 0; i < factoriesCount; i++) {	
			 for(int j = 0; j < factoriesCount; j++) {												// spocte zda  nove vytvarena planeta je dostatecne daleko od vsech vytvorenych entit
				actDist = Math.sqrt( Math.pow( (entities.get(j).getXAxis() - entities.get(i).getXAxis()), 2) + Math.pow((entities.get(j).getYAxis() - entities.get(i).getYAxis()), 2) );
	 			distance[i+factoriesCount][j] = actDist;											// ulozi vzdalenost prave vytvarene planety od entity j do pomocneho AL
	 			distance[j][i+factoriesCount] = actDist;											// matice distance je symetricka
			 }	
		 }
		 return entities;
	  }
	 
	 /**
	 * Generuje nahodne rozlozeni planet na zadanem uzemi.
	 * @param factoriesCount
	 * @param planetsCount
	 * @param entity
	 * @param neighbourCountP
	 * @return
	 */
	 public static ArrayList<Entity> planetsDistribution(int factoriesCount, int planetsCount,int neighbourCountP, ArrayList<Entity> entities) {
		 int boundX = 800+2,  boundY = 800+2;													   // nasteaveni mezi
		 ArrayList<Double> auxDist = new ArrayList<Double>();		   							   // pomocny ArrayList, ktery slouzi k ukladani vzdalenosti planet a nasledne kontroly splneni podminky min. vzdalenosti 2	
		 Random rX = new Random(), rY = new Random();
	
		 int i = 0;
		 while(i < planetsCount) {																	// cyklus, ktery bezi, tak dlouho, dokud nejsou vytvoreny vsechny pozadovane planety
			 auxDist.clear();																		// vycisti AL s pomocnymi vzdalenostmi, aby se nepletli s novymi(AL by se zvetoval do nekonecna)
			 xAxis = rX.nextInt(boundX);													    	// vygeneruje nahodne X, Y souradnice v zadanem rozmezi
	 		 yAxis = rY.nextInt(boundY); 	
	 		 for(int j = 0; j < entities.size(); j++) {												// spocte zda  nove vytvarena planeta je dostatecne daleko od vsech vytvorenych entit
	 			 actDist = Math.sqrt( Math.pow( (entities.get(j).getXAxis() - xAxis), 2) + Math.pow((entities.get(j).getYAxis() - yAxis), 2) );
		 		 auxDist.add(actDist);																// ulozi vzdalenost prave vytvarene planety od entity j do pomocneho AL
		 		 distance[i+factoriesCount][j] = actDist;											// matice distance je symetricka
		 		 distance[j][i+factoriesCount] = actDist;
		 	 }	
	 		 actDist = Collections.min(auxDist); 													// vybere nejmensi vzdalenost prave vytvarene planety a nejblizsi entity
	 		 if(actDist > 2) { 																		// overi, zda dana planeta je dostatecne daleko, pokud ano, tak ji vytvori a prida do AL entit
	 		 		Planet pl = new Planet(i, xAxis, yAxis, neighbourCountP);
	 		 		entities.add(pl);
	 		 		i++;
	 		 }
		  }		
	 	  return entities;
	  }
	  
	 /**
	  * Tato metoda zjisti Id sousedu
	  * @param planetsCount
	  * @param factoriesCount
	  * @param neighbourCountP
	  * @return
	  * @throws IOException
	  */
	 public static int [][] idSousedu(int factoriesCount, int planetsCount, int neighbourCountP) throws IOException {	
		 adjDistance = new double[factoriesCount+factoriesCount][neighbourCountP];                  // deklaruje velikost matice uchvavajici vzdalenost sousednich objektu
		 adjId = new int[factoriesCount+factoriesCount][neighbourCountP];                           // deklaruje velikost matice uchvavajici Id sousednich objektu
		 double[] auxAr = new double[planetsCount+factoriesCount];									// pomocne pole pro serazeni vzdalenosti
		 BufferedWriter bw1 = new BufferedWriter(new FileWriter("seznamVzdal.txt"));				// BW na vypis do textaku vzdalenosti entit
		 BufferedWriter bw2 = new BufferedWriter(new FileWriter("seznamVzdalSeraz.txt"));			// BW na vypis do textaku serazenych vzdalenosti entit
		 BufferedWriter bw3 = new BufferedWriter(new FileWriter("seznamId.txt"));					// BW na vypis do textaku Id sousedu
		  
		 for (int i = 0; i < planetsCount+factoriesCount; i++) {
			 for (int j = 0; j < planetsCount+factoriesCount; j++) {
				 if(j < factoriesCount) {
					 auxAr [j] = distance[i][j];
				 }
				 else {
					 auxAr [j] = distance[i+factoriesCount][j];
				 }
				 auxAr [j] = distance[i][j];														// ulozi vzdalenost objektu i a j do pole, ktere se pak seradi		
				 if(j == 0){
					 bw1.write("Entita c."+(i+1)+":  ");											// pro kazdy prvek udela novy popisek, pouze pro prehlednost textu		
				 }
				 bw1.write(Math.floor(distance [i][j])+"  ");										// vypise vzdalenost entity i od entity j, zakrouhleni je z duvodu setreni mista
				 if(j == planetsCount+factoriesCount-1){											// nez se zacne pracovat s dalsim objektem odradkuje, opet pouze pro prehlednost textu	
					 bw1.newLine();
					 bw1.newLine();
				 }
			 }	
			 Arrays.sort(auxAr);																	//seradi vzdalensoti objektu i a vsech ostatnich
			
			 for (int j = 0; j < neighbourCountP; j++) {											// v tomto foru se najde 5 nejblizsich sousedu objektu i
				 adjDistance [i][j] = auxAr [j] ;													//ulozi 5 nejblizsich sousedu pro nasledne vytvoreni cest			
				 if(j == 0){
					 bw2.write("Planeta c."+(i+1)+":  ");											// pro kazdy prvek udela novy popisek, pouze pro prehlednost textu	
				 }
				 bw2.write(Math.floor(adjDistance [i][j])+"  ");									// vypise vzdalenosti 5 nejblizsich entit 
				 if(j == neighbourCountP-1){														// nez se zacne pracovat s dalsim objektem odradkuje, opet pouze pro prehlednost textu
					 bw2.newLine();
					 bw2.newLine();
				 }
			 }
			  
			 for (int j = 1; j < neighbourCountP; j++) {											// v tomto foru se zjisti Id 5 nejblizsich sousedu objektu i
				 for (int k = 0; k < planetsCount+factoriesCount; k++) {
					 if( adjDistance [i][j] == distance [i][k]) {									// zjistujeme, kdy se vzdalenosti rovnaji, predpokladame, ze nemuze nastat nejednoznacnost
						 adjId [i][j] = k;															//zjisti id nejblizsich 5 sousedu
					 }
				 }
				 if(j == 1){
					 bw3.write("Planeta c."+(i+1)+":  ");											// pro kazdy prvek udela novy popisek, pouze pro prehlednost textu	
				 }
				 bw3.write(Math.floor(adjId [i][j])+"  ");
				 if(j == neighbourCountP-1){														// nez se zacne pracovat s dalsim objektem odradkuje, opet pouze pro prehlednost textu
					 bw3.newLine();
					 bw3.newLine();
				 }
			}
		}
		bw1.close();																				// uzavre vypis do souboru		
		bw2.close();																				// uzavre vypis do souboru
		bw3.close();																				// uzavre vypis do souboru
		return adjId; 
	  }	  
	 
	 public static double[][] getDistance() {	
		 return distance;
	 }
}


