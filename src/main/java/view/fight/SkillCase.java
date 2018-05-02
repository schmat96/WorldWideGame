package view.fight;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Ability;

public class SkillCase extends Case {

	public SkillCase(BorderPane grid, Ability skill) {
		super(grid);
		
		Image image = new Image("resources/fire.jpg", 50, 50, false, false);
        Label name = new Label(skill.getName());
        name.setStyle("-fx-font: bold italic 12pt \"Arial\";");
        
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(new ImageView(image));
        hbox1.getChildren().add(name);
        
        this.add(hbox1,0,0);
	}

}
