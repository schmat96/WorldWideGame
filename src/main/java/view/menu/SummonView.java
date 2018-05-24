package view.menu;

import java.awt.Dimension;

import constants.CharacterStates;
import constants.ImagesPreloader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;
import static java.lang.Math.random;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Universum;
import model.unit.Charakter;

public class SummonView extends MenuViews {

	private int rowIndex = 1;
	private Text scenetitle;
	private final int CIRCLE_RADIUS = 60;
	private Label hinweis;
	
	public SummonView(Dimension dim) {
		super(dim);
		scenetitle = new Text("Summon");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		super.grid.add(scenetitle, 0, 0);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}
	
	public void crystalView(Dimension dim, Charakter charakter) {
		
		super.grid.getChildren().clear();
		
		if (charakter==null) {
			super.grid.getChildren().add(new Label("Leider nichts"));
		} else {
			super.grid.getChildren().add(ImagesPreloader.getCharakterImageBig(charakter));
			//Label label = new Label(charakter.getName());
			//label.setLayoutY(-180);
			//super.grid.getChildren().add(label);
		}
        
		
		
		for (int i = 0; i < 50; i++) {
            Circle circle = new Circle(CIRCLE_RADIUS, Color.color(random(),random(),random(),random()));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.toBack();
            circle.setStrokeWidth(4);
            super.grid.getChildren().add(circle);
            Timeline timeline = new Timeline();

            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), 0),
                    new KeyValue(circle.translateYProperty(),0)),
                    new KeyFrame(new Duration(10000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), ((random()-0.5) * dim.getWidth()*2)),
                    new KeyValue(circle.translateYProperty(), getYProperty(dim.getHeight()))));
            timeline.play();
        }		
	}



	private double getYProperty(double height) {
		double hush = (random()-0.5) * height * 2;
		if (hush < CIRCLE_RADIUS*2 && hush > -(CIRCLE_RADIUS*2)) {
			hush = CIRCLE_RADIUS*2;
		}
		return hush;
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
	
	class clickedOnCrystal implements EventHandler {

		RotateTransition rotateTransition;
		
		public clickedOnCrystal (RotateTransition rotateTransition) {
			this.rotateTransition = rotateTransition;
		}
		
		
		@Override
		public void handle(Event arg0) {
			rotateTransition.stop();
			
			rotateTransition.setDuration(Duration.millis(rotateTransition.durationProperty().getValue().toMillis()*1.1));
		      rotateTransition.play(); 
		}

	}

	public void addHinweis(String string) {
		if (hinweis==null) {
			hinweis = new Label(string);
			super.grid.add(hinweis, 0, rowIndex);
			rowIndex++;
		} else {
			hinweis.setText(string);
		}
		
		 Timeline timeline = new Timeline();

         timeline.getKeyFrames().addAll(
                 new KeyFrame(Duration.ZERO,
                 new KeyValue(hinweis.opacityProperty(), 1.0)),
                 new KeyFrame(new Duration(2000), // set end position at 40s
                new KeyValue(hinweis.opacityProperty(), 0.0)));
         timeline.play();
	}
	
}