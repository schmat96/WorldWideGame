package view.fight;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Case extends GridPane {
	public Case(BorderPane grid) {
		super();

		this.setMinWidth(grid.getWidth()/2-10);
		
	}
}
