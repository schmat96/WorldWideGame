package view.fight;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;

import model.Ability;
import model.NumbersToDisplay;
import model.Spieler;
import model.Universum;
import model.unit.Charakter;
import model.unit.Enemy;

import view.menu.HeaderView;

public class MainFightView {
	protected HeaderView header;

	protected BorderPane hauptGrid;

	protected BorderPane grid;
	
	protected GridPane charsAndSkill;
	
	protected VBox enemyBar;
	
	protected ScrollPane charsAndSkillScrollable;

	protected Canvas fightScreen;
	
	protected Scene scene;

	protected Button backBtn;
	
	private Text scenetitle;
	
	private  ArrayList<NumbersToDisplay> numbersToDisplay;
	private Boolean running = false;

	private ShowNumbersThread showNumbersThread;

	public MainFightView(Dimension dim, ArrayList<NumbersToDisplay> numbersToDisplay) {
		header = new HeaderView();
		
		hauptGrid = new BorderPane();

		grid = new BorderPane();

		this.numbersToDisplay = numbersToDisplay;
		
		enemyBar = new VBox();
		charsAndSkill = new GridPane();
		fightScreen = new Canvas(200,300);
		grid.setTop(enemyBar);
		grid.setCenter(fightScreen);
		

		//grid.setHgrow(charsAndSkill, Priority.ALWAYS);
		
		charsAndSkillScrollable = new ScrollPane(charsAndSkill);
		charsAndSkillScrollable.setFitToWidth(true);
		
		grid.setBottom(charsAndSkillScrollable);
		charsAndSkillScrollable.setMaxHeight(190);
		//grid.add(charsAndSkillScrollable, 1, 1,1,1);
		
		fightScreen.setStyle("-fx-border-color: blue;");

		hauptGrid.setCenter(grid);
		hauptGrid.setTop(header);
				
		scenetitle = new Text("Fight");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
		scene.getStylesheets().add("resources/userCase.css");
		
		showNumbersThread = new ShowNumbersThread(numbersToDisplay, fightScreen);
		showNumbersThread.start();
	}

	public void show(Stage stage) {
		stage.setTitle("World Wide Game - Fight");
		stage.setScene(scene);
		stage.show();
	}
	

	public void setData(Spieler spieler) {
		this.header.setEnergie(spieler.getGeld());
		this.header.level("Level: " + spieler.getExp());
		this.header.spielerName(spieler.getName());
	}
	
	public UnitCase addUnitCase(int position, Charakter charakter) {
		UnitCase uc = new UnitCase(grid, charakter);
		charsAndSkill.add(uc, position%2, Math.round(position/2));
		return uc;
	}

	public HeaderView getHeader() {
		return this.header;
	}

	public void removeAllUnitAndSkills() {
		charsAndSkill.getChildren().clear();
		
	}

	public GridPane addBackCase() {
		GridPane back = new GridPane();
        Label name = new Label("back");
        name.setStyle("-fx-font: bold italic 12pt \"Arial\";");
        
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(name);
        back.add(hbox1,0,0);
        

        back.setStyle("-fx-border-color: black;");
        charsAndSkill.add(back, 0,0);
        return back;

		
	}

	public SkillCase addSkillCase(int zahl, Ability ability) {
		SkillCase uc = new SkillCase(grid, ability);
		charsAndSkill.add(uc, zahl%2, Math.round(zahl/2));
		return uc;
	}

	public void showGegner(Enemy gegner) {
		
		VBox hbox1 = new VBox();
        VBox vBoxHP = new VBox();

		
		final ProgressBar pb = new ProgressBar();
		
		pb.setStyle("orange-bar");
        pb.setProgress(gegner.getProgressHp());
        pb.setMaxHeight(15);
        pb.setPrefWidth(100000);
        pb.setStyle("-fx-accent: green;");
        Label hp = new Label(gegner.getProgressHpString());
        hp.setPrefWidth(100000);
        hp.setAlignment(Pos.CENTER);
        hp.setStyle("-fx-font: bold italic 7pt \"Arial\";");
        vBoxHP.getChildren().add(pb);
        vBoxHP.getChildren().add(hp);
        
        Label gegnerName = new Label(gegner.getName());
        gegnerName.setPrefWidth(100000);
        gegnerName.setAlignment(Pos.CENTER);
        gegnerName.setStyle("-fx-font: bold italic 12pt \"Arial\";");
        hbox1.getChildren().add(gegnerName);
        
        
        
        enemyBar.getChildren().add(vBoxHP);
        enemyBar.getChildren().add(hbox1);

		
		
	}

	public void removeGegner() {
		enemyBar.getChildren().clear();
		
	}

}
