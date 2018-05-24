package constants;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Ability;
import model.AbilityArt;
import model.Elemente;
import model.unit.Charakter;

/**
 * This Class works like a Factory and holds in static HashMaps the Icons for Elemente and AbilityArt. Improves
 * loading times in view.
 * @author Mathias Schmid
 *
 */
public class ImagesPreloader {

	private static ImageView defaultImage;
	
	public static final Logger ImagesPreloaderLogger = Logger.getLogger("imageLoader");
	public static LinkedList<String> ownTestLogger = new LinkedList<String>();
	
	private static HashMap<AbilityArt, ImageView> hashMapAbilityArt;
	private static HashMap<Elemente, ImageView> hashMapElement;
	private static HashMap<Charakter, CharacterImage> hashMapCharacter;
	
	/**
	 * Loads the default imageView. This image works as a placeholder for all missing images.
	 */
	private static void loadDefault() {
		try {
			defaultImage = new ImageView(new Image("icons/fire.jpg", 30, 30, true, true));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadImages(ArrayList<Charakter> choosenUnits) {

		for (Charakter unit : choosenUnits) {
			for (CharacterStates cs : CharacterStates.values()) {
				ImagesPreloader.getCharakterImages(unit, cs);
			}
		}
		
		for (Elemente ele : Elemente.values()) {
			ImagesPreloader.getElementeArtImages(ele);
		}
		
		for (AbilityArt aa : AbilityArt.values()) {
			ImagesPreloader.getAbilityArtImages(aa);
		}
		
	}

	public static ImageView getCharakterImageBig(Charakter charakter) {
		String path = "units/"+charakter.getName().toLowerCase()+"/"+CharacterStates.alive.toString().toLowerCase()+".png";
		ImageView image = ImagesPreloader.getDefaultImage();
		try {
			image = new ImageView(new Image(path, 100, 100, false, false));
		} catch (NullPointerException e) {
			String message = "Image missing from Charakter: "+path;
			ImagesPreloader.ImagesPreloaderLogger.log(Level.WARN, message);
			ImagesPreloader.ownTestLogger.add(message);
		} catch (IllegalArgumentException e) {
			String message = "Image missing from Charakter: "+path;
			ImagesPreloader.ImagesPreloaderLogger.log(Level.WARN, message);
			ImagesPreloader.ownTestLogger.add(message);
		}
		return image;
	}
	
	public static ImageView getDefaultImage() {
		if (defaultImage==null) {
			loadDefault();
		}
		return defaultImage;
	}
	
	public static String getLastLoggedMessage() {
		return ownTestLogger.removeLast();
	}
	
	public static ImageView getCharakterImages(Charakter charakter, CharacterStates cs) {
		if (hashMapCharacter==null) {
			hashMapCharacter = new HashMap<Charakter, CharacterImage>();
		}
		if (hashMapCharacter.containsKey(charakter)) {
			return hashMapCharacter.get(charakter).getCharacterState(cs);
		}
		hashMapCharacter.put(charakter, new CharacterImage(charakter));
		return hashMapCharacter.get(charakter).getCharacterState(cs);
    }
	
	public static ImageView getElementeArtImages(Elemente ele) {
		if (hashMapElement==null) {
			hashMapElement = new HashMap<Elemente, ImageView>();
		}
		if (hashMapElement.containsKey(ele)) {
			return hashMapElement.get(ele);
		}
		if (defaultImage == null) {
			loadDefault();
		}
		
		String path = "elemente/"+ele.toString().toLowerCase()+".png";
		ImageView image = defaultImage;
		try {
			image = new ImageView(new Image(path, 30, 30, false, false));
		} catch (NullPointerException e) {
			String message = "Image missing from Element: "+path;
			ImagesPreloaderLogger.log(Level.WARN, message);
			ownTestLogger.add(message);
		} catch (IllegalArgumentException e) {
			String message = "Image missing from Element: "+path;
			ImagesPreloaderLogger.log(Level.WARN, message);
			ownTestLogger.add(message);
		}
		hashMapElement.put(ele, image);
		return image;
    }
	
	public static ImageView getAbilityArtImages(AbilityArt aa) {
		if (hashMapAbilityArt==null) {
			hashMapAbilityArt = new HashMap<AbilityArt, ImageView>();
		}
		if (hashMapAbilityArt.containsKey(aa)) {
			return hashMapAbilityArt.get(aa);
		}
		if (defaultImage == null) {
			loadDefault();
		}
		
		String path = "icons/"+aa.toString().toLowerCase()+".png";
		ImageView image = defaultImage;
		try {
			image = new ImageView(new Image(path, 30, 30, false, false));
		} catch (NullPointerException e) {
			String message = "Image missing from AbilityArt: "+path;
			ImagesPreloaderLogger.log(Level.WARN, message);
			ownTestLogger.add(message);
		} catch (IllegalArgumentException e) {
			String message = "Image missing from AbilityArt: "+path;
			ImagesPreloaderLogger.log(Level.WARN, message);
			ownTestLogger.add(message);ImagesPreloaderLogger.log(Level.WARN, "Image missing from AbilityArt: "+path);
		}
		hashMapAbilityArt.put(aa, image);
		return image;

    }

	
}
