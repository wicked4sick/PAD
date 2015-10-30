/**
 * ElectricityTariffCalculator.java
 * 
 * calculates the price of electricity per year for different tariff models
 */

// import Scanner class for command line reading
import java.util.Scanner;

public class ElectricityTariffCalculator {
	/**
	 * Calculates the energy price per year at a given energy consumption per year for 3 different Tariffs.
	 * 
	 * @param args
	 * @param energy_consumption energy consumption per year
	 * 
	 * @param BASE_RATE_EASY basic price for energy per month (Easy-Model)
	 * @param BASE_RATE_COMPACT basic price for energy per month (Compact-Model)
	 * @param BASE_RATE_FLAT basic price for energy per month (Flat-Model)
	 * 
	 * @param USAGE_RATE_EASY price per used kWh energy (Easy-Model)
	 * @param USAGE_RATE_COMPACT price per used kWh energy (Compact-Model)
	 * @param USAGE_RATE_FLAT price per used kWh energy (Flat-Model)
	 * 
	 * @param fee_easy price per year for consumed energy (Easy-Model)
	 * @param fee_compact price per year for consumed energy (Compact-Model)
	 * @param fee_flat price per year for consumed energy (Flat-Model)
	 * 
	 * @param FLAT_RATE Price of the Flat-Tariff within the Flat-Package
	 * @param FLAT_VOLUME Consumed energy of the Flat-Tariff within the Flat-Package
	 * 
	 * @param tariff_cheapest_price lowest price per year for consumed energy
	 * @param tariff_name String for name of the lowest Tariff
	 */
	public static void main(String[] args) {
		
		// constants for tariff calculation
		final double	BASE_RATE_EASY = 0.0,		BASE_RATE_COMPACT = 5.5,		BASE_RATE_FLAT = 0.0,
						USAGE_RATE_EASY = 0.2448,	USAGE_RATE_COMPACT = 0.2144,	USAGE_RATE_FLAT = 0.2688,
						FLAT_RATE_EASY = 0,			FLAT_RATE_COMPACT = 0,			FLAT_RATE_FLAT = 1111.0,
						FLAT_VOLUME_EASY = 0,		FLAT_VOLUME_COMPACT = 0,		FLAT_VOLUME_FLAT = 5600.0;
		
		// variables for tariff calculation
		double energy_consumption;
		int fee_easy = 0, fee_compact = 0, fee_flat = 0, tariff_cheapest_price = 0;
		String tariff_cheapest_name = new String();
		
		tariff_cheapest_price=0;
		
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Geben Sie den Jahresstromverbrauch in kWh ein und drücken Sie die Enter-Taste");
		
		// read command line and interpret as Double
		energy_consumption = scan.nextDouble();
		
		// TODO examine if user inserted a number
		if (energy_consumption>=0){
			
			// calculate prices for all tariffs rounded to the greatest included Integer
			fee_easy=tariff_calculation(energy_consumption, BASE_RATE_EASY, USAGE_RATE_EASY, FLAT_RATE_EASY, FLAT_VOLUME_EASY);
			fee_compact=tariff_calculation(energy_consumption, BASE_RATE_COMPACT, USAGE_RATE_COMPACT, FLAT_RATE_COMPACT, FLAT_VOLUME_COMPACT);
			fee_flat=tariff_calculation(energy_consumption, BASE_RATE_FLAT, USAGE_RATE_FLAT, FLAT_RATE_FLAT, FLAT_VOLUME_FLAT);
			
			// choose cheapest tariff
			tariff_cheapest_price = tariff_cheapest_price(fee_easy, fee_compact, fee_flat);
			tariff_cheapest_name = tariff_cheapest_name(fee_easy, fee_compact, fee_flat);
			
		} else {
			
			// exit program if anything else than positive numbers are inserted
			System.out.println("Sie müssen einen positiven Wert eingeben!");
			System.out.println("System beendet");
			System.exit(0);
			
		} // end if
		
		// output the prices of the different Tariffs and a suggestion of choice (cheapest).
		System.out.println("Alle Preise verstehen sich inkl. aller Steuern und Abgaben.");
		System.out.println("Der Preis für " + energy_consumption + "kWh kostet " 
							+ "im Easy-Modell: " + fee_easy + "€, "
							+ "im Kompakt-Modell: " + fee_compact + "€ und "
							+ "im Flat-Modell: " + fee_flat + "€.");
		System.out.println("Wir empfehlen den " + tariff_cheapest_name + " für " + tariff_cheapest_price + "€.");
	}
	
	/**
	 * calculates the tariff with the given input
	 * 
	 * @param energy_consumption energy consumption per year (kWh)
	 * @param base_rate monthly pay rate
	 * @param usage_rate rate per kWh energy
	 * @param flat_rate fee for given volume
	 * @param flat_volume volume, which is handled with a fix pricing
	 * 
	 * @return fee to be paid
	 */
	static int tariff_calculation(double energy_consumption, double base_rate, double usage_rate, double flat_rate, double flat_volume){
		int fee = 0;
		if (energy_consumption<=flat_volume){
			// if flat rate is given and and the amount is not exhausted
			fee =(int) Math.floor(12.0*base_rate+flat_rate);
		} else {
			// if no flat rate is given or the amount is exceeded
			fee =(int) Math.floor(12.0*base_rate+flat_rate+(energy_consumption-flat_volume)*usage_rate);
		} // end if
		return fee;
	}
	
	/**
	 * Calculates the cheapest price and returns the rate.
	 * 
	 * @param fee_easy price per year for consumed energy (Easy-Model)
	 * @param fee_compact price per year for consumed energy (Compact-Model)
	 * @param fee_flat price per year for consumed energy (Flat-Model)
	 * 
	 * @return tariff_cheapest_price rate of the cheapest tariff
	 */
	static int tariff_cheapest_price(int fee_easy, int fee_compact, int fee_flat){
		int tariff_cheapest_price = 0;
		
		if (fee_easy<fee_compact && fee_easy<fee_flat){
			tariff_cheapest_price = fee_easy;
		} else if (fee_compact<fee_flat){
			tariff_cheapest_price = fee_compact;
		} else {
			tariff_cheapest_price = fee_flat;
		} // end if
		
		return tariff_cheapest_price;
	}
	
	/**
	 * Calculates the cheapest price and returns the name.
	 * 
	 * @param fee_easy price per year for consumed energy (Easy-Model)
	 * @param fee_compact price per year for consumed energy (Compact-Model)
	 * @param fee_flat price per year for consumed energy (Flat-Model)
	 * 
	 * @return tariff_cheapest_name name of the cheapest tariff
	 */
	static String tariff_cheapest_name(int fee_easy, int fee_compact, int fee_flat){
		String tariff_cheapest_name = new String();
		
		if (fee_easy<fee_compact && fee_easy<fee_flat){
			tariff_cheapest_name = "Easy-Tarif";
		} else if (fee_compact<fee_flat){
			tariff_cheapest_name = "Kompakt-Tarif";
		} else {
			tariff_cheapest_name = "Flat-Tarif";
		} // end if
		
/*
 * 	 * @param uBE upper boundary for the Easy-Model
	 * @param uBK upper boundary for the Compact-Model
	 * 
		// TODO evtl löschen dieser Zeile und löschen aller Verweise (Methoden-Kommentar, Deklaration)
		// ich habe Auswahl des günstigsten anders gelöst.
		// the boundaries (kWh), at which the next Pricing-Model is better than the previous one
		uBE = 2171.05;
		uBK = 4874.07;
*/
		return tariff_cheapest_name;
	}
}
