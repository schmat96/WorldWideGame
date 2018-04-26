package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;

import com.sun.javafx.application.LauncherImpl;
import controller.LoginViewVC;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;
import model.DataBean;
import view.ViewLogin;

public class Main extends Application {
	
	private DataBean dataBean;
	private int COUNT_LIMIT = 3;
	private int progressID = 1;
	
	private RandomProgress randomProgress;
	
	static final int WIDTH = 320;
    static final int HEIGHT = 568;
		
	public static void main(String[] args) {
		
		LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		dataBean.setPrimaryStage(primaryStage);
		LoginViewVC wwg = new LoginViewVC(dataBean);
	    wwg.show(); 
	    wwg.login("Kevin", "Welcome$18");
	}
	//Test
	@Override
	public void stop() throws Exception {
	    super.stop();
	    dataBean.close();
	}
	
	public void init() throws Exception {
		randomProgress = new RandomProgress(this);
		randomProgress.start();
		dataBean = new DataBean(this, WIDTH, HEIGHT);
		randomProgress.terminate();
		
    }
	

	public void notifyProgress() {
		double progress = (100 * progressID) / COUNT_LIMIT;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
		progressID++;

	}

	public void notifyFromRandomProgress() {
		COUNT_LIMIT++;
		notifyProgress();
	}
}


