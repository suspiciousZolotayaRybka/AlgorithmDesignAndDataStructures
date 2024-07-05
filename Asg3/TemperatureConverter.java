package Asg3;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 2/5/2023
 *
 * A concrete class that extends Converter and converts fahrenheit to celsius
 *
 * @author fineh
 */
public class TemperatureConverter extends Converter {

	private static final double FAHRENHEIT_CONVERT_DIVIDE = 9;
	private static final double FAHRENHEIT_CONVERT_MULTIPLY = 5;
	private static final double FAHRENHEIT_CONVERT_SUBTRACT = 32;

	/**
	 * Constructors which call parent constructors
	 */
	public TemperatureConverter() {
		super();
	}

	public TemperatureConverter(double input) {
		super(input);
	}

	/**
	 * Overridden convert() method to convert input (Fahrenheit temperature) to
	 * Celsius and returns the value. If the instance has no input value, it should
	 * return Double.NaN
	 */
	@Override
	protected double convert() {

		if (Double.isNaN(this.getInput())) {
			return Double.NaN;
		}

		double fahrenheit = this.getInput();
		double celsius = ((fahrenheit - FAHRENHEIT_CONVERT_SUBTRACT) * FAHRENHEIT_CONVERT_MULTIPLY)
				/ FAHRENHEIT_CONVERT_DIVIDE;

		return celsius;
	}

}
