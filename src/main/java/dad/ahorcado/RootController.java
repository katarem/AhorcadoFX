package dad.ahorcado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.RuntimeErrorException;

import dad.ahorcado.Palabras.PalabrasController;
import dad.ahorcado.Partida.PartidaController;
import dad.ahorcado.Puntuaciones.PuntuacionesController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController implements Initializable{
    
    @FXML
    TabPane rootPane;

    @FXML
    Tab Partida, Palabras, Puntuaciones;

    private PartidaController gc = new PartidaController();
    private PalabrasController pc = new PalabrasController();
    private PuntuacionesController sc = new PuntuacionesController();
    private SimpleStringProperty puntuacion, jugador;

    public RootController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/RootView.fxml"));
            l.setController(this);
            l.load();
        } catch (IOException e1) {
           throw new RuntimeErrorException(null);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Partida.setContent(gc.getView());
        Palabras.setContent(pc.getView());
        Puntuaciones.setContent(sc.getView());

        puntuacion = new SimpleStringProperty();
        puntuacion.bind(gc.getPointsProperty());
        jugador = new SimpleStringProperty();
        jugador.bind(gc.getPlayerProperty());
    }    

    public void cerrar() throws IOException{
        sc.add(jugador.getValue(), puntuacion.getValue());
        sc.guardar();
        pc.update();
    }
    public TabPane getView(){
        return rootPane;
    }

}
