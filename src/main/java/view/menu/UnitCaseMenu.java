package view.menu;

import constants.LayoutConstants;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.unit.Charakter;

public class UnitCaseMenu extends VBox {


	private HBox iconBox;
	private Label number;
	
	public UnitCaseMenu(Charakter charakter) {
		super();
		VBox canvas = new VBox();

		canvas.setCursor(Cursor.HAND);
		canvas.setAlignment(Pos.CENTER);
		canvas.setStyle(LayoutConstants.STYLE_ELEMENTS);
	    
	    Image image = new Image("units/"+charakter.getImageSource()+"/alive.png", 35, 35, false, true);
        
	    iconBox = new HBox();
	    iconBox.getChildren().add(new ImageView(image));
	    number = new Label("");
	    number.setStyle(LayoutConstants.FONT_SIZE_BIG);
		this.iconBox.getChildren().add(number);
		Label name = new Label(charakter.getName());
		Label universum = new Label(charakter.getUniversum().getName());

		canvas.getChildren().add(iconBox);
		canvas.getChildren().add(name);
		canvas.getChildren().add(universum);
		canvas.setMinWidth((LayoutConstants.WIDTH-(LayoutConstants.UNITS_IN_ROW*25))/LayoutConstants.UNITS_IN_ROW);

		this.getChildren().add(canvas);
	
	}

	public void setMarked(int pos) {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
		number.setText((pos+1)+"");
		
	}

	public void removeMark() {
		this.setBackground(null);
		number.setText("");
	}
}
