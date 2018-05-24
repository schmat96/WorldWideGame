package view.menu;

import java.awt.Dimension;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Challenge;
import model.unit.Charakter;

public class ChooseView extends MenuViews {

	private Text scenetitle;
	private VBox vbox;
	
	private BorderPane borderPane;
	
	private FlowPane gridUnits;
	
	private BorderPane vboxConfirm;
	
	
	public ChooseView(Dimension dim) {
		super(dim);
		scenetitle = new Text("Challenges");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		vbox = new VBox();
		borderPane = new BorderPane();
		gridUnits = new FlowPane();
		hauptGrid.setCenter(vbox);
		gridUnits.setAlignment(Pos.CENTER);
		borderPane.setCenter(gridUnits);
		this.vbox.getChildren().add(scenetitle);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}

	@Override
	public void setFocusOfButton() {
		super.setFocusOfButton(super.getFooter().getSpecialButton());
	}
	
	public BorderPane addChallenge(Challenge challenge, int runden) {
		BorderPane vbox = new BorderPane();
		Label label = new Label(challenge.getName());
		vbox.setTop(label);
		Label label2 = new Label("Schwierigkeit: " + challenge.getSchwierigkeit());
		vbox.setCenter(label2);
		Label label3 = new Label("Anzahl Runden: " + runden);
		vbox.setRight(label3);
		vbox.setPadding(new Insets(10, 10, 10, 10)); 
		vbox.getStyleClass().add("ChallengeCase");
		vbox.setId("enabled");
		this.vbox.getChildren().add(vbox);
		return vbox;
	}
	
	public UnitCaseMenu addUnits(Charakter charakter) {
		UnitCaseMenu uc = new UnitCaseMenu(charakter);
		gridUnits.getChildren().add(uc);
		return uc;
	}
	
	public void prepareForChallenge() {
		hauptGrid.setCenter(borderPane);
	}
	
	public void prepareForChallengeView() {
		hauptGrid.setCenter(vbox);
	}
	

	public BorderPane addBackButton() {
		BorderPane vbox = new BorderPane();
		Label label = new Label("zurück");
		vbox.setCenter(label);
		vbox.setPadding(new Insets(10, 10, 10, 10)); 
		vbox.getStyleClass().add("Button");
		vbox.setId("enabled");
		borderPane.setTop(vbox);
		return vbox;
	}

	public void clearGrid() {
		vbox.getChildren().clear();	
	}

	public BorderPane addConfirmButton() {
		vboxConfirm = new BorderPane();
		Label label = new Label("confirm");
		vboxConfirm.setTop(label);
		vboxConfirm.setPadding(new Insets(10, 10, 10, 10)); 
		vboxConfirm.getStyleClass().add("Button");
		this.borderPane.setBottom(vboxConfirm);
		return vboxConfirm;
	}
	
	public void setConfirm(Boolean confirm) {
		if (!confirm) {
			vboxConfirm.setId("disabled");
		} else {
			vboxConfirm.setId("enabled");
		}
	}

}
