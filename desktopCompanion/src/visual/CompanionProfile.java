package visual;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class that is used in order to create visuals for the desktop companion
 * Mainly just methods that are going to be used somewhere else in order
 * to have an easy time of changing the portrait
 * 
 * @author Colin Fawcett
 */
public class CompanionProfile {
	
	//Only need one storage of the portaits
	private static JLabel[] remPortraits;
	
	//Current frame to work in
	private JFrame frame;
	
	//The current portrait
	private JLabel currentPortrait;
	
	//Loads in the needed pictures and then creates frames for them to be within
	//Portraits should only be loaded in once
	public CompanionProfile() {
		//If the pictures are loaded already, don't do it again
		if(remPortraits == null) {
			remPortraits = new JLabel[2];
			loadPortraits();
		}
		
		
		//Boring setup, just saying what the background is,
		//to exit when you close the window, and putting in the
		//non-speaking portrait
		frame = new JFrame();
		
		frame.setUndecorated(true);
		frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		currentPortrait = remPortraits[0];
		frame.add(currentPortrait);
		
		//This here ends up getting the proper dimensions of the device
		//Should mean that it always goes to the bottom right corner, though
		//it is not extensively tested as I don't have a bunch of different
		//devices to test and its not really worth setting up
		//a bunch of virtual machines to do so
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		GraphicsConfiguration configuration = device.getDefaultConfiguration();
		Rectangle display = configuration.getBounds();
		int width = (int) display.getWidth();
		int height = (int) display.getHeight();
		Insets screenInset = Toolkit.getDefaultToolkit().getScreenInsets(configuration);
		int barHeight = screenInset.top - screenInset.bottom;
		frame.pack();
		frame.setLocation(width - currentPortrait.getWidth(), height - currentPortrait.getHeight() + barHeight);
		
		
		//Make them able to see it now
		frame.setAlwaysOnTop(true);
		
		frame.setVisible(true);
	}
	
	//Method to load in the portraits from the files included
	//I had it return a boolean for if something was missing
	//but I never ended up utilizing it
	private boolean loadPortraits() {
		
		remPortraits[0] = new JLabel(new ImageIcon("resources/imageFiles/silent.png"));
		remPortraits[1] = new JLabel(new ImageIcon("resources/imageFiles/speak.png"));
		
	
		if(remPortraits[0] == null || remPortraits[1] == null) {
			remPortraits = null;
			return false;
		}
		
		return true;
	}
	
	//Method used to change the visual for what appears to the user
	//gloat is just the name of the speaking portrait, thus the boolean name too
	public void changePortrait(boolean gloat) {
		frame.remove(currentPortrait);
		if(gloat) {
			currentPortrait = remPortraits[1];
		}
		else {
			currentPortrait = remPortraits[0];
		}
		frame.add(currentPortrait, 0);
		frame.revalidate();
		frame.repaint();
	}
	
	

}
