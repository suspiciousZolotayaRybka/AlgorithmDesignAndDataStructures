/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 3/4/2023
 *
 * A duplicate copy of MovieDVD from Week 3 -> Practice Exercise - Inheritance solution
 * Contains attribute store indication from media class and any additional
 * constructors and methods needed to support Project functionality
 */
package AsgProject;

public class MovieDVD extends Media {

	// local attributes
	private double size; // value in MB

	// constructor
	protected MovieDVD(int id, String title, int year, boolean isRented, double size) {
		super(id, title, year, isRented);
		this.size = size;
	}

	// constructor to parse string with xml tags for its values
	protected MovieDVD(String line) {
		super(line);
		size = Double.parseDouble(line.substring(line.indexOf("<size>") + 6, line.indexOf("</size>")));
	}

	// get method
	protected double getSize() {
		return size;
	}

	// set method
	protected void setSize(double size) {
		this.size = size;
	}

	// Displays when user looks up a media with a title
	@Override
	protected String display() {
		return String.format("MovieDVD [id=%d, title=%s, year=%d, size=%.1fMB available=%b]", super.getId(),
				super.getTitle(), super.getYear(), size, !super.getIsRented());
	}

	// toString method used when user saves or loads to and from a file
	@Override
	public String toString() {
		return "<MovieDVD>"
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
				+ "<size>"
				+ size
				+ "</size>"
				+ "</MovieDVD>";
	}
}
