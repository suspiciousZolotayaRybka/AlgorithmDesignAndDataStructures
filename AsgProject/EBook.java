/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 3/4/2023
 *
 * A duplicate copy of EBook from Week 3 -> Practice Exercise - Inheritance solution
 * Contains attribute store indication from media class and any additional
 * constructors and methods needed to support Project functionality
 */
package AsgProject;

import java.util.Calendar;

public class EBook extends Media {

	// local attributes
	private int numChapters;

	// constructor
	protected EBook(int id, String title, int year, boolean isRented, int numChapters) {
		super(id, title, year, isRented);
		this.numChapters = numChapters;
	}

	// constructor to parse string with xml tags for its values
	protected EBook(String line) {
		super(line);
		numChapters = Integer
				.parseInt(line.substring(line.indexOf("<numChapters>") + 13, line.indexOf("</numChapters>")));
	}

	// get method
	protected int getNumChapters() {
		return numChapters;
	}

	// set method
	protected void setNumChapters(int numChapters) {
		this.numChapters = numChapters;
	}

	// override parent's
	@Override
	protected double calculateRentalFee() {
		double fee = numChapters * 0.10; // basic fee
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		if (this.getYear() == currYear) {
			fee += 1; // add $1.00 fee
		}
		return fee;
	}

	// Displays when user looks up a media with a title
	@Override
	protected String display() {
		return String.format("EBook [id=%d, title=%s, year=%d, chapters=%d available=%b]", super.getId(),
				super.getTitle(), super.getYear(), numChapters, !super.getIsRented());
	}

	// toString method used when user saves or loads to and from a file
	@Override
	public String toString() {
		return "<EBook>"
				+ "<id>"
				+ super.getId()
				+ "</id>"
				+ "<title>"
				+ super.getTitle()
				+ "</title>"
				+ "<year>"
				+ super.getYear()
				+ "</year>"
				+ "<isRented>"
				+ super.getIsRented()
				+ "</isRented>"
				+ "<numChapters>"
				+ numChapters
				+ "</numChapters>"
				+ "</EBook>";
	}
}
