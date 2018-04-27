package view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import constants.LayoutConstants;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Charakter;

public class UnitsView extends MenuViews {

	private int rowIndex = 1;
	private int columnIndex = 0;
	
	private final int UNITS_IN_ROW = 3;

	private Text scenetitle;

	public UnitsView(Dimension dim) {

		super(dim);
		//scenetitle = new Text("Deine Units");
		//scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		//super.grid.add(scenetitle, 0, 0);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	public VBox addUnit(Charakter charakter) {
		VBox canvas = new VBox();
		
		canvas.setCursor(Cursor.HAND);
		canvas.setAlignment(Pos.CENTER);
		canvas.setStyle(LayoutConstants.STYLE_ELEMENTS);
	    Circle circle = new Circle(20);

		Label name = new Label(charakter.getName());
		Label universum = new Label(charakter.getUniversum().getName());

		canvas.getChildren().add(circle);
		canvas.getChildren().add(name);
		canvas.getChildren().add(universum);

		super.grid.add(canvas, columnIndex, rowIndex);
		columnIndex++;
		if (columnIndex==UNITS_IN_ROW) {
			rowIndex++;
			columnIndex=0;
		}
		
		return canvas;
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getUnitButton());
	}

}