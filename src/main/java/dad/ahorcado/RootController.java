package dad.ahorcado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.ahorcado.Palabras.PalabrasController;
import dad.ahorcado.Partida.PartidaController;
import dad.ahorcado.Puntuaciones.PuntuacionesController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;

public class RootController implements Initializable{
    
    @FXML
    TabPane rootPane;

    @FXML
    Tab Partida, Palabras, Puntuaciones;

    PartidaController gc = new PartidaController();
    PalabrasController pc = new PalabrasController();
    PuntuacionesController sc = new PuntuacionesController();

    public RootController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/RootView.fxml"));
            l.setController(this);
            l.load();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       

        Partida.setContent(gc.getView());
        Palabras.setContent(pc.getView());
        Puntuaciones.setContent(sc.getView());





        try {
            int palabras = pc.getSize();
                while(!gc.getFinished()){
                    if(pc.getSize()!=palabras){
                        pc.update();
                        gc.loadWords();
                        palabras = pc.getSize();
                    }
                }
            TextInputDialog a = new TextInputDialog();
            a.setTitle("NIGG");
            a.setHeaderText("pon nombre");
            a.showAndWait();
            sc.add(a.getEditor().getText(), gc.getPuntos());
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }    

    public TabPane getView(){
        return rootPane;
    }

}
