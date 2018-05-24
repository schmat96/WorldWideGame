package controller.fight;

import java.util.Iterator;

import model.unit.Enemy;

/**
 * Thread for checking if the boss dies during an Attack Animation.
 * @author Mathias Schmid
 *
 */
public class FinishedThread extends Thread {

	/**
	 * This Boolean is being set to true by any Attack call from {@link AttackPatterThread AttackPatternThread.Class}.
	 * Improves performance.
	 */
	public static boolean needToCheckBossHp = false;
	MainFightController dbf;
	private boolean running = true;

	/**
	 * Sets this Thread as Daemon and initializes the MainFightController which is needed to iterate over all Enemies
	 * #TODO ArrayList of the enemies static access?!
	 * @param dbf
	 */
	public FinishedThread(MainFightController dbf) {
		this.dbf = dbf;
		super.setDaemon(true);
		super.setName("GegnerHpBarThread");
	}
	
    @Override
    public void run() {
 
        while (running) {
        	     	
            if (needToCheckBossHp) {
            	FinishedThread.needToCheckBossHp = false;
            
            	/**
            	 * Iterates over all enemies and checks if they are dead.
            	 */
            	Iterator<Enemy> gosh = dbf.getGegner().iterator();
        		
            	Boolean finished = true;
        		while (gosh.hasNext()) {
        			Enemy current = gosh.next();
        			if (current.getHpCalc()>0) {
                		finished = false;
                	}
        		}
        		if (finished) {
        			/**
        			 * I found no other way to change the ActionListeners of an JavaFX application from a different Thread but the
        			 * Main Thread. Therefore this is a workaround.
        			 */
        			MainFightController.newGegner = true;
        		}
            	
            }
            
 
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                
            }
        }
 
 
    }
}
