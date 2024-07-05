/**
 *
 * Name: Finehout, Isaac CMIS 242/6384 Date: 3/4/2023
 *
 * MediaRentalSystem class mimics Class from Week 8 Files in GUI
 *
 * MediaRentalSystem has the following functionality:
 * selection to load Media files from a given directory (supplied by user)
 * selection to find a media object for a specific title value
 * (user supplies title and should display to user the media information
 * once it finds it - should find all media with that title)
 * selection to exit program
 */
package AsgProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MediaRentalSystem extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// Main method
	public static void main(String[] args) {
		JFrame window = new MediaRentalSystem();
		window.setVisible(true);
	}

	// Declare attributes
	private JFileChooser fileDialog;
	private File mediaFile;
	private Manager mediaManager;

	// Constructor for MediaRentalSystem
	protected MediaRentalSystem() {
		super("MediaRentalSystem");
		mediaManager = new Manager();
		setJMenuBar(makeMenus());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
	}

	// Method to create the menu bars for MediaRentalSystem
	private JMenuBar makeMenus() {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String cmd = evt.getActionCommand();
				if (cmd.equals("Load Media objects...")) {
					doLoadMediaObjects();
				} else if (cmd.equals("Find Media object...")) {
					doFindMediaObject();
				} else if (cmd.equals("Rent Media object...")) {
					doRentMediaObject();
				} else if (cmd.equals("Quit")) {
					doQuit();
				}
			}
		};

		// Declare the JMenu for the JMenuItem subdivisions
		JMenu mediaMenu = new JMenu("Menu");

		JMenuItem loadCmd = new JMenuItem("Load Media objects...");
		loadCmd.addActionListener(listener);
		mediaMenu.add(loadCmd);

		JMenuItem findCmd = new JMenuItem("Find Media object...");
		findCmd.addActionListener(listener);
		mediaMenu.add(findCmd);

		JMenuItem rentCmd = new JMenuItem("Rent Media object...");
		rentCmd.addActionListener(listener);
		mediaMenu.add(rentCmd);

		mediaMenu.addSeparator();

		JMenuItem quitCmd = new JMenuItem("Quit");
		quitCmd.addActionListener(listener);
		mediaMenu.add(quitCmd);

		// Add media menu to the JMenuBar and return to the constructor to set the
		// JFrame's menu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mediaMenu);
		return bar;
	} // end makeMenus()

	/**
	 * Load the media objects from the folder. Let's the user choose to load media
	 * objects from the database, if no file names with MovieDVD, MusicCD, or EBook
	 * exist in said location
	 */
	private void doLoadMediaObjects() {
		if (fileDialog == null) {
			fileDialog = new JFileChooser();
		}
		fileDialog.setDialogTitle("Open");
		fileDialog.setSelectedFile(null);
		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = fileDialog.showOpenDialog(this);
		if (option != JFileChooser.APPROVE_OPTION) {
			return;
		}
		mediaFile = fileDialog.getSelectedFile();

		// Test to see if media files exist in the location
		boolean hasMediaObjectsInMediaFile = testForMediaObjects();
		int uploadChoice = JOptionPane.NO_OPTION;
		if (!hasMediaObjectsInMediaFile) {
			uploadChoice = JOptionPane.showConfirmDialog(this,
					"The file location you selected does not contain media objects.\nUpload the database's current media objects to the chosen file location?");
		} else {
			// File already has media objects. Download them to the mediaManager object
			try {
				mediaManager = new Manager(mediaFile);
			} catch (Exception e) {
				// Exception caught for FileNotFound or constructor IndexOutOfBoundsException if
				// a file named with a media object does not actually contain a media object
				JOptionPane.showMessageDialog(this,
						"Sorry, but an error occurred while trying to open the file:\n" + e);
			}
		}
		// File does not have media objects and user chose to upload the database's
		if (uploadChoice == JOptionPane.YES_OPTION) {
			addAllCurrentMedia();
			try {
				mediaManager.createMediaFiles(mediaFile);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this,
						"Sorry, but an error occurred while trying to open the file:\n" + e);
			}
		}

	} // end doLoadMediaObjects()

	/**
	 * Uses the findTitle method from manager to find specific titles for the user.
	 * If the user exits the dialog box or chooses an invalid title an error dialog
	 * box will be shown. Finds all media with the title.
	 */
	private void doFindMediaObject() {
		String title = null;
		title = JOptionPane.showInputDialog(this, "Enter the title", "Input", JOptionPane.QUESTION_MESSAGE);

		if (title == null) {
			// User chose to just exit the dialog box
			JOptionPane.showMessageDialog(this, "Invalid title vale");
		} else {
			// Use create media files method from Manager to get a list of media for the
			// chosen title
			ArrayList<Media> specificTitledMedia = mediaManager.findTitle(title);
			if (specificTitledMedia.size() == 0) {
				JOptionPane.showMessageDialog(this, "There is no media with this title: " + title);
			} else {
				String displayText = "";
				for (int i = 0; i < specificTitledMedia.size(); i++) {
					if (i > 0) {
						displayText += "\n";
					}
					displayText += specificTitledMedia.get(i).display();
				}
				JOptionPane.showMessageDialog(this, displayText);
			}
		}
	} // end doFindMediaObjects()

	/**
	 * rent a media object based on its id value user supplies id and should display
	 * rental fee value to the user
	 */
	private void doRentMediaObject() {
		String rentChoice = null;
		double rentalFee;
		rentChoice = JOptionPane.showInputDialog(this, "Enter the id", "Input", JOptionPane.QUESTION_MESSAGE);

		// A try block that ensures the rent choice is not null and it is a valid int
		// that matches an existing media object
		try {
			if (rentChoice == null) {
				throw new IllegalArgumentException("Invalid id value");
			}
			int rentChoiceNumber = Integer.parseInt(rentChoice); // Throws number format exception
			rentalFee = mediaManager.changeRentStatusMedia(mediaFile, rentChoiceNumber);
			if (Double.isNaN(rentalFee)) {
				throw new IllegalArgumentException("The media object " + rentChoice + " is not found");
			}
			JOptionPane.showMessageDialog(this,
					String.format("Media was successfully rented. Rental fee = $%.2f", rentalFee));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Sorry, but an error occurred while trying to process your choice:\n" + e);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Sorry, your input was not an integer:\n" + e);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void doQuit() {
		System.exit(0);
	} // end doRentMediaObjects()

	/**
	 *
	 * @return hasMediaObjectInMediaFile true or false based on if a media file
	 *         exists in the user's chosen location
	 */
	private boolean testForMediaObjects() {
		File[] fileList = mediaFile.listFiles();
		// Return false if no files exist
		if (fileList == null) {
			return false;
		}
		// Return true if a correct file exists
		for (File file : fileList) {
			if (file.getName().contains("EBook") || file.getName().contains("MusicCD")
					|| file.getName().contains("MovieDVD")) {
				return true;
			}
		}
		// Return false if a files exist but no file has the correct object
		return false;
	}

	private void addAllCurrentMedia() {
		mediaManager.addMedia(new EBook(123, "Forever Young", 2018, false, 20));
		mediaManager.addMedia(new MovieDVD(126, "Forever Young", 2020, false, 140));
		mediaManager.addMedia(new MusicCD(124, "Beyond Today", 2020, false, 114));
		mediaManager.addMedia(new MovieDVD(125, "After Tomorrow", 2020, false, 120));
	}

}
