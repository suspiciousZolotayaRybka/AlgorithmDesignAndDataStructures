/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 3/4/2023
 *
 * A duplicate copy of Media from Week 3 -> Practice Exercise - Inheritance solution
 * Contains attribute store indication and any additional
 * constructors and methods needed to support Project functionality
 */
package AsgProject;

/*
* abstract class to represent a generic media
*/
public abstract class Media {

	// attributes
	private final int id;
	private String title;
	private int year; // validate that 4 digits
	private boolean isRented;

	// constructor
	protected Media(int id, String title, int year, boolean isRented) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.isRented = isRented;
	}

	// constructor to parse string with xml tags for its values
	protected Media(String line) {
		id = Integer.parseInt(line.substring(line.indexOf("<id>") + 4, line.indexOf("</id>")));
		title = line.substring(line.indexOf("<title>") + 7, line.indexOf("</title>"));
		year = Integer.parseInt(line.substring(line.indexOf("<year>") + 6, line.indexOf("</year>")));
		isRented = Boolean.parseBoolean(line.substring(line.indexOf("<isRented>") + 10, line.indexOf("</isRented>")));
	}

	// get methods
	protected int getId() {
		return id;
	}

	protected String getTitle() {
		return title;
	}

	protected int getYear() {
		return year;
	}

	protected boolean getIsRented() {
		return isRented;
	}

	// set methods
	protected void setTitle(String title) {
		this.title = title;
	}

	protected void setYear(int year) {
		this.year = year;
	}

	protected void setIsRented(boolean isRented) {
		this.isRented = isRented;
	}

	protected String display() {
		return "";
	}

	// calculate rental fee; for generic media it is flat fee $3.50
	protected double calculateRentalFee() {
		return 3.50;
	}
}