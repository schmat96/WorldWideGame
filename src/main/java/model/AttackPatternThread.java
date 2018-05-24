package model;


import java.util.LinkedList;
import java.util.Queue;

import controller.fight.FinishedThread;
import model.unit.Charakter;






public class AttackPatternThread extends Thread {
    
    private Queue<String> attackPattern;
    private DataBeanFight dataBeanFight;
    private Ability ability;
    private AbilityArt abilityArt;
    
    private double atkCalc;
    private double abilityMod;
    private boolean running;
 
 
    public AttackPatternThread(String patter2, DataBeanFight dataBeanFight2, Ability ability, Charakter charakter) {
    	attackPattern = new LinkedList<String>();
    	this.ability = ability;
    	this.abilityArt = ability.getAbilityArt();
    	switch (abilityArt) {
		case Angriff:
			this.atkCalc = charakter.getAtkCalc();
			break;
		case Magie:
			this.atkCalc = charakter.getMagCalc();
			break;
		default:
			break;
    	
    	}
    	this.abilityMod = ability.getModifier();
    	this.dataBeanFight = dataBeanFight2;
    	String[] aPattern = patter2.split(";");
    	for(String elem : aPattern) {
    		attackPattern.add(elem);
    	}
    	running = true;
    	super.setName("AttackPatternThread");

	}

	@Override
    public void run() {
 
        while (running) {
        	
        	if (attackPattern.isEmpty()) {
        		running = false;
        	 } else {
        		FinishedThread.needToCheckBossHp = true;
        		String[] singletons = attackPattern.remove().split("=");
        		int wait = 0;
        		double mod = 0;
        		try {
        			wait = Integer.parseInt(singletons[0]);
            		mod = Double.parseDouble(singletons[1]);
        		} catch (NumberFormatException e) {
        			wait = 0;
            		mod = 0;
        		} catch (ArrayIndexOutOfBoundsException e) {
        			wait = 0;
            		mod = 0;
        		}
        		
        		switch (this.abilityArt) {
        		case Angriff:
        			dataBeanFight.makeDmgPhy((int) (atkCalc*(abilityMod*mod)));
        			break;
        		case Magie:
        			dataBeanFight.makeDmgMag((int) (atkCalc*(abilityMod*mod)),ability.getElement());
        			break;
        		default:
        			break;
        		}
            	
            	
                try {
                    sleep(wait);
                } catch (InterruptedException ex) {
                    
                }
        	}
        }
        return;
 
    }

	public void setRunning(boolean b) {
		this.running = b;
		
	}
 
}