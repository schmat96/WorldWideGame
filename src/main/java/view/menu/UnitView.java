package view.menu;

import java.awt.Dimension;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Charakter;

public class UnitView extends MenuViews {

	Label unitName;
	Label unitUniversum;
	

	public UnitView(Dimension dim) {

		super(dim);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getUnitButton());
	}

	public void displayUnit(Charakter selectedCharacter) {
		//this.scenetitle.setText(selectedCharacter.getName());
		
	}

}