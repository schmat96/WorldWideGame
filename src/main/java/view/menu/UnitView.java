package view.menu;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.AbilityArt;
import model.Stats;

public class UnitView extends MenuViews {

	Label unitName;
	Label unitUniversum;

	private Text scenetitle;
	
	private VBox vbox;
	
	private BorderPane borderPane;
	
	
	public UnitView(Dimension dim, String name) {
		super(dim);
		scenetitle = new Text(name);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		vbox = new VBox();
		borderPane = new BorderPane();

		ScrollPane scrollBar = new ScrollPane(vbox);

		hauptGrid.setCenter(scrollBar);
		this.vbox.getChildren().add(scenetitle);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}
	
	public BorderPane addStat(String kurzeBesch, int wert, Stats stat) {
		BorderPane vbox = new BorderPane();
		Label label = new Label(stat+"");
		vbox.setLeft(label);
		Label label2 = new Label("Wert: " + wert);
		vbox.setRight(label2);
		vbox.setPadding(new Insets(10, 10, 10, 10)); 
		vbox.getStyleClass().add("stat");
		this.vbox.getChildren().add(vbox);
		return vbox;
	}
	
	public BorderPane addAbility(AbilityArt art, String Name, double d, int mana, String AttackPattern) {
		BorderPane vbox = new BorderPane();
		Label label = new Label(art+"");
		vbox.setTop(label);
		Label label2 = new Label(Name);
		vbox.setLeft(label2);
		Label label3 = new Label(Name);
		vbox.setCenter(label3);
		Label label4 = new Label("Mana: " +mana);
		vbox.setRight(label4);
		Label label5 = new Label("Attack Pattern: " +AttackPattern);
		vbox.setBottom(label5);
		vbox.setPadding(new Insets(10, 10, 10, 10)); 
		vbox.getStyleClass().add("ability");
		vbox.setMaxWidth(300);
		this.vbox.getChildren().add(vbox);
		return vbox;
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getUnitButton());
	}

}