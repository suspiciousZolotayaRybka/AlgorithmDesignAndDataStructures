/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 3/4/2023
 *
 * Manager class mimics the Manager class from the Week 8 Reading and Writing files example.
 *
 * Manager class stores a list of media objects,
 * has functionality to load Media objects from files,
 * creates/updates Media files,
 * has functionality to add new Media object to its Media list,
 * has functionality to find all media objects for a specific title and returns that list,
 * and has functionality to rent Media based on id (updates rental status on media,
 * updates file, returns rental fee)
 */
package AsgProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

	// attribute
	private static List<Media> mediaList;

	// default constructor
	protected Manager() {

		// initialize empty media list
		mediaList = new ArrayList<>();
	}

	/**
	 * load all media files from directory; assume file name convention starts with
	 * media type EBook, MovieDVD, or MusicCD followed by id
	 */
	protected Manager(File directoryPath) throws FileNotFoundException {

		// initialize empty media list
		mediaList = new ArrayList<>();

		// Get list of all files and directories
		File[] filesList = directoryPath.listFiles();

		if (filesList == null) {
			throw new FileNotFoundException("Could not load, no such directory");
		}

		// declare local variables
		Media media = null;
		String line = null;
		Scanner scan = null;

		// Process each media file
		for (File file : filesList) {

			// parse files whose fileName starts with "EBook", "MovieDVD", or "MusicCD"
			if (file.getName().contains("EBook") || file.getName().contains("MovieDVD")
					|| file.getName().contains("MusicCD")) {

				// open and read line (assume whole object is stored on single line)
				scan = new Scanner(file);
				line = scan.nextLine(); // assumes the file is not empty

				// if EBook object then call EBook constructor
				if (file.getName().contains("EBook")) {
					media = new EBook(line);
				}

				// if MovieDVD object then call MovieDVD constructor
				if (file.getName().contains("MovieDVD")) {
					media = new MovieDVD(line);
				}

				// if MusicCD object then call MusicCD constructor
				if (file.getName().contains("MusicCD")) {
					media = new MusicCD(line);
				}

				// add media object to mediaList attribute
				mediaList.add(media);
			}
		}
	}

	// creates (or overwrites) a file for each media object in mediaList attribute
	// in the given directory
	protected void createMediaFiles(File directory) throws IOException {

		PrintWriter out = null;

		// for all media objects create files using media type and is value as fileName
		for (int i = 0; i < mediaList.size(); i++) {
			String fileName = directory
					+ "/"
					+ mediaList.get(i).getClass().getSimpleName()
					+ "-"
					+ mediaList.get(i).getId()
					+ ".txt";
			out = new PrintWriter(new File(fileName)); // create/overwrite file
			out.println(mediaList.get(i).toString()); // write the media's data
			out.flush();
			out.close();
		}
	}

	// add media object
	protected void addMedia(Media media) {
		mediaList.add(media);
	}

	// find media with a specific title
	protected ArrayList<Media> findTitle(String title) {
		// declare array of media to be returned
		ArrayList<Media> specificTitledMedia = new ArrayList<>();

		// use a enhanced for loop to add media to the list if the name matches
		for (Media media : mediaList) {
			if (media.getTitle().equals(title)) {
				specificTitledMedia.add(media);
			}
		}

		return specificTitledMedia;
	}

	// rent media based on id
	protected double changeRentStatusMedia(File directory, int id) throws IOException {

		// declare attributes
		String fileName = null;
		PrintWriter out = null;

		// Use a for loop to find the media that matches
		for (int i = 0; i < mediaList.size(); i++) {

			if (mediaList.get(i).getId() == id) {
				// Check to see if media is rented
				// Declare PrintWriter for selected media
				fileName = directory
						+ "/"
						+ mediaList.get(i).getClass().getSimpleName()
						+ "-"
						+ mediaList.get(i).getId()
						+ ".txt";
				out = new PrintWriter(new File(fileName));

				// Change the status to the opposite of current status
				if (mediaList.get(i).getIsRented() == false) {
					mediaList.get(i).setIsRented(true);
				} else {
					mediaList.get(i).setIsRented(false);
				}

				// Update the file
				out.println(mediaList.get(i).toString());
				out.flush();
				out.close();

				// Return the rental fee
				return mediaList.get(i).calculateRentalFee();
			}
		}
		// return Double.NaN if id does not exist
		return Double.NaN;
	}

}
