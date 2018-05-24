package view.fight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.unit.Charakter;
import model.unit.Enemy;

public class UnitHitBox extends ImageView {
		
	public UnitHitBox(Charakter charakter) {
		super(new Image("units/"+charakter.getImageSource()+"/alive.png",50, 50, true, false));
	}
	
	public UnitHitBox(Enemy enemy) {
		super(new Image("enemies/"+enemy.getImageSource()+"/alive.png",100, 100, true, false));
	}
}
