package controller.menu;

import java.util.ArrayList;
import java.util.Iterator;

import constants.LayoutConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.DataBean;
import model.unit.Charakter;
import view.menu.MenuViews;
import view.menu.UnitsView;

public class UnitsViewController extends MainViewsController {

	public UnitsViewController(DataBean dataBean) {
		super(dataBean);
		view = new UnitsView(LayoutConstants.DIMENSION);
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
			VBox unit = ((UnitsView) view).addUnit(current);
			unit.setOnMousePressed(new changeToUnit(dataBean, current));
			unit.setOnMouseEntered(new changeFocus(unit));
			unit.setOnMouseExited(new removeFocus(unit));
		}
	}
}

class changeToUnit implements EventHandler<MouseEvent> {

	private DataBean dataBean;
	private Charakter current;

	public changeToUnit(DataBean dataBean, Charakter current) {
		super();
		this.dataBean = dataBean;
		this.current = current;
	}

	@Override
	public void handle(MouseEvent event) {
		UnitViewController unitsView = new UnitViewController(this.dataBean, current);
		unitsView.show();
	}
}

class changeFocus implements EventHandler<MouseEvent> {

	private DataBean dataBean;
	private VBox current;

	public changeFocus(VBox current) {
		super();
		this.current = current;
	}

	@Override
	public void handle(MouseEvent event) {
		this.current.setStyle(LayoutConstants.STYLE_ELEMENTS_ON_FOCUSED);
	}
}

class removeFocus implements EventHandler<MouseEvent> {

	private VBox current;

	public removeFocus(VBox current) {
		super();
		this.current = current;
	}

	@Override
	public void handle(MouseEvent event) {
		this.current.setStyle(LayoutConstants.STYLE_ELEMENTS);
	}
}

