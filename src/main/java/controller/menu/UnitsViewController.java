package controller.menu;

import java.util.ArrayList;
import java.util.Iterator;

import model.Charakter;
import model.DataBean;
import view.menu.UnitsView;

public class UnitsViewController extends MainViewsController implements Runnable {

	public UnitsViewController(DataBean dataBean) {
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
