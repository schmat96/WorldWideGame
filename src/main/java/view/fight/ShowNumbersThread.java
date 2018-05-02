package view.fight;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.NumbersToDisplay;

public class ShowNumbersThread extends Thread{
    
    private ArrayList<NumbersToDisplay> numbersToDisplay = null;
    private Canvas fightScreen;
 
    public ShowNumbersThread(ArrayList<NumbersToDisplay> numbersToDisplay, Canvas fightScreen) {
    	this.numbersToDisplay = numbersToDisplay;
    	this.fightScreen = fightScreen;
	}
 
    @Override
    public void run() {
 
        while (!this.isInterrupted()) {
             

            Platform.runLater(new Runnable() {

                public void run() {
                	GraphicsContext gc = fightScreen.getGraphicsContext2D();
            		gc.clearRect(0, 0, 300, 200);
                	if (numbersToDisplay.size()>0) {
                		
        				Iterator<NumbersToDisplay> iter = numbersToDisplay.iterator();
        				while (iter.hasNext()) {
        					NumbersToDisplay toDisplay = iter.next();
        					System.out.println(toDisplay.getArt()+":"+toDisplay.getUnit()+":"+toDisplay.getValue());
        					
        					gc.strokeText(toDisplay.getValue()+"", 50, toDisplay.getXDisplay());
        					if (toDisplay.destroy()) {
        						iter.remove();
        					}
        					
        				}
        			}
                }
            });
 
            // Thread schlafen
            try {
                // fuer 3 Sekunden
                sleep(40);
            } catch (InterruptedException ex) {
                
            }
        }
 
 
    }
 
}
