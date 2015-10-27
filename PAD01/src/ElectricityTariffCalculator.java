/**
 * ElectricityTariffCalculator.java
 * 
 * calculates the price of electricity per year
 */

// import Scanner class for command line reading
import java.util.Scanner;

public class ElectricityTariffCalculator {
	/**
	 * Computes the energy price per year at a given energy consumption per year for 3 different Tariffs..
	 * 
	 * @param args
	 * @param jSV energy consumption per year
	 * 
	 * @param gGE basic price for energy per month (Easy-Model)
	 * @param gGK basic price for energy per month (Compact-Model)
	 * @param gGF basic price for energy per month (Flat-Model)
	 * 
	 * @param vPE price per used kWh energy (Easy-Model)
	 * @param vPK price per used kWh energy (Compact-Model)
	 * @param vPF price per used kWh energy (Flat-Model)
	 * 
	 * @param uBE upper boundary for the Easy-Model
	 * @param uBK upper boundary for the Compact-Model
	 * 
	 * @param jSPE price per year for consumed energy (Easy-Model)
	 * @param jSPK price per year for consumed energy (Compact-Model)
	 * @param jSPF price per year for consumed energy (Flat-Model)
	 * 
	 * @param FP Price of the Flat-Tariff within the Flat-Package
	 * @param FU Consumed energy of the Flat-Tariff within the Flat-Package
	 * 
	 * @param jSPB lowest price per year for consumed energy
	 * @param tariff String for the lowest Tariff
	 */
	public static void main(String[] args) {
		
		// variables for the problem
		double jSV, gGE, gGK, gGF, vPE, vPK, vPF, uBE, uBK, FP, FU;
		int jSPE, jSPK, jSPF, jSPB;
		String tariff;
		
		// initialization of the Tariff-Values
		gGE = 0.0;
		vPE = 0.2448;
		gGK = 5.5;
		vPK = 0.2144;
		gGF = 0.0;
		vPF = 0.2688;
		FP = 1111.0;
		FU = 5600.0;
		tariff = "Fehler";
		jSPB=0;
		
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Geben Sie den Jahresstromverbrauch in kWh ein und drücken Sie die Enter-Taste");
		
		// read command line and interpret as Double
		jSV = scan.nextDouble();
/*
		// TODO examine if user inserted a number
		if (jSV==0){
			
		} else {
			//exit with error message
			System.out.println("Sie müssen eine Zahl eingeben!");
			
		}
*/
		
/*
		// TODO evtl löschen dieser Zeile und löschen aller Verweise (Methoden-Kommentar, Deklaration)
		// ich habe Auswahl des günstigsten anders gelöst.
		// the boundaries (kWh), at which the next Pricing-Model is better than the previous one
		uBE = 2171.05;
		uBK = 4874.07;
*/
		
		// calculation of the prices for all Tariffs rounded to the greatest included full Integer.
		jSPE=(int) Math.floor(12.0*gGE+(vPE*jSV));
		jSPK=(int) Math.floor(12.0*gGK+(vPK*jSV));
		if (jSV<=FU){
			jSPF=(int) Math.floor(12.0*gGF+FP);
		} else {
			jSPF=(int) Math.floor(12.0*gGF+FP+(jSV-FU)*vPF);
		}
		
		// choice of cheapest Tariff
		if (jSPE<jSPK & jSPE<jSPF){
			tariff = "Easy-Tarif";
			jSPB = jSPE;
		} else if (jSPK<jSPF & jSPK<jSPE){
			tariff = "Kompakt-Tarif";
			jSPB = jSPK;
		} else if (jSPF<jSPE & jSPF<jSPK){
			tariff = "Flat-Tarif";
			jSPB = jSPF;
		} else {
			System.out.println("Fehler bei Zuweisung oder Rechnung.");
		}
		
		// output the prices of the different Tariffs and a suggestion of choice (cheapest).
		System.out.println("Alle Preise verstehen sich inkl. aller Steuern und Abgaben.");
		System.out.println("Der Preis für " + jSV + "kWh kostet " 
				+ "im Easy-Modell: " + jSPE + "€, "
				+ "im Kompakt-Modell: " + jSPK + "€ und "
				+ "im Flat-Modell: " + jSPF + "€.");
		System.out.println("Wir empfehlen den " + tariff + " für " + jSPB + "€.");
	}
}
