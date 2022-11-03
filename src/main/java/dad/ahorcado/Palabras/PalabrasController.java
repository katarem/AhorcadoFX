package dad.ahorcado.Palabras;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PalabrasController implements Initializable{

    @FXML
    private GridPane palabrasView;

    @FXML
    private TextField input;

    @FXML
    private ListView<String> palabrasList;

    private ListProperty<String> palabras = new SimpleListProperty<>(FXCollections.observableArrayList());

    public PalabrasController(){
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/PalabrasView.fxml"));
            l.setController(this);
            l.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        InputStream palabrasStream = getClass().getResourceAsStream("/palabras.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(palabrasStream));
		String line;
		try {
            while ((line = reader.readLine()) != null) {
            	palabras.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        palabrasList.itemsProperty().bind(palabras);
    }
    
    public int getSize(){
        return palabras.size();
    }

    @FXML
    public void añadir(){
        String palabra = input.getText();
        palabras.add(palabra);
        try {
            update();
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    @FXML
    public void quitar(){
        String palabra = input.getText();
        palabras.remove(palabra);
        try {
            update();
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
    public void update() throws IOException{ 
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/main/resources/palabras.txt")));
        int cont = 0;
        while(cont<palabras.size()){
            bw.write(palabras.get(cont) + "\n");
            cont++;
        }
        bw.close();
    }
    
    public GridPane getView(){
        return palabrasView;
    }

}
