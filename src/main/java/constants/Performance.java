package constants;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * If called this logs some Information about the performane.
 * @deprecated
 * @author Mathias Schmid
 *
 */
public class Performance {
	
	private final static Logger LOGGER = Logger.getLogger("performance");
	
	private static long memoryLossFromLastCall = 0;
	
	public static void logPerformance() {
		LOGGER.log(Level.INFO, "Free Memory: " + Runtime.getRuntime().freeMemory());
		LOGGER.log(Level.INFO, "Total Memory: " + Runtime.getRuntime().totalMemory());
		LOGGER.log(Level.INFO, "Available Processors: " + Runtime.getRuntime().availableProcessors());
		
		if (memoryLossFromLastCall==0) {
			memoryLossFromLastCall = Runtime.getRuntime().freeMemory();
		} else {
			LOGGER.log(Level.INFO, "Memory Loss from Last Call " +  (memoryLossFromLastCall-Runtime.getRuntime().freeMemory()));
			memoryLossFromLastCall = Runtime.getRuntime().freeMemory();
		}
		
	}
}
