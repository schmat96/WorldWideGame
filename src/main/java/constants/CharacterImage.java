package constants;

import java.util.HashMap;

import org.jboss.logging.Logger.Level;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.unit.Charakter;

/**
 * Loads an imageView of the character with his CharacterStates into a HashMap. Used to improve reloads in View.
 * @author Mathias Schmid
 *
 */
public class CharacterImage {
	
	private HashMap<CharacterStates, ImageView> hashMapCharacterState;
	private Charakter charakter;
	
	/**
	 * Constructor
	 * @param charakter
	 */
	public CharacterImage(Charakter charakter) {
		this.charakter = charakter;
	}
	
	/**
	 * 
	 * @param cs the The CharacterState you would like to get the ImageView of.
	 * @return the ImageView of the CharacterState and the Character
	 */
	public ImageView getCharacterState(CharacterStates cs) {
		if (hashMapCharacterState==null) {
			hashMapCharacterState = new HashMap<CharacterStates, ImageView>();
			loadCharacterState(cs);
		}
		if (hashMapCharacterState.containsKey(cs)) {
			return hashMapCharacterState.get(cs);
		}
		loadCharacterState(cs);
		return hashMapCharacterState.get(cs);
	}

	/**
	 * Loads the ImageView of the Chararacter and the CharacterState
	 * @param cs
	 */
	private void loadCharacterState(CharacterStates cs) {
		String path = "units/"+charakter.getName().toLowerCase()+"/"+cs.toString().toLowerCase()+".png";
		ImageView image = ImagesPreloader.getDefaultImage();
		try {
			image = new ImageView(new Image(path, 30, 30, false, false));
		} catch (NullPointerException e) {
			String message = "Image missing from Charakter: "+path;
			ImagesPreloader.ImagesPreloaderLogger.log(Level.WARN, message);
			ImagesPreloader.ownTestLogger.add(message);
		} catch (IllegalArgumentException e) {
			String message = "Image missing from Charakter: "+path;
			ImagesPreloader.ImagesPreloaderLogger.log(Level.WARN, message);
			ImagesPreloader.ownTestLogger.add(message);
		}
		hashMapCharacterState.put(cs, image);
		
	}
}
