package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import model.Charakter;
import model.DataBean;
import model.Spieler;
import view.MainWindow;
import view.UnitsView;

public class UnitsVC extends MainViewController implements Runnable {

	private boolean running = false;
	
	public UnitsVC(DataBean dataBean) {
		super(dataBean);
		view = new UnitsView(dataBean.getDimension());
		addListener();
		displayUnits();
	}

	@Override
	protected void addListener() {
		super.addListener();
	}
	
	private void displayUnits() {
		while (dataBean.loading()) {
			dataBean.loadCharakters();
		}
		ArrayList<Charakter> characters = dataBean.getLoadedCharakters();
		Iterator<Charakter> iter = characters.iterator();
		while (iter.hasNext()) {
			Charakter current = iter.next();
			((UnitsView) view).addUnit(current);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	


}
