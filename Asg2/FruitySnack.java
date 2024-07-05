package Asg2;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 1/29/2023
 *
 * Fruity snack has the additional attribute "isCitrusy"
 *
 * @author fineh
 */
public class FruitySnack extends Snack {

	/*-*
	 * Attribute for a single FruitySnack object.
	 */
	private final boolean isCitrusy;

	/**
	 * Constructor for FruitySnack objects.
	 *
	 * @param id        a combination of numbers and letters
	 * @param size      values S, M, or L
	 * @param isCitrusy citrus fruit indication
	 */
	public FruitySnack(int size, boolean isCitrusy) {
		super(size);
		// id is size, type of snack abbreviation, + two random int's
		this.setId(getSIZES()[size] + "frsn" + (getRAN_ID().nextInt(89) + 10));
		this.isCitrusy = isCitrusy;
		this.setPrice(calculatePrice());
	}

	/*-*
	 * A FruitySnack with a true isCitrusy attribute is $5.99 more.
	 *
	 * @return the calculatedPrice to constructor
	 */
	private double calculatePrice() {
		if (isCitrusy) {
			return (this.getPrice() + 5.99);
		} else {
			return this.getPrice();
		}
	}

	@Override
	public String toString() {
		return String.format("FruitySnack: [id=%s, size=%c, price=%.2f, isCitrusy=%b]", this.getId(),
				getSIZES()[this.getSize()], this.getPrice(), isCitrusy);
	}

}
