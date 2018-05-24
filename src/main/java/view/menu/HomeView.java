package view.menu;

import static java.lang.Math.random;

import java.awt.Dimension;

import constants.ImagesPreloader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.unit.Charakter;

public class HomeView extends MenuViews {

	private Text scenetitle;

	private Button removeMoneyBtn;
	private Button addMoneyBtn;
	private HBox hbBtn;


	public HomeView(Dimension dim) {

		super(dim);

		scenetitle = new Text("Hallo zum Weltweiten Spiel :)");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		super.grid.add(scenetitle, 0, 0, 2, 1);

		// Button
		backBtn = new Button("Ausloggen");

		addMoneyBtn = new Button("Add Money");

		removeMoneyBtn = new Button("Remove Money");

		// Buttongruppe
		hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(backBtn);
		grid.add(hbBtn, 0, 3);
		//grid.add(addMoneyBtn, 0, 2);
		//grid.add(removeMoneyBtn, 1, 2);

		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	public Button getRemoveMoneyBtn() {
		return removeMoneyBtn;
	}

	public Button getAddMoneyBtn() {
		return addMoneyBtn;
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getHomeButton());
	}

	public GridPane addImage(Charakter charakter) {
		ImageView image = ImagesPreloader.getCharakterImageBig(charakter);
		Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                new KeyValue(image.translateXProperty(), -1000),
                new KeyValue(image.translateYProperty(),-1000)),
                new KeyFrame(new Duration(30000), // set end position at 40s
                new KeyValue(image.translateXProperty(),random()*300+1000),
                new KeyValue(image.translateYProperty(), random()*300+1000)));
        timeline.play();
        GridPane grid = new GridPane();
        grid.add(image,0,0);
        super.grid.getChildren().add(grid);
        
		return grid;
		
		
	}

}