package view.fight;


import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Case extends GridPane {
	
	private EventHandler<MouseEvent> mouseEvent;
	
	public Case(BorderPane grid) {
		super();
		this.setMinWidth(grid.getWidth()/2-10);
		this.getStyleClass().add("unitCase");
	}
	
	public void addSingleEventHandler(EventType<MouseEvent> mouseClicked, EventHandler<MouseEvent> handler) {
		if (mouseEvent != null) {
			this.removeEventHandler(mouseClicked, mouseEvent);
		}
		this.mouseEvent = handler;
		this.addEventHandler(mouseClicked, mouseEvent);
	}
	
	public void removeEventHandler() {
		if (mouseEvent != null) {
			this.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent);
		}
	}

}
