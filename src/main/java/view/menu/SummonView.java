package view.menu;

import java.awt.Dimension;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Universum;

public class SummonView extends MenuViews {

	private int rowIndex = 1;
	private Text scenetitle;

	public SummonView(Dimension dim) {

		super(dim);
		scenetitle = new Text("Summon");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		super.grid.add(scenetitle, 0, 0);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	public Button addUniversum(Universum current) {
		Button summ = new Button("Summon");
		
		Label l = new Label(current.getName());
		
		Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
		
		HBox hbBtn = new HBox(l, region1, region2, summ);
		super.grid.add(hbBtn, 0, rowIndex);
		rowIndex++;
		return summ;
		
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getSummonButton());
	}
	
}