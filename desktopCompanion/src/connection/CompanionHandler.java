package connection;

import audio.AudioLibrary;
import visual.CompanionProfile;

/**
 * This class was made to handle the logic of the actual program
 * It utilizes methods defined in the AudioLibrary and RemProfile
 * classes in order to properly align both the audio and visual together
 * to create the illusion of the character actually speaking
 * 
 * @author Colin Fawcett
 */
public class CompanionHandler {
	
	//Amount of variance that each voiceline can have
	private static final int TIME_RATIO = 15;
	
	//Minimum amount of time between voicelines
	private static final int TIME_MIN = 3;

	//Loads in audio and the visual, then loops until the program is ended
	//Essentially just handles the logic behind playing voicelines and 
	//changing the portrait
	public static void main(String args[]) {
		
		//Load in needed objects
		AudioLibrary sounds = new AudioLibrary();
		CompanionProfile portrait = new CompanionProfile();
		
		
		//Just run until the user ends execution
		while(true) {
			
			
			try {
				//Get the amount of time to wait, then make this
				//instance wait for that long
				int time = (int) ((Math.random() * TIME_RATIO) + TIME_MIN);
				Thread.sleep(time * 1000);
				
				//Change portrait to show speaking, speak, then change back
				portrait.changePortrait(true);
				sounds.playRandomSound();
				
				portrait.changePortrait(false);
				
				//I have to do this bit because of sleep so thats why this is here
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
