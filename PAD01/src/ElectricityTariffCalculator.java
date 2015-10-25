
/**
 * ElectricityTariffCalculator.java
 * 
 * calculates the price of electricity per year
 */

// import Scanner class for command line reading
import java.util.Scanner;

public class ElectricityTariffCalculator {
	/**
	 * 
	 * @param args
	 */
   public static void main(String[] args) {
      
      // variables for the problem
      double fahrenheit, celsius;
      
      Scanner scan = new Scanner (System.in);
      
      System.out.println("Geben Sie eine Temperatur in Fahrenheit ein und dr�cken Sie die Enter-Taste");
      
      // read command line and interpret as Double
      fahrenheit = scan.nextDouble();
      
             
      // calculate celsius and round it to 1/100 degrees
      celsius = 5.0 / 9 * (fahrenheit - 32);               
      // use Math class for round
      celsius = Math.round(celsius * 100);                
      celsius = celsius / 100.0;                           
      
      
      // output the temperature in celsius
      System.out.println(fahrenheit + " Grad Fahrenheit entsprechen "       
                   + celsius + " Grad Celsius.");
   }
}
