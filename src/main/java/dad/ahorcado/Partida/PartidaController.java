package dad.ahorcado.Partida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private String palabra;
    private boolean gameFinished = false;

    public PartidaController(){
        //Controller
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("/PartidaView.fxml"));
            l.setController(this);
            l.load();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Cargamos las palabras
        loadWords();
        getRandom();
        //Bindeamos
        
        puntuacion.setText("Puntos: 0");

    }

    // FUNCIONES

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
    }

    private void getRandom() {
        palabra = palabras.get((int)Math.random()*palabras.size());
        String adivString = "";
        for (int i = 0; i < palabra.length(); i++) {
            adivString += "_ ";
        }
        adivinar.setText(adivString);
    }

    @FXML
    public void adivinarLetra() {
        mensaje.setText("");
        String guess = adivinar.getText().replace(" ", "");
        char intento = input.getText().charAt(0);
        input.setText("");
        if (!Character.isLetter(intento)) {
            mensaje.setText("Carácter no válido");
        } else if (guess.indexOf(intento) == -1) {
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
                    mensaje.setText("Adivinaste! ahora viene otra");
                    checkwin();
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
            }
        } else
            mensaje.setText("Esa letra ya la intentaste");
    }

    @FXML
    public void adivinarPalabra() {
        String intento = input.getText();
        String guess = adivinar.getText().replace(" ", "");
        
        //Comando para perder y hacer testing
        if(intento=="IWANNALOSE"){
            ahorcado=9;
            checkwin();
        }
        else if (palabra.equals(intento)) {
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
            checkwin();
        }
    }

    private void setPuntuacion(int puntos) throws IOException {
        if (puntos == 0) {
            ahorcado++;
            imagen.setImage(new Image(String.format("/hangman/%d.png", ahorcado)));
        }
        String[] a = puntuacion.getText().split(" ");
        puntos += Integer.parseInt(a[1]);
        puntuacion.setText("Puntos: " + puntos);
    }

    public String getPuntos() {
        String[] a = puntuacion.getText().split(" ");
        String puntos = a[1];
        return puntos;
    }

    private void checkwin() {
        if (ahorcado == 9) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("PERDISTE");
            a.show();
            gameFinished = true;
        }
        else
            getRandom();
        }

    public GridPane getView(){
        return partidaView;
    }

}