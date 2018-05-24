package view.fight;

import java.util.ArrayList;
import java.util.Iterator;

import controller.fight.MainFightController;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.NumbersToDisplay;

public class ShowNumbersThread extends Thread{
    
    private ArrayList<NumbersToDisplay> numbersToDisplay = null;
    private Canvas fightScreen;
    private boolean running = true;
 
    public ShowNumbersThread(Canvas fightScreen) {
    	this.numbersToDisplay = MainFightController.getNumbersToDisplay();
    	this.fightScreen = fightScreen;
    	this.setDaemon(true);
    	super.setName("ShowNumbersThread");
	}
 
    @Override
    public void run() {
 
        while (running) {

            Platform.runLater(new Runnable() {
                public void run() {
                	GraphicsContext gc = fightScreen.getGraphicsContext2D();
            		gc.clearRect(0, 0, 300, 200);
                	if (numbersToDisplay.size()>0) {
                		
        				Iterator<NumbersToDisplay> iter = numbersToDisplay.iterator();
        				while (iter.hasNext()) {
        					NumbersToDisplay toDisplay = iter.next();
        					//System.out.println(toDisplay.getArt()+":"+toDisplay.getUnit()+":"+toDisplay.getValue()+"--"+toDisplay.getXDisplay()+"-"+toDisplay.getYDisplay());

        					Color c = toDisplay.getColor();
        					gc.setStroke(c);
        					gc.strokeText(toDisplay.getValue()+"", toDisplay.getXDisplay(), toDisplay.getYDisplay());
        					if (toDisplay.destroy()) {
        						iter.remove();
        					}
        					
        				}
        			}
                }
            });
 
            try {
                sleep(40);
            } catch (InterruptedException ex) {
                
            }
        }
 
 
    }

	public void setRunning(boolean b) {
		this.running = b;
	}
 
}
