package audio;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class that is used to handle the voice clips of the character
 * Essentially it would really suck to do all the functions of the program
 * in one class, so break it into pieces, Audio, Visual, and Logic.
 * This one handles audio by loading it in, and then being able to make requests to it
 * 
 * @author Colin Fawcett
 */
public class AudioLibrary {
	
	//Once everything is in the list, there are only gets, so array over linked
	private static ArrayList<Clip> audio;
	
		
	//Private constructor that loads in audio clips
	public AudioLibrary() {
		if(audio == null) {
			audio = new ArrayList<Clip>();
			loadAudio();
		}
		
	}
	
	
	private void loadAudio() {
		//Stored audio folder
		File folder = new File("resources/audioFiles");
		//For every file in the folder
		for(File fileEntry: folder.listFiles()) {
			//Make a new clip for the list
			try {
				//Get the audio stream
			    AudioInputStream currentAudio = AudioSystem.getAudioInputStream(fileEntry);
			    //Make a clip for it
			    Clip currentClip = AudioSystem.getClip();
			    currentClip.open(currentAudio);
			    //Close the stream
			    currentAudio.close();
			    //Add it to the list
			    audio.addLast(currentClip);
			}
			catch(Exception e) {
				//Just don't add it to the list then
			}
		}
		
		//Done
		
		
	}
	
	//Picks an index in the list and then plays that sound
	//It says random but because of how computers work it 
	//technically isn't
	public void playRandomSound() {
		//Get a random index
		int index = (int) (Math.random() * audio.size());
		Clip currentClip = audio.get(index);
		//Play it for however long it is
		long duration = currentClip.getMicrosecondLength();
		currentClip.start();
		//Have everything else wait while the sound is playing
		//Probably doesn't change much since I didn't really
		//thread this program, but I figured it would be good practice
		//for if I ended up adding things that do concurrently run
		try {
			Thread.sleep(duration / 1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		//End the clip and set it back to the start
		currentClip.stop();
		currentClip.setFramePosition(0);
	}
	
	public void playSpecificSound(int index) {
		//Get a specific index
		Clip currentClip = audio.get(index);
		//Play it for however long it is
		long duration = currentClip.getMicrosecondLength();
		currentClip.start();
		//Have everything else wait while the sound is playing
		//Probably doesn't change much since I didn't really
		//thread this program, but I figured it would be good practice
		//for if I ended up adding things that do concurrently run
		try {
			Thread.sleep(duration/1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		//End the clip and set it back to the start
		currentClip.stop();
		currentClip.setFramePosition(0);
	}
	
	public void shutdownLibrary() {
		//Make sure all the clips are closed
		for(Clip currentClip: audio) {
			currentClip.close();
		}
	}
		
}
