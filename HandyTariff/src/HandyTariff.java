// HandyTariff.java
//
// calculates the monthly fee for a handy tariff

//import scanner class for command line reading
import java.util.Scanner;

public class HandyTariff{   
    public static void main(String[] args) {
		
	// constants for tariff calculation
	final double		  BASE_RATE = 5.95,
		LOW_VOLUME = 60,  LOW_RATE = .11,
		HIGH_VOLUME = 120,HIGH_RATE = .03,
		FLAT_VOLUME = 300,FLAT_RATE = 17.95;
			
	// assume volume is in full minutes
	int volume; 
	double fee;
	
	Scanner scan = new Scanner(System.in);
	System.out.println("Geben Sie Ihr monatliches"
		+" Gesprächsvolumen in Minuten an und "
		+"drücken Sie die Eingabetaste: ");		
	// read command line and interpret as int
	volume =  scan.nextInt();
	
	// calculate fee
	if (volume <= LOW_VOLUME) { // BASE_RATE applies
		fee = BASE_RATE;
	} else if (volume <= HIGH_VOLUME) { // LOW_RATE applies
		fee = BASE_RATE + LOW_RATE * (volume - LOW_VOLUME);
	} else if (volume <= FLAT_VOLUME) { // HIGH_RATE applies
	    fee = BASE_RATE + LOW_RATE * (HIGH_VOLUME - LOW_VOLUME)
		                + HIGH_RATE * (volume - HIGH_VOLUME);
	} else { // volume > FLAT_VOLUME, FLAT_RATE applies
		fee = FLAT_RATE;
	}
	
	// round to Eurocent
	fee = Math.round(fee * 100) / 100.0;
	//output the calculated fee		
	System.out.println("Bei " + volume + " Gesprächsminuten "
			   + "zahlen Sie " + fee + " Euro.");
    }
}
