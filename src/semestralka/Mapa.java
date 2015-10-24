package semestralka;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class Mapa extends JFrame{

//test!!
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int pocetCent;
	int pocetPlan;
	ArrayList<Entity> ar;
	int [][] mSousId;
	//ArrayList<Pivovar> ar3;

	public Mapa(int pocetPlan,int pocetCent, ArrayList<Entity>ar,int [][] mSousId) {
		this.pocetCent = pocetCent;
		this.pocetPlan = pocetPlan;
		this.ar = ar;
		this.mSousId= mSousId;
		
		this.setTitle("Mapa");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		paint2D((Graphics2D)g);
	}
	
	public void paint2D(Graphics2D g2) {
	
		System.out.println("Prave jsem vstoupil do kresleni mapy");
		g2.setColor(Color.BLUE);
		for(int i = pocetCent; i < ar.size(); i++) {
			g2.fill(new Ellipse2D.Double(ar.get(i).getXAxis()+100, ar.get(i).getYAxis()+130 , 4, 4));
			//System.out.println("Prave jsem nakreslil planetu c.: "+i+" na souradnicich X: "+ar.get(i).getxSour()+" a Y: "+ar.get(i).getySour());
		}
		
		
		g2.setColor(Color.GREEN);
		for(int j = 0; j < pocetCent; j++) {
			g2.fill(new Ellipse2D.Double(ar.get(j).getXAxis()+100, ar.get(j).getYAxis()+130, 7, 7));
			//System.out.println("Prave jsem nakreslil planetu c.: "+i+" na souradnicich X: "+ar2.get(i).getXSour()+" a Y: "+ar2.get(i).getYSour());
		}
		
		
		g2.setColor(Color.RED);
		g2.fill(new Ellipse2D.Double(400 + 100, 400 + 130, 10, 10));
		
		
		/*
		g2.setColor(Color.BLACK);
		g2.fill(new Ellipse2D.Double(ar4.get(0).getxSour() - 2 + 100, ar4.get(0).getySour() - 2 + 100, 4, 4));
		*/
		
		/*
		 * kontrola id
		for (int i = 0; i < mSousId.length; i++) {
			System.out.println("Kreslim: "+i+ " pocX: "+ (ar.get(i).getxSour() - 2 + 100)+" konX: "+(ar.get(mSousId [i][1]).getxSour() - 2 + 100)+" pocY: "+(ar.get(i).getySour() - 2 + 100)+" koncY: "+(ar.get(mSousId [i][1]).getxSour() - 2 + 100 ));
		}*/
		
		
		g2.setColor(Color.BLACK);
		for (int i = 0; i < ar.size(); i++) {	
			g2.draw(new Line2D.Double(ar.get(i).getXAxis()+2+100, ar.get(i).getYAxis()+2+130, ar.get((mSousId [i][1])).getXAxis()+2+100,  ar.get((mSousId [i][1])).getYAxis()+2+130));
			g2.draw(new Line2D.Double(ar.get(i).getXAxis()+2+100, ar.get(i).getYAxis()+2+130, ar.get((mSousId [i][2])).getXAxis()+2+100,  ar.get((mSousId [i][2])).getYAxis()+2+130));
			g2.draw(new Line2D.Double(ar.get(i).getXAxis()+2+100, ar.get(i).getYAxis()+2+130, ar.get((mSousId [i][3])).getXAxis()+2+100,  ar.get((mSousId [i][3])).getYAxis()+2+130));
			g2.draw(new Line2D.Double(ar.get(i).getXAxis()+2+100, ar.get(i).getYAxis()+2+130, ar.get((mSousId [i][4])).getXAxis()+2+100,  ar.get((mSousId [i][4])).getYAxis()+2+130));
			g2.draw(new Line2D.Double(ar.get(i).getXAxis()+2+100, ar.get(i).getYAxis()+2+130, ar.get((mSousId [i][5])).getXAxis()+2+100,  ar.get((mSousId [i][5])).getYAxis()+2+130));
						
						
			//System.out.println( "Prave jsem nakreslil drahu mezi planetou c.: " +i+ " na souradnicich X: " +ar.get(i).getxSour()+ " a Y: " +ar.get(i).getySour()+ " a mezi planetou c.: "+mSousId [i][1]+" na souradnicich X: "+ar.get(mSousId [i][1]).getxSour()+" a Y: "+ar.get(mSousId [i][1]).getySour() );
		} 
	}
}
