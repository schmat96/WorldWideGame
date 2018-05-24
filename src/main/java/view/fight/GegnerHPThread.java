package view.fight;

import controller.fight.MainFightController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.unit.Enemy;

public class GegnerHPThread extends Thread {

	private boolean running = true;
	private ProgressBar progressBar;
	private Enemy enemy;
	private Label label;
	private  MainFightController mfc;
	
	public GegnerHPThread(ProgressBar pb, Label hp, Enemy enemy, MainFightController mfc) {
		this.progressBar = pb;
		this.label = hp;
		this.enemy = enemy;
		this.mfc = mfc;
		super.setDaemon(true);
		super.setName("GegnerHpBarThread");
	}
	
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
    @Override
    public void run() {
 
        while (running) {

            Platform.runLater(new Runnable() {
                public void run() {
                	progressBar.setProgress(enemy.getProgressHp());
                	label.setText(enemy.getProgressHpString());                	
                }
            });
 
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                
            }
        }
        return;
 
 
    }
    
    public void setRunning(Boolean b) {
    	this.running = b;
    }
}
