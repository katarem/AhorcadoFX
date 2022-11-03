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
    private AnchorPane anclapanel;

    @FXML
    private ListView<String> scoreList;

    private ListProperty<String> scores = new SimpleListProperty<>(FXCollections.observableArrayList());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/PuntuacionView.fxml"));
            l.setController(this);
            l.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    
        InputStream scoresStream = getClass().getResourceAsStream("/scores.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(scoresStream));
		String line;
		try {
            while ((line = reader.readLine()) != null) {
            	scores.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        scoreList.itemsProperty().bind(scores);
        
    }

    public void add(String player, String score){
        scores.add(player + " " + score + "pts");
    }

    public AnchorPane getView(){
        return anclapanel;
    }

    public void guardar() throws IOException{ 
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/main/resources/palabras.txt")));
        int cont = 0;
        while(cont<scores.size()){
            bw.write(scores.get(cont) + "\n");
            cont++;
        }
        bw.close();
    }
    
}