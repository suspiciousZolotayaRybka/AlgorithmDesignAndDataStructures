package Asg2;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 1/29/2023
 *
 * saltySnack has the additional attribute "isSalty"
 *
 * @author fineh
 */
public class SaltySnack extends Snack {

	/*-*
	 * Attribute for a single SaltySnack object.
	 */
	private final boolean hasNuts;

	/**
	 * Constructor for FruitySnack objects.
	 *
	 * @param id      a combination of numbers and letters
	 * @param size    values S, M, or L
	 * @param hasNuts nut snack indication
	 */
	public SaltySnack(int size, boolean hasNuts) {
		super(size);
		// id is size, type of snack abbreviation, + two random int's
		this.setId(getSIZES()[size] + "slsn" + (getRAN_ID().nextInt(89) + 10));
		this.hasNuts = hasNuts;
		this.setPrice(calculatePrice());
	}

	/*-*
	 * A SaltySnack with a true hasNuts attribute is $4.50 more.
	 *
	 * @return the calculatedPrice to constructor
	 */
	private double calculatePrice() {
		if (hasNuts) {
			return (this.getPrice() + 4.50);
		} else {
			return this.getPrice();
		}
	}

	@Override
	public String toString() {
		return String.format("SaltySnack: [id=%s, size=%c, price=%.2f, hasNuts=%b]", this.getId(),
				getSIZES()[this.getSize()], this.getPrice(), hasNuts);
	}

}
