/** Name: Finehout, Isaac
 * CMIS 242/6384
 * Date: 1/16/2023
 * A class that manipulates different Weight objects using findMinimum, findMaximum,
 * and findAverage methods with return types of weight
 * @author fineh
 */

/*	TABLE OF CONTENTS
 *
 * 	Main Method
 * 	Line 29
 *
 *  findMinimum(Weight[] weights)
 *  Line 74
 *
 *  findMaximum(Weight[] weights)
 *  Line 122
 *
 *  findAverage(Weight[] weights)
 *  Line 170
 */
package Asg1;

import java.util.Random;

public class Project {

	public static void main(String[] args) {

		// Print out error messages for trying to compare all of the same weights
		Weight weight1 = new Weight(11, 0);
		Weight weight2 = new Weight(11, 0);
		Weight weight3 = new Weight(11, 0);
		Weight[] errorWeights = { weight1, weight2, weight3 };

		findMinimum(errorWeights);
		findMaximum(errorWeights);
		findAverage(errorWeights);
		System.out.println("\n".repeat(5));

		// The test cases provided in the instructions
		weight1 = new Weight(11, 3);
		weight2 = new Weight(7, 20);
		weight3 = new Weight(14, 6);
		Weight[] weights = { weight1, weight2, weight3 };

		findMinimum(weights);
		findMaximum(weights);
		findAverage(weights);
		System.out.println("\n".repeat(5));

		// Find random weights, test that the methods can use any length of array
		Random ranNum = new Random();
		int pounds;
		double ounces;
		int numWeights = ranNum.nextInt(8) + 2; // Have not implemented code for arrays of length 0 or 1 yet, so add two
		Weight[] ranWeights = new Weight[numWeights];
		System.out.printf("Random Weights (array length %d)\n", numWeights);
		for (int i = 0; i < ranWeights.length; i++) {
			pounds = ranNum.nextInt(100);
			ounces = ranNum.nextDouble(16);
			ranWeights[i] = new Weight(pounds, ounces);
			System.out.println(ranWeights[i]);
		}

		System.out.println();
		findMinimum(ranWeights);
		findMaximum(ranWeights);
		findAverage(ranWeights);

		System.out.println("\n".repeat(5));

		// testing the addTo method. This should show the triple for every weight in
		// weights
		for (Weight weight : weights) {
			weight.addTo(new Weight(0, (weight.getOunces() + (weight.getPounds() * 16)) * 2));
		}

	}

	private static Weight findMinimum(Weight[] weights) {
		/*-*
		 Note: the comment below demonstrates a findMinimum weight method that would
		 take EXACTLY three Weight objects as parameters. the current implementation
		 can take an array of length three, in addition to any other array length

		 private static Weight findMinimum(Weight weight1, Weight weight2, Weight weight3) {
			Weight[] weights = { weight1, weight2, weight3 };

		*/

		// Declare variables
		boolean weightsAreEqual = true;
		Weight minimumWeight = weights[weights.length - 1];

		// Loop that determines minimumWeight, works on any number of weights
		for (int i = 0; i < (weights.length); i++) {
			if (i == (weights.length - 1)) {
				// skip last weight to prevent index out of bounds error
				// if last weight is minimum weight, it was already placed in variable
				// declaration
			} else {
				// otherwise the loop will compare this index to future index
				if (weights[i].lessThan(minimumWeight)) {
					minimumWeight = weights[i];
					weightsAreEqual = false;
				}
			}
		}

		// check to see if the last weight is the minimumWeight
		if (weights[weights.length - 1].lessThan(weights[0])) {
			weightsAreEqual = false;
		}

		// Remove the object pointer from minimumWeight, so that if weights[i] changes,
		// minimumWeight has its own object pointer
		Weight tempWeight = minimumWeight;
		minimumWeight = new Weight(tempWeight.getPounds(), tempWeight.getOunces());

		if (weightsAreEqual) {
			System.err.println("Error: Project.findMinimum() method calculated all equal weights");
		} else {
			System.out.printf("The minimum weight is %s\n", minimumWeight);
		}
		return minimumWeight;
	}

	private static Weight findMaximum(Weight[] weights) {
		/*-*
		 Note: the comment below demonstrates a findMaximum weight method that would
		 take EXACTLY three Weight objects as parameters. the current implementation
		 can take an array of length three, in addition to any other array length

		 private static Weight findMaximum(Weight weight1, Weight weight2, Weight weight3) {
			Weight[] weights = { weight1, weight2, weight3 };

		*/

		// Declare variables
		boolean weightsAreEqual = true;
		Weight maximumWeight = weights[weights.length - 1];

		// Loop that determines maximumWeight, works on any number of weights
		for (int i = 0; i < (weights.length); i++) {
			if (i == (weights.length - 1)) {
				// skip last weight to prevent index out of bounds error
				// if last weight is maximum weight, it was already placed in variable
				// declaration
			} else {
				// otherwise the loop will compare this index to future index
				if (maximumWeight.lessThan(weights[i])) {
					maximumWeight = weights[i];
					weightsAreEqual = false;
				}
			}
		}

		// check to see if the last weight is the maximumWeight
		if (weights[0].lessThan(weights[weights.length - 1])) {
			weightsAreEqual = false;
		}

		// Remove the object pointer from maximumWeight, so that if weights[i] changes,
		// maximumWeight has its own object pointer
		Weight tempWeight = maximumWeight;
		maximumWeight = new Weight(tempWeight.getPounds(), tempWeight.getOunces());

		if (weightsAreEqual) {
			System.err.println("Error: Project.findMaximum() method calculated all equal weights");
		} else {
			System.out.printf("The maximum weight is %s\n", maximumWeight);
		}
		return maximumWeight;
	}

	private static Weight findAverage(Weight[] weights) {
		/*-*
		 Note: the comment below demonstrates a findAverage weight method that would
		 take EXACTLY three Weight objects as parameters. the current implementation
		 can take an array of length three, in addition to any other array length

		 private static Weight findAverage(Weight weight1, Weight weight2, Weight weight3) {
			Weight[] weights = { weight1, weight2, weight3 };

		*/

		// declare variables
		Weight averageWeight;
		double totalOunces = 0;
		double numWeights = 0;
		double averageOunces;

		// a for loop that can find the average of any number of Weight array or list
		for (int i = 0; i < weights.length; i++) {
			totalOunces += ((weights[i].getPounds() * 16) + weights[i].getOunces());
			numWeights++;

		}
		averageOunces = totalOunces / numWeights;
		averageWeight = new Weight(0, averageOunces);
		System.out.printf("The average weight is %s", averageWeight);
		return averageWeight;
	}

}
