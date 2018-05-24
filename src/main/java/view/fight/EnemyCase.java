package view.fight;

import constants.LayoutConstants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.unit.Enemy;

public class EnemyCase extends HBox {
	
	private ProgressBar pbEnemy;
	
	private Label hpEnemy;
	
	
	EnemyCase(Enemy enemy) {
		super();
		
		VBox hbox1 = new VBox();
        VBox vBoxHP = new VBox();

        pbEnemy = new ProgressBar();
		
        pbEnemy.setStyle("orange-bar");
        pbEnemy.setProgress(enemy.getProgressHp());
        pbEnemy.setMaxHeight(15);
        pbEnemy.setPrefWidth(100000);
        pbEnemy.setStyle("-fx-accent: green;");

        hpEnemy = new Label(enemy.getProgressHpString());
        hpEnemy.setPrefWidth(100000);
        hpEnemy.setAlignment(Pos.CENTER);
        hpEnemy.setStyle("-fx-font: bold italic 7pt \"Arial\";");

        vBoxHP.getChildren().add(pbEnemy);
        vBoxHP.getChildren().add(hpEnemy);

        Label gegnerName = new Label(enemy.getName());
        gegnerName.setPrefWidth(100000);
        gegnerName.setAlignment(Pos.CENTER);
        gegnerName.setStyle("-fx-font: bold italic 12pt \"Arial\";");
        hbox1.getChildren().add(gegnerName);
        
        this.getChildren().add(vBoxHP);
        this.getChildren().add(hbox1);
	}


	public Label getHPEnemy() {
		return this.hpEnemy;
	}


	public ProgressBar getProgressEnemy() {
		return this.pbEnemy;
	}


	public void markSelected() {
		this.setStyle(LayoutConstants.STYLE_ELEMENTS_ON_FOCUSED);
		
	}
}
