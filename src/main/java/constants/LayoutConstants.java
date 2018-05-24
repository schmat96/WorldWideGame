package constants;

import java.awt.Dimension;

/**
 * Class for handling layout Constants
 * 
 * @author schmidmath
 */
public class LayoutConstants {
	
	
	
	
	/**
	 * @deprecated should be replaced with a css.
	 */
	
	
	public final static String STYLE_ELEMENTS = "-fx-border-color: #000000; -fx-border-width: 1px";
	
	/**
	 * @deprecated should be replaced with a css.
	 */
	public final static String STYLE_ELEMENTS_ON_FOCUSED = "-fx-border-color: #0000DD; -fx-border-width: 1px";

            
	public static final int WIDTH = 320;
    public static final int HEIGHT = 568;
    public static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
    
	public static final int UNITS_IN_ROW = 3;

	public static final String FONT_SIZE_BIG = "-fx-font-size: 15pt;";
    
    
	public static double GetPositionUnitX(int position) {
		return ((position%2)*50+180);
	}

	public static double GetPositionUnitY(int position) {
		return Math.round(position/2)*70-position*15+80;
	}
	
	public static double GetPositionEnemyX(int position) {
		return (position%2)*50+20;
	}
	
	public static double GetPositionEnemyY(int position) {
		return Math.round(position/2)*70-position*15+80;
	}
	
}
