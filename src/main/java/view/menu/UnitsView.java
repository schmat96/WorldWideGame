package view.menu;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Charakter;
import model.Spieler;

public class UnitsView extends MenuViews {
    
	

	private static final double WIDTH = 375;
    private static final double HEIGHT = 568;
	
	private int rowIndex = 1;

	    
	   
	   private Text scenetitle;
	      

	   



	private Button removeMoneyBtn;
	   private Button addMoneyBtn;
	   private HBox hbBtn;
	    
	   public UnitsView(Dimension dim) {
		   
		   super(dim);
		   
		   

	      
	      scenetitle = new Text("Deine Units");
	      scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	      super.grid.add(scenetitle, 0, 0);
	      
	 
	      super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	   }
	 
	  public void addUnit(Charakter charakter) {
		  Label l = new Label(charakter.getName());
		  super.grid.add(l, 0, rowIndex);
		  rowIndex++;
	  }
	


		
}