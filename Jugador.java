
package GUI;


public class Jugador {
    private String nombre;
    private int posicion;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.posicion = 1; 
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int nuevaPosicion) {
        posicion = nuevaPosicion;
    }
}


