package controller.menu;

import constants.LayoutConstants;
import model.DataBean;
import view.menu.ResultView;

public class ResultViewController extends MainViewsController {

	public ResultViewController(DataBean dataBean, Boolean gewonnen) {
		super(dataBean);
		super.view = new ResultView(LayoutConstants.DIMENSION);
		if (gewonnen) {
			((ResultView) view).setTitel("Gewonnen!");
		} else {
			((ResultView) view).setTitel("Verloren!");
		}
		((ResultView) view).setResults(dataBean.getResults(gewonnen));
		view.setData(dataBean.getLoggedInSpieler());
		addListener();
		
	}

}
