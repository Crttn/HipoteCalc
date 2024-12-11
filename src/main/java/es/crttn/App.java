package es.crttn;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    RootController rootController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        rootController = new RootController();

        primaryStage.setTitle("HipoteCalc");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
