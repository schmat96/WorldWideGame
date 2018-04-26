package controller.menu;

import model.DataBean;
import view.menu.SummonView;

public class SummonViewController extends MainViewsController {

	public SummonViewController(DataBean dataBean) {
		super(dataBean);
		super.view = new SummonView(dataBean.getDimension());
		addListener();

	}

	@Override
	protected void addListener() {
		super.addListener();
		

	}

	// +++++++++++++++++++++++++++++++++++++++++++++
	// Events
	// +++++++++++++++++++++++++++++++++++++++++++++

	

}
