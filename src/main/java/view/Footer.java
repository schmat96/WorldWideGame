package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Footer extends GridPane {
	
	private HBox[] buttons = new HBox[3];
	private Button unitButton;
	private Button specialButton;
	private Button homeButton;

	public Footer() {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color: #C0C0C0;");
		this.setPadding(new Insets(25, 25, 25, 25));
		
		homeButton = new Button("Home");
		buttons[0] = createButton(homeButton);
		this.add(buttons[0], 0, 0);
		
		unitButton = new Button("Units");
		buttons[1] = createButton(unitButton);
		this.add(buttons[1], 1, 0);
		
		specialButton = new Button("Special");
		buttons[2] = createButton(specialButton);
		this.add(buttons[2], 2, 0);
	}

	private HBox createButton(Button b) {

		HBox hbBtn1 = new HBox(10);
		hbBtn1.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn1.getChildren().add(b);
		return hbBtn1;
	}
	
	public Button getUnitButton() {
		return unitButton;
	}

	public void setUnitButton(Button unitButton) {
		this.unitButton = unitButton;
	}

	public Button getSpecialButton() {
		return specialButton;
	}

	public Button getHomeButton() {
		return homeButton;
	}

}
