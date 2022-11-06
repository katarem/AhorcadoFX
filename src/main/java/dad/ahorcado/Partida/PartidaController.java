package dad.ahorcado.Partida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PartidaController implements Initializable {

    @FXML
    private GridPane partidaView;

    @FXML
    private Label puntuacion, adivinar, letras, mensaje;

    @FXML
    private TextField input;

    @FXML
    private ImageView imagen;

    private int ahorcado = 1;
    private ArrayList<String> palabras = new ArrayList<>();
    private static String palabra;
    private boolean gameFinished = false;
    private SimpleStringProperty points, nombre;
    private  TextInputDialog t = new TextInputDialog();
    public PartidaController(){
        //Controller
        try {
                FXMLLoader l = new FXMLLoader(getClass().getResource("/PartidaView.fxml"));
                l.setController(this);
                l.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Cargamos las palabras
        loadWords();
        imagen.setImage(new Image(getClass().getResourceAsStream("/hangman/1.png")));
        //Bindeamos 

        points = new SimpleStringProperty();
        points.bind(puntuacion.textProperty());

        nombre = new SimpleStringProperty();
        nombre.bind(t.getEditor().textProperty());

    }

    // FUNCIONES

    public StringProperty getPointsProperty(){
        return points;
    }

    public StringProperty getPlayerProperty(){
        return nombre;
    }

    public boolean getFinished(){
        return gameFinished;
    }

    public void loadWords(){
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
        getRandom();
    }

    private void getRandom() {
        int random = (int)(Math.random()*palabras.size());
        palabra = palabras.get(random);
        String adivString = "";
        for (int i = 0; i < palabra.length(); i++) {
            adivString += "_ ";
        }
        letras.setText("");
        adivinar.setText(adivString);
    }

    @FXML
    public void adivinarLetra() throws IOException {

    if(!gameFinished){
        mensaje.setText("");
        if (input.getText().length()<1)
            mensaje.setText("Introduzca un carácter válido");
        else if(!Character.isLetter(input.getText().charAt(0))){
            mensaje.setText("Introduzca un carácter válido");
            input.setText("");
        }
        else{
            String guess = adivinar.getText().replace(" ", "");
            char intento = input.getText().charAt(0);
            input.setText("");
            if (letras.getText().indexOf(intento)==-1 && guess.indexOf(intento)==-1) {
                if (!(palabra.indexOf(intento) == -1)) {
                    int cont = 0;
                    char[] desglose = guess.toCharArray();
                    for (int i = 0; i < palabra.length(); i++) {
                        if (Character.toUpperCase(palabra.charAt(i))  == Character.toUpperCase(intento)) {
                            desglose[i] = intento;
                            cont++;
                        }
                    }
                    guess = "";
                    for (int i = 0; i < desglose.length - 1; i++) {
                        guess += desglose[i] + " ";
                    }
                    guess += desglose[desglose.length - 1];
                    String word = guess.replace(" ", "");
                    if(palabra.equals(word)){
                        adivinar.setText(palabra);
                        input.setText("");
                        letras.setText("");
                        mensaje.setText("Adivinaste! ahora viene otra");
                        checkwin(0);
                    }
                    else
                        adivinar.setText(guess);
                    try {
                        setPuntuacion(cont);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    letras.setText(letras.getText() + intento);
                    setPuntuacion(0);
                }
            } else
                mensaje.setText("Esa letra ya la intentaste");
            }
    }
    else
        mensaje.setText("El juego ya acabó");
}

    @FXML
    public void adivinarPalabra() throws IOException {
        
        if(!gameFinished){
        String intento = input.getText();
        String guess = adivinar.getText().replace(" ", "");
        
        if (palabra.equals(intento)) {
            try {
                setPuntuacion(10);
            } catch (IOException e) {
                e.printStackTrace();
            }
            char[] desglose = intento.toCharArray();
            for (int i = 0; i < desglose.length - 1; i++) {
                guess += desglose[i] + " ";
            }
            guess += desglose[desglose.length - 1];
            adivinar.setText(guess);
            checkwin(0);
        }
        else
            setPuntuacion(0);
    }
        else
            mensaje.setText("El juego ya acabó");
}

    private void setPuntuacion(int puntos) throws IOException {
        if(ahorcado==9)
            checkwin(1);
        else if (puntos == 0) {
            ahorcado++;
            imagen.setImage(new Image(String.format("/hangman/%d.png", ahorcado)));
        }
        input.setText("");
        puntos += Integer.parseInt(puntuacion.getText());
        puntuacion.setText(""+puntos);
    }

    public String getPuntos() {
        return puntuacion.getText();
    }

    private void checkwin(int cond) {
        if (cond == 1) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("PERDISTE");
            a.show();
            gameFinished = true;
            adivinar.setText("Y O U  L O S E");

            t.setTitle("Añadir puntuacion");
            t.setContentText("Escribe tu nombre: ");
            t.showAndWait();
        }
        else{
            getRandom();
        }
    }

    public GridPane getView(){
        return partidaView;
    }

}