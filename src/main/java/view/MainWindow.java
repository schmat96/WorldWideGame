package view;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Spieler;

public class MainWindow extends MenuViews {
    
	
    private static final double WIDTH = 375;
    private static final double HEIGHT = 568;
	
	

	    
	   
	   private Text scenetitle;
	      

	   



	private Button removeMoneyBtn;
	   private Button addMoneyBtn;
	   private HBox hbBtn;
	    
	   public MainWindow(Dimension dim) {
		   
		   super(dim);
		   
		   

	      
	      scenetitle = new Text("Sicher eingeloogt");
	      scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	      super.grid.add(scenetitle, 0, 0, 2, 1);
	       
	      // Button
	      backBtn = new Button("zurück");
	      
	      addMoneyBtn = new Button("Add Money");
	      
	      removeMoneyBtn = new Button("Remove Money");
	 
	      // Buttongruppe
	      hbBtn = new HBox(10);
	      hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	      hbBtn.getChildren().add(backBtn);
	      grid.add(hbBtn, 0, 3);
	      grid.add(addMoneyBtn, 0, 2);
	      grid.add(removeMoneyBtn, 1, 2);
	 
	      super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	   }
	 
	  
	  
	   
	   public Button getRemoveMoneyBtn() {
		return removeMoneyBtn;
	}

	public Button getAddMoneyBtn() {
		return addMoneyBtn;
	}
	


		
}