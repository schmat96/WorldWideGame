package view.menu;

import java.awt.Dimension;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import model.unit.Charakter;

public class UnitsView extends MenuViews {

	private int rowIndex = 1;
	private int columnIndex = 0;
	
	private FlowPane grid;

	private Text scenetitle;

	public UnitsView(Dimension dim) {
		super(dim);
		grid = new FlowPane();
		hauptGrid.setCenter(grid);
		grid.setAlignment(Pos.CENTER);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	public UnitCaseMenu addUnit(Charakter charakter) {
		UnitCaseMenu uc = new UnitCaseMenu(charakter);
		grid.getChildren().add(uc);
		return uc;
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getUnitButton());
	}

}