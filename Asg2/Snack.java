package Asg2;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 1/29/2023
 *
 * An abstract superclass for FruitySnack and SaltySnack.
 *
 * @author fineh
 */
import java.util.Random;

public abstract class Snack {

	/**
	 * Declare private static final variables.
	 *
	 * @param SIZES       char index to handle snack sizes
	 * @param SMALL_SIZE  represents small in SIZES
	 * @param MEDIUM_SIZE represents medium in SIZES
	 * @param LARGE_SIZE  represents large in SIZES
	 * @param RAND_ID     used to find random id's for snacks
	 */
	private static final char[] SIZES = { 'S', 'M', 'L' };
	private static final int SMALL_SIZE = 0;
	private static final int MEDIUM_SIZE = 1;
	private static final int LARGE_SIZE = 2;
	private static final Random RAN_ID = new Random();

	/*-*
	 * Attributes for a single Snack object.
	 * FruitySnack and SaltySnack objects have these attributes too.
	 */
	private String id;
	private final int size;
	private double price;

	/**
	 * Constructor for Snack objects.
	 *
	 * @param id   a combination of numbers and letters
	 * @param size values S, M, or L
	 */
	public Snack(int size) {
		// id is size, type of snack abbreviation, + two random int's
		id = (SIZES[size]) + "sn" + (RAN_ID.nextInt(89) + 10);
		this.size = size;
		price = calculatePrice();
	}

	/**
	 *
	 * @return the SIZES
	 */
	public static char[] getSIZES() {
		return SIZES;
	}

	/**
	 *
	 * @return the SMALL_SIZE
	 */
	public static int getSMALL_SIZE() {
		return SMALL_SIZE;
	}

	/**
	 *
	 * @return the MEDIUM_SIZE
	 */
	public static int getMEDIUM_SIZE() {
		return MEDIUM_SIZE;
	}

	/**
	 *
	 * @return the LARGE_SIZE
	 */
	public static int getLARGE_SIZE() {
		return LARGE_SIZE;
	}

	/**
	 *
	 * @return the RAN_ID
	 */
	public static Random getRAN_ID() {
		return RAN_ID;
	}

	/**
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 *
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/*-*
	 * A switch statement sets the flat fee of a snack.
	 * This is based on Small, Medium, or Large snack size.
	 *
	 * @return the calculatedPrice to constructor
	 */
	private double calculatePrice() {
		switch (size) {
		case (SMALL_SIZE):
			return 19.99;
		case (MEDIUM_SIZE):
			return 29.99;
		case (LARGE_SIZE):
			return 39.99;
		default:
			System.err.println("ERROR - Could not calculate price");
			return 0;
		}
	}

}
