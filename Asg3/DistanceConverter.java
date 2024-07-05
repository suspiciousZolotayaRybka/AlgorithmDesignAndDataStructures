package Asg3;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 2/5/2023
 *
 * A concrete class that extends Converter and converts miles to kilometers
 *
 * @author fineh
 */
public class DistanceConverter extends Converter {

	final private static double MILES_CONVERSION = 1.609;

	/**
	 * Constructors which call parent constructors
	 */
	public DistanceConverter() {
		super();
	}

	public DistanceConverter(double input) {
		super(input);
	}

	/**
	 * Overridden convert() method to conert input (distance in miles) to distance
	 * in kilometers and return the value. If the instance has no input value, it
	 * should return Double.NaN
	 */
	@Override
	protected double convert() {

		if (Double.isNaN(this.getInput())) {
			return Double.NaN;
		}

		double miles = this.getInput();
		double kilometers = miles * MILES_CONVERSION;

		return kilometers;

	}

}
