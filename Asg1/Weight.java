/** Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/16/2023
 * A class that manipulates creates Weight objects with pound and ounce attributes
 * @author fineh
 */

/*	TABLE OF CONTENTS
 *
 * 	Declaring and Assigning Constants
 * 	Line 37
 *
 *  Constructor for Weight class
 *  Line 44
 *
 *  getters
 *  Line 51
 *
 *  toOunces()
 *  Line 61
 *
 *  normalize()
 *  Line 67
 *
 *  lessThan()
 *  Line 85
 *
 *  toString()
 *  Line 100
 *
 */
package Asg1;

public class Weight {

	// Declare and assign final variable
	private static final double OUNCES_IN_A_POUND = 16;

	// Declare dynamic variables
	private int pounds;
	private double ounces;

	// Constructor for Weight class
	public Weight(int pounds, double ounces) {
		this.pounds = pounds;
		this.ounces = ounces;
		normalize(); // Normalize if the user enters a number of ounces that is greater than 16
	}

	// getters (used to give minimumWeight and maximumWeight their own object
	// pointers)
	public int getPounds() {
		return pounds;
	}

	public double getOunces() {
		return ounces;
	}

	private double toOunces() {
		// multiplies pounds by OUNCES_IN_A_POUND and adds on the remainder of ounces,
		// returns that number
		return ((pounds * OUNCES_IN_A_POUND) + ounces);
	}

	private void normalize() {
		// Declare/assign method variables
		int pounds = 0;
		double totalOunces = toOunces(); // grand total of ounces in weight

		// while totalOunces is over 16, for each iteration subtract 16 from ounces and
		// add 1
		// to pounds
		while (totalOunces >= 16) {
			totalOunces -= 16;
			pounds += 1;
		}

		// Assign the class's dynamic variables after normalization
		ounces = totalOunces;
		this.pounds = pounds;
	}

	public boolean lessThan(Weight weight) {
		// Find and compare totalOunces of both weights
		double thisTotalOunces = this.toOunces();
		double thatTotalOunces = weight.toOunces();
		if (thisTotalOunces < thatTotalOunces) {
			return true;
		} else if (thisTotalOunces > thatTotalOunces) {
			// return is outside of control statement
		} else if (thisTotalOunces == thatTotalOunces) {
			System.err.println("Error: Two weights were compared in Weight.lessThan() that were the same.\n"
					+ "Returned false anyways!");
		}
		return false;
	}

	public void addTo(Weight weight) {
		// Find totalOunces of both weights
		double thisTotalOunces = this.toOunces();
		double thatTotalOunces = weight.toOunces();
		Weight sumOfWeights = new Weight(0, (thisTotalOunces + thatTotalOunces));
		System.out.printf("%dLbs %.2foz + %dLbs %.2foz = %dLbs %.2foz\n", pounds, ounces, weight.pounds, weight.ounces,
				sumOfWeights.pounds, sumOfWeights.ounces);

	}

	@Override
	public String toString() {
		return String.format("%d pounds and %.2f ounces", pounds, ounces);
	}
}
