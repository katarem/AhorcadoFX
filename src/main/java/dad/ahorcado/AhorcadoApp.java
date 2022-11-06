package dad.ahorcado;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application{
    RootController c = new RootController();
    

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ahorcado App");
        primaryStage.setScene(new Scene(c.getView()));
        primaryStage.setHeight(480);
        primaryStage.setWidth(710);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        c.cerrar();
    }

    public static void main(String[] args){
        launch(args);
    }
}
