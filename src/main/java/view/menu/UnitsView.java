package view.menu;

import java.awt.Dimension;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Charakter;

public class UnitsView extends MenuViews {

	private int rowIndex = 1;

	private Text scenetitle;

	public UnitsView(Dimension dim) {

		super(dim);

		scenetitle = new Text("Deine Units");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		super.grid.add(scenetitle, 0, 0);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	public void addUnit(Charakter charakter) {
		Label l = new Label(charakter.getName() + " from "+charakter.getUniversum().getName());
		super.grid.add(l, 0, rowIndex);
		rowIndex++;
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getUnitButton());
	}

}