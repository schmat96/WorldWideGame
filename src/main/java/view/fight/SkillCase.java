package view.fight;

import constants.ImagesPreloader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Ability;

public class SkillCase extends Case {

	public SkillCase(BorderPane grid, Ability skill) {
		super(grid);
		
		
		
        Label name = new Label(skill.getName());
        name.setStyle("-fx-font: bold italic 12pt \"Arial\";");
        this.setId("enabled");
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(ImagesPreloader.getAbilityArtImages(skill.getAbilityArt()));
        hbox1.getChildren().add(name);
        
        HBox hboxMana = new HBox();
        Label mana = new Label("Manakosten: "+skill.getMana());
        hboxMana.getChildren().add(ImagesPreloader.getElementeArtImages(skill.getElement()));
        hboxMana.getChildren().add(mana);
     
        this.add(hbox1,0,0);
        this.add(hboxMana,0,1);
	}

	public void markDisabled() {
		this.setId("disabled");
	}

}
