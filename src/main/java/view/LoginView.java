package view;


import java.awt.Dimension;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.menu.FooterView;
import view.menu.HeaderView;


public class LoginView {
	
	
	private PasswordField pwBox;
	
	private Scene scene;
	private TextField userTextField;
	private Button btnLogin;
	private Button register;

	public LoginView(Dimension dim) {
		
		HeaderView header = new HeaderView();
		FooterView footer = new FooterView();
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		BorderPane hauptGrid = new BorderPane();

		hauptGrid.setCenter(grid);
		hauptGrid.setTop(header);
		hauptGrid.setBottom(footer);
		
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
		
		btnLogin = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btnLogin);
		
		
		register = new Button("Register");
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(register);
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
	
	
	public Button getBtnLogin() {
		return btnLogin;
	}

	public Button getRegister() {
		return register;
	}

	public PasswordField getPwBox() {
		return this.pwBox;
	}

	public TextField getUserTextField() {
		return userTextField;
	}
}
