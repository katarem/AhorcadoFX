package dad.ahorcado.Puntuaciones;

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
        return String.format("%s %dpts", nombre,puntos);
    }

    @Override
    public int compareTo(Puntuacion p) {
        // TODO Auto-generated method stub
        return 0;
    }

}
