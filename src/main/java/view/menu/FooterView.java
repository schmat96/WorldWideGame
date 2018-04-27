package view.menu;

import constants.LayoutConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FooterView extends GridPane {
	
	
	private final int BUTTON_ANZAHL = 4;
	private final int BUTTON_MARGIN = 10;
	private final int BUTTON_WIDTH = ((LayoutConstants.WIDTH-100)/BUTTON_ANZAHL);
	private HBox[] buttons = new HBox[BUTTON_ANZAHL];
	private Button unitButton;
	private Button specialButton;
	private Button summonButton;
	private Button homeButton;
	

	public FooterView() {
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
		
		summonButton = new Button("Summon");
		buttons[2] = createButton(summonButton);
		this.add(buttons[2], 2, 0);
		
		specialButton = new Button("Special");
		buttons[3] = createButton(specialButton);
		this.add(buttons[3], 3, 0);
		
		resetButtonBackgrounds();
	}

	private HBox createButton(Button b) {
		b.setMinWidth(BUTTON_WIDTH);
		HBox hbBtn1 = new HBox(BUTTON_MARGIN);
		
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
	
	public Button getSummonButton() {
		return this.summonButton;
	}

	public Button getHomeButton() {
		return homeButton;
	}

	public void setFocus(Button button) {
		resetButtonBackgrounds();
		button.setStyle(LayoutConstants.STYLE_ELEMENTS_ON_FOCUSED);
		button.requestFocus();
	}

	private void resetButtonBackgrounds() {
		unitButton.setStyle(LayoutConstants.STYLE_ELEMENTS);
		specialButton.setStyle(LayoutConstants.STYLE_ELEMENTS);
		summonButton.setStyle(LayoutConstants.STYLE_ELEMENTS);
		homeButton.setStyle(LayoutConstants.STYLE_ELEMENTS);
		
	}

}
