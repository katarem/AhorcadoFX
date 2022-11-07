package dad.ahorcado.Puntuaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class PuntuacionesController implements Initializable{

    @FXML
    private AnchorPane scoreView;

    @FXML
    //private ListView<String> scoreList;
    private ListView<Puntuacion> scoreList;
    private ListProperty<Puntuacion> scores = new SimpleListProperty<>(FXCollections.observableArrayList());
    //private ListProperty<String> scores = new SimpleListProperty<>(FXCollections.observableArrayList());

    public PuntuacionesController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/PuntuacionView.fxml"));
            l.setController(this);
            l.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputStream scoresStream = getClass().getResourceAsStream("/scores.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(scoresStream));
		String line;
		try {
            while ((line = reader.readLine()) != null) {
                String[] a = line.split(",");
                System.out.println(line);
            	scores.add(new Puntuacion(a[0],Integer.parseInt(a[1])));
                //scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scoreList.itemsProperty().bind(scores);
        
    }

    public void add(String player, String score){
        scores.add(new Puntuacion(player, Integer.parseInt(score)));
       // scores.add(player + " " + score);
    }

    public AnchorPane getView(){
        return scoreView;
    }

    public void guardar() throws IOException{ 
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/main/resources/scores.txt")));
        int cont = 0;
        while(cont<scores.size()){
            bw.write(scores.get(cont) + "\n");
            cont++;
        }
        bw.close();
    }
    
}
