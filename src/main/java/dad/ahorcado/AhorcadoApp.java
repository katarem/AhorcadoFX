package dad.ahorcado;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    static void setRoot(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AhorcadoApp.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args){
        launch(args);
    }
}
