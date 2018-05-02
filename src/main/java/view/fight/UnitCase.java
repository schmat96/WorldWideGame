package view.fight;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.unit.Charakter;

import java.net.URL;
import java.net.URLClassLoader;

public class UnitCase extends Case {

	private HBox iconHbox;
	private Charakter charakter;
	private Label hp;
	private Label mpText;
	private final ProgressBar pb;
	private final ProgressBar mpProgress;
	
	public UnitCase(BorderPane grid, Charakter charakter) {
		super(grid);

		this.charakter = charakter;
		this.getStyleClass().add("unitCase");
		
        Image image = new Image("resources/fire.jpg", 30, 30, false, false);
        
        Label name = new Label();

        name.setText(charakter.getName());
        name.setStyle("-fx-font: bold italic 12pt \"Arial\";");

        iconHbox = new HBox();
        VBox vBoxHP = new VBox();
        VBox vBoxMP = new VBox();
        
        iconHbox.getChildren().add(new ImageView(image));
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

	private void updateMPLabel() {
		mpText.setText(charakter.getProgressMpString());
		mpProgress.setProgress(charakter.getProgressMp());
	}
	
	private void updateHPLabel() {
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
		String pfad="";
		switch (this.charakter.getChoosenAbility().getAbilityArt()) {
		case Angriff:
			pfad="angriff.png";
			break;
		case BuffEinzeln:
			pfad="buff.png";
			break;
		case BuffGegner:
			break;
		case BuffGruppe:
			pfad="buff.png";
			break;
		case BuffSelf:
			pfad="buff.png";
			break;
		case Defense:
			pfad="Defense.png";
			break;
		case Heilung:
			pfad="heilung.png";
			break;
		case Raise:
			pfad="heilung.png";
			break;
		default:
			pfad="fire.jpg";
			break;
		
		}
		
		Image image = new Image("resources/"+pfad, 30, 30, false, false);
		iconHbox.getChildren().add(new ImageView(image));
		
	}
}
