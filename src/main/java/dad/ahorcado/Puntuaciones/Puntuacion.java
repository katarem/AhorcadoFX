package dad.ahorcado.Puntuaciones;

import javax.lang.model.util.ElementScanner14;

public class Puntuacion implements Comparable<Puntuacion>{
    private String nombre;
    private int puntos;

    public Puntuacion(String nombre, int puntos){
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public int getPuntos(){
        return this.puntos;
    }

    public String getUser(){
        return this.nombre;
    }

    public String toString(){
        return String.format("%s %d puntos", nombre,puntos);
    }

    @Override
    public int compareTo(Puntuacion p) {
        int salida;

        if(this.getPuntos()<p.getPuntos())
            salida = 1;
        else if(this.getPuntos()==p.getPuntos())
            salida = 0;
        else
            salida = -1;
        return salida;
    }

}
