package view.menu;

import java.awt.Dimension;

import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SummonView extends MenuViews {

	private Text scenetitle;

	public SummonView(Dimension dim) {

		super(dim);

		scenetitle = new Text("Summon");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		super.grid.add(scenetitle, 0, 0);

		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}
}