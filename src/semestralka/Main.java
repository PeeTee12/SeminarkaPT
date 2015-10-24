package semestralka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/** vytvori ArrayList do ktereho se budou ukladat objekty Entita */
		ArrayList<Entity> entity = new ArrayList<Entity>();
		
		/** vytvori promenou ktera urcuje pocet central v galaxii */
		int pocetCent = 5;
		/** vytvori promenou ktera urcuje pocet sousedu kazde centraly */
		int pocetSousC = 20; 
		System.out.println("Zadej pocet planet");
		/** vytvori promenou ktera urcuje pocet planet v galaxii */
		int pocetPlan = sc.nextInt();
		/** vytvori promenou ktera urcuje pocet sousedu kazde planety(v zadani 5) */
		int pocetSousP = 6;
		/** vytvori matici do ktere se budou ukladat id nejblizsich 6(5) sousedu */
		int[][] mSousId = new int[pocetPlan+pocetCent][pocetSousP];
		
		/** zavola metodu, ktera vytvori centraly */
		entity = Data.rozlozeniCentral(pocetCent, pocetPlan, entity, pocetSousC);
		/** zavola metodu, ktera vytvori planety */
		entity = Data.rozlozeniPlanet(pocetCent, pocetPlan, entity, pocetSousP);		
		/** zavola metodu, ktera vyplni matici Id sousedu */
		mSousId = Data.idSousedu(pocetPlan, pocetCent, pocetSousP); 
		/** zavola metodu, ktera nazorne vykresli galaxii, tj. plnety,  centraly a cesty mezi nimi */
		new Mapa(pocetPlan,pocetCent, entity, mSousId);
	}
}