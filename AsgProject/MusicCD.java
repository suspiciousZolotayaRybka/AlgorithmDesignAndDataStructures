/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 3/4/2023
 *
 * A duplicate copy of MusicCD from Week 3 -> Practice Exercise - Inheritance solution
 * Contains attribute store indication from media class and any additional
 * constructors and methods needed to support Project functionality
 */
package AsgProject;

import java.util.Calendar;

public class MusicCD extends Media {

	// local attributes
	private int length;

	// constructor
	protected MusicCD(int id, String title, int year, boolean isRented, int length) {
		super(id, title, year, isRented);
		this.length = length;
	}

	// constructor to parse string with xml tags for its values
	protected MusicCD(String line) {
		super(line);
		length = Integer.parseInt(line.substring(line.indexOf("<length>") + 8, line.indexOf("</length>")));
	}

	// get method
	protected int getLength() {
		return length;
	}

	// set method
	protected void setLength(int length) {
		this.length = length;
	}

	// override parent's
	@Override
	protected double calculateRentalFee() {
		double fee = length * 0.02; // basic fee
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		if (this.getYear() == currYear) {
			fee += 1; // add $1.00 fee
		}
		return fee;
	}

	// Displays when user looks up a media with a title
	@Override
	protected String display() {
		return String.format("MusicCD [id=%d, title=%s, year=%d, length=%dminutes available=%b]", super.getId(),
				super.getTitle(), super.getYear(), length, !super.getIsRented());
	}

	// toString method used when user saves or loads to and from a file
	@Override
	public String toString() {
		return "<MusicCD>"
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
				+ "<length>"
				+ length
				+ "</length>"
				+ "</MusicCD>";
	}
}
