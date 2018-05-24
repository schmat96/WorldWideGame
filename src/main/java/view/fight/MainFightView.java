package view.fight;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import constants.LayoutConstants;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ability;
import model.Spieler;
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
	
	protected Pane imagesEnemyAndUnits; 
	
	protected Scene scene;

	protected Button backBtn;
	
	private Text scenetitle;
	

	private GridPane back;
	
	private HashMap<Integer, UnitCase> unitCase;
	private HashMap<Integer, SkillCase> skillCase;
	
	private ArrayList<UnitHitBox> uhbEnemies;
	private ArrayList<EnemyCase> enemyCases;

	private ShowNumbersThread showNumbersThread;

	public MainFightView(Dimension dim) {
		
		enemyCases = new ArrayList<EnemyCase>();
		
		header = new HeaderView();
		
		hauptGrid = new BorderPane();

		grid = new BorderPane();
		
		enemyBar = new VBox();
		charsAndSkill = new GridPane();
		imagesEnemyAndUnits = new Pane();
		
		BackgroundImage myBI= new BackgroundImage(new Image("backgrounds/gilgamesh.jpg",600,400,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);

		imagesEnemyAndUnits.setBackground(new Background(myBI));
		imagesEnemyAndUnits.setMaxSize(300,200);
		imagesEnemyAndUnits.setMinSize(300,200);
	    
	    
		fightScreen = new Canvas(200,300);
		imagesEnemyAndUnits.getChildren().add(imagesEnemyAndUnits.getChildren().size(), fightScreen);
		grid.setTop(enemyBar);
		grid.setCenter(imagesEnemyAndUnits);
		

		//grid.setHgrow(charsAndSkill, Priority.ALWAYS);
		
		charsAndSkillScrollable = new ScrollPane(charsAndSkill);
		charsAndSkillScrollable.setFitToWidth(true);
		charsAndSkillScrollable.setMinHeight(250);
		grid.setBottom(charsAndSkillScrollable);
		charsAndSkillScrollable.setMaxHeight(190);
		//grid.add(charsAndSkillScrollable, 1, 1,1,1);

		hauptGrid.setCenter(grid);
		hauptGrid.setTop(header);
				
		scenetitle = new Text("Fight");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
		scene.getStylesheets().add("css/userCase.css");
		
		showNumbersThread = new ShowNumbersThread(fightScreen);
		showNumbersThread.start();
		
		unitCase = new HashMap<Integer, UnitCase>();
		skillCase = new HashMap<Integer, SkillCase>();
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
	
	public UnitCase addUnitCase(Charakter charakter) {
		UnitCase uc = new UnitCase(grid, charakter);
		int position = charakter.getPosition();
		charsAndSkill.add(uc, position%2, Math.round(position/2));
		unitCase.put(position, uc);
		return uc;
	}

	public HeaderView getHeader() {
		return this.header;
	}

	public GridPane addBackCase() {
		back = new GridPane();
        Label name = new Label("back");
        name.setStyle("-fx-font: bold italic 12pt \"Arial\";");
        
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(name);
        back.add(hbox1,0,0);
        
        back.getStyleClass().add("Button");
        back.setId("enabled");
        charsAndSkill.add(back, 0,0);
        return back;

	}

	public SkillCase addSkillCase(int zahl, Ability ability) {
		SkillCase uc = new SkillCase(grid, ability);
		charsAndSkill.add(uc, zahl%2, Math.round(zahl/2));
		skillCase.put(zahl, uc);
		return uc;
	}
	
	public UnitHitBox addUnitHitBox(int position, Charakter charakter) {
		UnitHitBox uhb = new UnitHitBox(charakter);
		double XDisplay = LayoutConstants.GetPositionUnitX(position);
		double YDisplay = LayoutConstants.GetPositionUnitY(position);
		uhb.relocate(XDisplay, YDisplay);
		imagesEnemyAndUnits.getChildren().add(0,uhb);
		return uhb;
	}
	
	public UnitHitBox addGegnerHitBox(int position, Enemy Enemy) {
		UnitHitBox currently = new UnitHitBox(Enemy);
		double XDisplay = LayoutConstants.GetPositionEnemyX(position);
		double YDisplay = LayoutConstants.GetPositionEnemyY(position);
		currently.relocate(XDisplay, YDisplay);
		imagesEnemyAndUnits.getChildren().add(0,currently);
		uhbEnemies.add(currently);
		return currently;
	}
	
	public void clearGegnerHitBox() {
		if (uhbEnemies==null) {
			uhbEnemies = new ArrayList<UnitHitBox>();
		} else {
			Iterator<UnitHitBox> iterator = uhbEnemies.iterator();
			while (iterator.hasNext()) {
				UnitHitBox current = iterator.next();
				imagesEnemyAndUnits.getChildren().remove(current);
			}
		}
	}

	public EnemyCase showGegner(Enemy gegner) {
		EnemyCase ex = new EnemyCase(gegner);
		enemyCases.add(ex);
        enemyBar.getChildren().add(ex);
        return ex;

	}
	
	

	public void removeGegner() {
		enemyBar.getChildren().clear();
		
	}
	
	public ShowNumbersThread getShowNumbersThread() {
		return this.showNumbersThread;
	}

	public void removeSkillCase(int pos) {
		this.charsAndSkill.getChildren().remove(skillCase.get(pos));
	}
	
	public void removeBack() {
		this.charsAndSkill.getChildren().remove(back);
	}
	
	public UnitCase getUnitCase(int pos) {
		return unitCase.get(pos);
	}
	
	public SkillCase getSkillCase(int pos) {
		return skillCase.get(pos);
	}

	public void setUnitCaseVisible(boolean b) {
		for (HashMap.Entry<Integer, UnitCase> entry : unitCase.entrySet())
		{
		    entry.getValue().setVisible(b);
		}
		
	}

	public void removeAllSkillCases() {
		removeBack();
		for (HashMap.Entry<Integer, SkillCase> entry : skillCase.entrySet())
		{
		    charsAndSkill.getChildren().remove(entry.getValue());
		}
		skillCase.clear();
	}

	public HashMap<Integer, UnitCase> getUnitCase() {
		return this.unitCase;
		
	}

	public void removeAllUnitEventHandlers() {
		for (HashMap.Entry<Integer, UnitCase> entry : unitCase.entrySet())
		{
		    entry.getValue().removeEventHandler();
		}
	}

	public void updateMana(int position) {
		this.unitCase.get(position).updateMPLabel();
		
	}

	public void updateHp() {
		for (HashMap.Entry<Integer, UnitCase> entry : unitCase.entrySet())
		{
		    entry.getValue().updateHPLabel();
		}
	}

	public void updateMana() {
		for (HashMap.Entry<Integer, UnitCase> entry : unitCase.entrySet())
		{
		    entry.getValue().updateMPLabel();
		}
	}

	public void resetStyleEnemyCases() {
		Iterator<EnemyCase> iterator = enemyCases.iterator();
		while (iterator.hasNext()) {
			EnemyCase current = iterator.next();
			current.setStyle(null);
		}
	}
	
}
