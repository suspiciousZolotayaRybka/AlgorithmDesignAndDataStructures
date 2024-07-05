package Asg3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 2/5/2023
 *
 * A class that uses JFrame and JPanels and implements three buttons that
 * provide functionality to convert distance, convert temperature, and exit the
 * program
 *
 * @author fineh
 *
 * @param windowGUIConverter           Window for GUIConverter
 * @param contentGUIConverter          Content for GUIConverter
 * @param buttonsGUIConverter          An Array of Buttons for the GUIConverter
 * @param DISTANCE_CONVERTER_BUTTON    Represent the distance converter button
 *                                     in buttonsGUIConverter
 * @param TEMPERATURE_CONVERTER_BUTTON Represent the temperature converter
 *                                     button in buttonsGUIConverter
 * @param EXIT_BUTTON                  Represent the exit button in
 *                                     buttonsGUIConverter
 * @param converter                    Used to demonstrate polymorphism and
 *                                     dynamic binding / loading in GUIConverter
 */
public class GUIConverter {

	private static JFrame windowGUIConverter;
	private static JPanel contentGUIConverter;
	private static final JButton[] buttonsGUIConverter = new JButton[3];
	private static final int DISTANCE_CONVERTER_BUTTON = 0;
	private static final int TEMPERATURE_CONVERTER_BUTTON = 1;
	private static final int EXIT_BUTTON = 2;
	private static Converter converter;

	/*-*
	 * An action listener for the distance converter button Creates a
	 * DistanceConverter instance of converter
	 * Displays the miles to kilometers conversion
	 */
	private static class DistanceConverterButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*-*
			 * getDouble finds a double for temperature and distance converters
			 * It checks for if the user exits the window w/out entering anything
			 */
			double miles = getDouble("Input miles distance to convert");

			// Do not display a number if user chooses to exit the window
			if (!(Double.isNaN(miles))) {
				converter = new DistanceConverter(miles);

				double kilometers = converter.convert();

				// displayConversion shows a JOptionPane message dialog for the converter family
				displayConversion(String.format("%.2f Miles equals %.4f Kilometers", miles, kilometers));
			}
		}
	}

	/*-*
	 * An action listener for the temperature converter button
	 * TemperatureConverter instance of converter
	 * Displays the fahrenheit to celsius conversion
	 */
	private static class TemperatureConverterButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*-*
			 * getDouble finds a double for temperature and distance converters
			 * It checks for if the user exits the window w/out entering anything
			 */
			double fahrenheit = getDouble("Input fahrenheit temperature to convert");

			// Do not display a number if user chooses to exit the window
			if (!(Double.isNaN(fahrenheit))) {
				converter = new TemperatureConverter(fahrenheit);

				double celsius = converter.convert();

				// displayConversion shows a JOptionPane message dialog for the converter family
				displayConversion(String.format("%.1fF equals %.1fC", fahrenheit, celsius));
			}

		}
	}

	private static class ExitButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(windowGUIConverter, "Thank you for using the GUIConverter!");
			System.exit(0);
		}

	}

	/**
	 * The GUIConverter constructor that sets up contentGUIDriver and
	 * windowGUIDriver
	 */
	private GUIConverter() {

		// Create JButtons
		buttonsGUIConverter[DISTANCE_CONVERTER_BUTTON] = new JButton("Distance Converter");
		buttonsGUIConverter[TEMPERATURE_CONVERTER_BUTTON] = new JButton("Temperature Converter");
		buttonsGUIConverter[EXIT_BUTTON] = new JButton("Exit");

		// Create listeners for JButtons
		DistanceConverterButtonHandler distanceConverterButtonListener = new DistanceConverterButtonHandler();
		TemperatureConverterButtonHandler temperatureConverterButtonListener = new TemperatureConverterButtonHandler();
		ExitButtonHandler exitButtonListener = new ExitButtonHandler();

		// Add listeners to JButtons
		buttonsGUIConverter[DISTANCE_CONVERTER_BUTTON].addActionListener(distanceConverterButtonListener);
		buttonsGUIConverter[TEMPERATURE_CONVERTER_BUTTON].addActionListener(temperatureConverterButtonListener);
		buttonsGUIConverter[EXIT_BUTTON].addActionListener(exitButtonListener);

		// Add JButtons to contentGUIConverter
		contentGUIConverter = new JPanel(new BorderLayout());
		contentGUIConverter.add(buttonsGUIConverter[DISTANCE_CONVERTER_BUTTON], BorderLayout.CENTER);
		contentGUIConverter.add(buttonsGUIConverter[TEMPERATURE_CONVERTER_BUTTON], BorderLayout.EAST);
		contentGUIConverter.add(buttonsGUIConverter[EXIT_BUTTON], BorderLayout.SOUTH);

		// Create the new window
		windowGUIConverter = new JFrame("GUI Converter");
		// Add the content to the window
		windowGUIConverter.setContentPane(contentGUIConverter);
		// Set window size to contain all the buttons
		windowGUIConverter.pack();
		// Set window location to the center of the screen
		windowGUIConverter.setLocationRelativeTo(null);
		// Make the window visible
		windowGUIConverter.setVisible(true);

	}

	// Run the program
	public static void main(String[] args) {
		new GUIConverter();
	}

	/**
	 * A method to ensure the user enters a double in GUIConverter
	 *
	 * @param String the inputType the user chooses to enter into the program
	 * @return double The valid user input for GUIConverter
	 */
	private static double getDouble(String inputType) {

		String userInput = null;
		double numUserInput = Double.NaN;
		boolean errorChecking = true;

		// Ask user for their number while numUserInput is not a number.
		do {

			// Get user's input
			userInput = JOptionPane.showInputDialog(windowGUIConverter, inputType, "Input",
					JOptionPane.QUESTION_MESSAGE);

			// Check to see if the user simply closed the window
			if (userInput == null) {
				JOptionPane.showMessageDialog(windowGUIConverter, "You chose not to enter a number.",
						"No Double Entered", JOptionPane.INFORMATION_MESSAGE);
				errorChecking = false;

				/*-*
				 * The control statement below this uses a regular expression
				 * I made with the help of ChatGPT to check if a String is a valid double
				 *
				 * Expression: \\d+(\\.\\d+)?
				 *
				 * My Understanding:
				 *
				 * \\d+
				 * Checks for 1 or more instances of a valid integer
				 * \\.
				 * Checks for one instance of a period
				 * \\d+ Checks for 1 or more instances of a valid integer
				 * (\\.\\d+)?
				 * Checks for 0 or 1 instances of a valid period + integer(s) combination
				 */
			} else if (!(userInput.matches("-?\\d+(\\.\\d+)?"))) {
				// User did not enter a valid number. Tell them to enter a valid number.
				JOptionPane.showMessageDialog(windowGUIConverter,
						String.format("%s is not a valid positive double.\nEnter a positive double.", userInput),
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				// User entered a valid number. ErrorChecking is false.
				numUserInput = Double.parseDouble(userInput);
				errorChecking = false;
			}

		} while (errorChecking);

		// Return the user's valid number
		return numUserInput;

	}

	/**
	 * A simple JOption pane that shows the user's conversion
	 */
	private static void displayConversion(String conversionMessage) {

		JOptionPane.showMessageDialog(windowGUIConverter, conversionMessage);

	}
}
