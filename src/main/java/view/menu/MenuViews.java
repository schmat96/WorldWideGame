package view.menu;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Spieler;

public abstract class MenuViews {

	protected HeaderView header;
	protected FooterView footer;
	protected BorderPane hauptGrid;
	
	   protected GridPane grid;
	   protected Scene scene;
	   
	   protected Button backBtn;
	
	protected MenuViews(Dimension dim) {
	   	header = new HeaderView();
	   	footer = new FooterView();
		
		 
		
		hauptGrid = new BorderPane();
		
		   grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
		
		hauptGrid.setBottom(footer);
		hauptGrid.setCenter(grid);
		hauptGrid.setTop(header);
	}
	
	 public void show(Stage stage) {
	      stage.setTitle("World Wide Game - Main");
	      stage.setScene(scene);
	      stage.show();
	   }
	 
	   public void setData(Spieler spieler) {
			this.header.setEnergie(spieler.getGeld());
			this.header.level("Level: "+ spieler.getExp());
			this.header.spielerName(spieler.getName());
		}
	   
		public HeaderView getHeader() {
			return this.header;
		}
		
		 public Button getBackBtn() {
		      return backBtn;
		   }
		 
		 public FooterView getFooter() {
			 return this.footer;
		 }
	 
}