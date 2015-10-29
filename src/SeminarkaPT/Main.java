package SeminarkaPT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/** vytvori ArrayList do ktereho se budou ukladat objekty Entita */
		ArrayList<Entity> entities = new ArrayList<Entity>();
		/** vytvori ArrayList do ktereho se budou ukladat objekty Entita jako vrcholy grafu */
		ArrayList<Vertex> entitiesV = new ArrayList<Vertex>();		
		/** vytvori promenou ktera urcuje pocet central v galaxii */
		int factoriesCount = 5;
		/** vytvori promenou ktera urcuje pocet sousedu kazde centraly */
		int neighbourCountF = 20; 
		System.out.println("Zadej pocet planet");
		/** vytvori promenou ktera urcuje pocet planet v galaxii */
		int planetsCount = sc.nextInt();
		/** vytvori promenou ktera urcuje pocet sousedu kazde planety(v zadani 5) */
		int neighbourCountP = 5;
		/** vytvori pole do ktereho se bude ukladat pst vrcholu na ceste z vrcholu i do vrcholu j*/
		int[][] shortestPath = new int[planetsCount][planetsCount]; 		
		/** vytvori matici do ktere se budou ukladat id nejblizsich 6(5) sousedu */
		int[][] adjIdF = new int[factoriesCount][neighbourCountF];
		int[][] adjIdP = new int[planetsCount][neighbourCountP];
		
		/** zavola metodu, ktera vytvori centraly */
		entities = Data.factoriesDistribution(factoriesCount, planetsCount, neighbourCountF, entities);
		/** zavola metodu, ktera vytvori planety */
		entities = Data.planetsDistribution(factoriesCount, planetsCount, neighbourCountP, entities);		
		/** zavola metodu, ktera vyplni matici Id sousedu */
		adjIdF = Data.idSousedu(factoriesCount, planetsCount, neighbourCountF);
		/** zavola metodu, ktera vyplni matici Id sousedu */
		adjIdP = Data.idSousedu(planetsCount, factoriesCount, neighbourCountP); 
		/** zavola metodu, ktera nazorne vykresli galaxii, tj. plnety,  centraly a cesty mezi nimi */
		new Mapa(factoriesCount, planetsCount, neighbourCountF, neighbourCountP, entities, adjIdF, adjIdP);
		
	
		/** V tomto foru se  na planety "nasadi na grafovou strukturu" */
		
	
		for(int i = 0; i < planetsCount+factoriesCount; i ++){
			if (i<factoriesCount) {
				Vertex veretexesF = new Vertex(i, 'B', neighbourCountF, adjIdF, entitiesV);
				entitiesV.add(veretexesF);
			}else {
				Vertex veretexesP = new Vertex(i, 'B', neighbourCountP, adjIdP, entitiesV);	
				entitiesV.add(veretexesP);
			}   
		}	
		
		/*
		// zde bude volani metody, ktera naplni
		shortestPath = Graph.shortestPath(entitiesV);
		
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("vrcholVzdal.txt", true));				// BW na vypis do textaku vzdalenosti entit
		
		for (int i = 0; i < shortestPath.length; i++) {
			for (int j = 0; j < shortestPath.length; j++) {												
				
				if(j == 0){
					bw1.write("Entita c."+(i+1)+":  ");												// pro kazdy prvek udela novy popisek, pouze pro prehlednost textu	
				}
				bw1.write(shortestPath[i][j]+"  ");										// vypise vzdalenosti 5 nejblizsich entit 
				if(j == shortestPath.length-1) {														// nez se zacne pracovat s dalsim objektem odradkuje, opet pouze pro prehlednost textu
					bw1.newLine();
					bw1.newLine();
				}
			}			
		}
		bw1.close();
		*/
	}
}