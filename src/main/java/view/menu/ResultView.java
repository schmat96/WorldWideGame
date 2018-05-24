package view.menu;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import constants.Performance;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class ResultView extends MenuViews {

	private Label titel;
	private int rowIndex = 1;
	
	public ResultView(Dimension dim) {
		super(dim);
		titel = new Label();
		
		Performance.logPerformance();
		
		grid.add(titel, 0, 0);
		super.scene = new Scene(hauptGrid, dim.getWidth(), dim.getHeight());
	}
	
	public void setTitel(String titel) {
		this.titel.setText(titel);
	}

	@Override
	public void setFocusOfButton() {
		// TODO Auto-generated method stub

	}

	public void setResults(ArrayList<String> results) {
		Iterator<String> iter = results.iterator();
		while (iter.hasNext()) {
			String msg = iter.next();
			Label label = new Label(msg);
			grid.add(label, rowIndex, 0);
			rowIndex++;
		}
		
	}

}
