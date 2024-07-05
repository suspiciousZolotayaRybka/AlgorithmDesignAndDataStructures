package Asg3;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 2/5/2023
 *
 * An abstract class extended by TemperatureConverter and DistanceConverter
 *
 * @author fineh
 */
public abstract class Converter {

	/**
	 * Private attribute for input of data type double
	 */
	private double input;

	/**
	 * Default constructor with no parameter which sets input to Double.NaN
	 */
	public Converter() {
		input = Double.NaN;
	}

	/**
	 * Overloaded constructor with input for parameter
	 *
	 * @param input the input to construct
	 */
	public Converter(double input) {
		this.input = input;
	}

	/**
	 *
	 * @return double this input
	 */
	public double getInput() {
		return input;
	}

	/**
	 *
	 * @param input the input to set
	 */
	public void setInput(double input) {
		this.input = input;
	}

	/**
	 * Method convert() which returns input value
	 *
	 * @return double this input
	 */
	protected double convert() {
		return input;
	}

}
