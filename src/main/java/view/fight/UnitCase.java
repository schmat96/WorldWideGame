package view.fight;

import constants.CharacterStates;
import constants.ImagesPreloader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.unit.Charakter;

public class UnitCase extends Case {

	private HBox iconHbox;
	private Charakter charakter;
	private Label hp;
	private Label mpText;
	private final ProgressBar pb;
	private final ProgressBar mpProgress;
	
	private Pane abilityIcon;
	
	private Pane cancel;
	
	public UnitCase(BorderPane grid, Charakter charakter) {
		super(grid);

		this.charakter = charakter;
		
        Label name = new Label();

        name.setText(charakter.getName());
        name.setStyle("-fx-font: bold italic 12pt \"Arial\";");

        iconHbox = new HBox();
        VBox vBoxHP = new VBox();
        VBox vBoxMP = new VBox();
        
        iconHbox.getChildren().add(ImagesPreloader.getCharakterImages(charakter, CharacterStates.alive));
        iconHbox.getChildren().add(name);
		
		pb = new ProgressBar(0);
		
		pb.setStyle("orange-bar");
        pb.setProgress(1);
        pb.setMaxHeight(15);
        pb.setStyle("-fx-accent: green;");
        hp = new Label();
        updateHPLabel();
        hp.setStyle("-fx-font: bold italic 7pt \"Arial\";");
        vBoxHP.getChildren().add(hp);
        vBoxHP.getChildren().add(pb);
        
        mpProgress = new ProgressBar(1);
        mpProgress.setProgress(0.2);
        mpProgress.setMaxHeight(15);
        mpText = new Label();
        updateMPLabel();
        mpText.setStyle("-fx-font: bold italic 7pt \"Arial\";");
        
        vBoxMP.getChildren().add(mpText);
        vBoxMP.getChildren().add(mpProgress);
        
        this.add(iconHbox,0,0,3,1);
        this.add(vBoxHP,0,1,1,1);
        this.add(vBoxMP,2,1,2,1);
	}

	void updateMPLabel() {
		mpText.setText(charakter.getProgressMpString());
		mpProgress.setProgress(charakter.getProgressMp());
	}
	
	void updateHPLabel() {
		hp.setText(charakter.getProgressHpString());
		pb.setProgress(charakter.getProgressHp());
	}

	public void setOwnStyle(UnitCaseMarks endedTurn) {
		switch (endedTurn) {
		case canMove:
			this.setId("canMove");
			break;
		case dead:
			this.setId("dead");
			break;
		case endedTurn:
			this.setId("endedTurn");
			break;
		case markable:
			this.setId("markable");
			break;
		case abilityChoosen:
			this.setId("ability");
			addSkillImage();
			break;
		default:
			break;
		
		}
		
	}

	private void addSkillImage() {

		if (abilityIcon == null) {
			abilityIcon = new Pane(ImagesPreloader.getAbilityArtImages(this.charakter.getChoosenAbility().getAbilityArt()));
		} else {
			abilityIcon.getChildren().clear();
			abilityIcon.getChildren().add(ImagesPreloader.getAbilityArtImages( this.charakter.getChoosenAbility().getAbilityArt()));
			iconHbox.getChildren().remove(abilityIcon);	
		}
		abilityIcon.setId("markable");
		iconHbox.getChildren().add(abilityIcon);
	}

	public Pane setCancelAbilityButton() {
		if (cancel == null) {
			cancel = new Pane();
	        Image image = new Image("icons/"+"/cancel.png", 30, 30, false, true);
	        cancel.getChildren().add(new ImageView(image));
	        cancel.setId("markable");
		}
		iconHbox.getChildren().add(cancel);
        return cancel;
	}
	
	public void removeCancel() {
		if (cancel != null) {
			iconHbox.getChildren().remove(cancel);
		}
	}
	
	public Pane getAbilityIcon() {
		return this.abilityIcon;
	}
	
	public void removeAbilityIcon() {
		if (abilityIcon != null) {
			abilityIcon.getChildren().clear();
		}
	}
	
}
