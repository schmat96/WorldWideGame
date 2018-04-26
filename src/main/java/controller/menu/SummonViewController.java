package controller.menu;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Charakter;
import model.DataBean;
import model.Universum;
import view.menu.SummonView;
import view.menu.UnitsView;

public class SummonViewController extends MainViewsController {

	public SummonViewController(DataBean dataBean) {
		super(dataBean);
		super.view = new SummonView(dataBean.getDimension());
		addListener();
		displayUniversums();
	}

	@Override
	protected void addListener() {
		super.addListener();

	}
	
	private void displayUniversums() {
		while (dataBean.loading()) {
			dataBean.loadUniversums();
		}
		ArrayList<Universum> universums = dataBean.getLoadedUniversums();
		Iterator<Universum> iter = universums.iterator();
		while (iter.hasNext()) {
			Universum current = iter.next();
			Button sumButton = ((SummonView) view).addUniversum(current);
			sumButton.setOnAction(new summonCharacter(current, this.dataBean));
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++
	// Events
	// +++++++++++++++++++++++++++++++++++++++++++++
	
	class summonCharacter implements EventHandler<ActionEvent> {

		private DataBean dataBean;
		private Universum universum;

		public summonCharacter(Universum universum, DataBean dataBean) {
			super();
			this.dataBean = dataBean;
			this.universum = universum;
		}

		public void handle(ActionEvent event) {
			System.out.println("Beschwören von: "+this.universum.getName());
			//this.dataBean.summonUnit(this.universum);
		}
	}

}
