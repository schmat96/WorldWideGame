package main;

import com.sun.javafx.application.LauncherImpl;

import constants.LayoutConstants;
import controller.LoginViewController;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;
import model.DataBean;

public class Main extends Application {
	
	private DataBean dataBean;
	private int COUNT_LIMIT = 7;
	private int progressID = 1;
	
	private RandomProgress randomProgress;

		
	public static void main(String[] args) {
		LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		dataBean.setPrimaryStage(primaryStage);
		LoginViewController wwg = new LoginViewController(dataBean);
	    wwg.show(); 
	    wwg.login("Kevin", "Welcome$18");
	}

	@Override
	public void stop() throws Exception {
	    super.stop();
	    dataBean.close();
	}
	
	public void init() throws Exception {
		randomProgress = new RandomProgress(this);
		randomProgress.start();
		dataBean = new DataBean(this);
		randomProgress.terminate();
    }
	

	public void notifyProgress(String progressMessage) {
		
		double progress = (100 * progressID) / COUNT_LIMIT;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
		progressID++;

	}

	public void notifyFromRandomProgress() {
		
		if (100/COUNT_LIMIT*progressID<100/COUNT_LIMIT*(progressID+1)) {
			COUNT_LIMIT++;
			notifyProgress("");
		}
		
		
	}
}


