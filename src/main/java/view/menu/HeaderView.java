package view.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HeaderView extends GridPane {
	
	private Label energie;
	private Label level;
	private Label spielerName;
	
	public HeaderView() {
		super();
		this.setAlignment(Pos.TOP_CENTER);
		this.setHgap(5);
		this.setVgap(5);
		this.setStyle("-fx-background-color: #C0C0C0;");
		Label titel = new Label("WorldWideGame");
		this.add(titel, 1, 1);
		
		energie = new Label();
		energie.setAlignment(Pos.CENTER);
		this.add(energie, 0, 0);
		
		level = new Label();
		level.setAlignment(Pos.CENTER);
		this.add(level, 1, 0);
		
		this.spielerName = new Label();
		spielerName.setAlignment(Pos.CENTER);
		this.add(spielerName, 2, 0);
	
	}
	
	public void setEnergie(int zahl) {
		energie.setText("Geld: "+zahl);		
	}
	
	public void level(String l) {
		level.setText(l);
	}
	
	public void spielerName(String s) {
		spielerName.setText(s);
	}
	

}
