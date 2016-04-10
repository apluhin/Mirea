package vk;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by aleksejpluhin on 09.04.16.
 */
public class ViewWin extends Application {

    public void start(Stage primaryStage) {
        // не исользуетсся

        final WebView view = new WebView();
        final WebEngine engine = view.getEngine();
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if(engine.getLocation().contains("token")) {
                    System.out.println(engine.getLocation());
                    Vk.accessToken = engine.getLocation();
                    return;

                }
            }
        });
        engine.load("https://oauth.vk.com/authorize?client_id=5095620&display=page&redirect_uri=http://vk.com&scope=friends&response_type=token&v=5.50");
        primaryStage.setScene(new Scene(view));
        primaryStage.show();


    }



}