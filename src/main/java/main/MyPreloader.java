package main;

import controller.LoginViewController;
import controller.RegisterViewViewController;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.DataBean;

public class MyPreloader extends Preloader {


    private Stage preloaderStage;
    private Scene scene;

    private Label progress;
    private ProgressBar pb;
    

    public MyPreloader() {
        // Constructor is called before everything.
       
    }

    @Override
    public void init() throws Exception {
        
    	
    	
        // If preloader has complex UI it's initialization can be done in MyPreloader#init
    	
    	
    	
    	
        Platform.runLater(() -> {
            Label title = new Label("World Wide Game!\nLoading, please wait...");
            title.setTextAlignment(TextAlignment.CENTER);
            progress = new Label("0%");
            pb = new ProgressBar(0.0);
            pb.setMinSize(300, 50);
            VBox root = new VBox(title, progress, pb);
            root.setAlignment(Pos.CENTER);
            
            scene = new Scene(root, Main.WIDTH, Main.HEIGHT);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        

        this.preloaderStage = primaryStage;

        // Set preloader scene and show stage.
        preloaderStage.setScene(scene);
        preloaderStage.show();
    }
    
    public void completed(DataBean dataBean) {
    	LoginViewController loginVC = new LoginViewController(dataBean);
		 loginVC.show();   
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        // Handle application notification in this point (see MyApplication#init).
        if (info instanceof ProgressNotification) {
            progress.setText(((ProgressNotification) info).getProgress() + "%");
            pb.setProgress(((ProgressNotification) info).getProgress()/100);
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        // Handle state change notifications.
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                // Called after MyPreloader#start is called.
                System.out.println("BEFORE_LOAD");
                break;
            case BEFORE_INIT:
                // Called before MyApplication#init is called.
                System.out.println("BEFORE_INIT");
                break;
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                System.out.println("BEFORE_START");

                preloaderStage.hide();
                break;
        }
    }
}
