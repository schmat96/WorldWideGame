package main;

import java.util.Random;

public class RandomProgress extends Thread {
	
	private Main main;
	private boolean running = true;
	private Random rng;
	
	public RandomProgress(Main main) {
		this.main = main;
		rng = new Random();
	}
	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (this.rng.nextInt(2)==1) {
				this.main.notifyFromRandomProgress();
			}
		}
		
	}
	
	public void terminate() {
		running = false;
	}

}
