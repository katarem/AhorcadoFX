package dad.ahorcado;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application{

    private static Scene scene;
    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ahorcado App");
        RootController c = new RootController();
        scene = new Scene(c.getView());
        primaryStage.setScene(scene);
        primaryStage.setHeight(480);
        primaryStage.setWidth(710);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
