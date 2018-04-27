package view;


import java.awt.Dimension;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.menu.FooterView;
import view.menu.HeaderView;


public class RegisterView {
	
	
	private PasswordField pwBox;
	
	private Scene scene;
	private TextField userTextField;
	private Button btnBack;
	private Button btnRegister;

	public RegisterView(Dimension dim) {
		
		HeaderView header = new HeaderView();
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane hauptGrid = new BorderPane();

		hauptGrid.setCenter(grid);
		hauptGrid.setTop(header);
		
		hauptGrid.setStyle("-fx-background-color: #CCC011;");
		
	     scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		btnRegister = new Button("Register");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnRegister);

		
		btnBack = new Button("Zurück");
		
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(btnBack);

		
		grid.add(hbBtn, 1, 4);
		
        
	}

	public void show(Stage stage) {
	      stage.setTitle("World Wide Game - Login");
	      stage.setScene(scene);
	      stage.show();
	   }
	
	public void closeView() {
		Platform.exit();
	}
	

	public Button getBtnBack() {
		return btnBack;
	}

	public Button getBtnRegister() {
		return btnRegister;
	}

	public PasswordField getPwBox() {
		return this.pwBox;
	}

	public TextField getUserTextField() {
		return userTextField;
	}
}
