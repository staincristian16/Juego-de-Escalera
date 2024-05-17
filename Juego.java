
package GUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Juego {
    private Jugador[] jugadores;
    private int jugadorActual;
    private Random dado;
    private Map<Integer, Integer> serpientes;
    private Map<Integer, Integer> escaleras;
    private boolean juegoTerminado;

    public Juego(String[] nombresParticipantes) {
        jugadores = new Jugador[nombresParticipantes.length];
        for (int i = 0; i < nombresParticipantes.length; i++) {
            jugadores[i] = new Jugador(nombresParticipantes[i]);
        }
        jugadorActual = 0;
        dado = new Random();
        juegoTerminado = false;

       
        serpientes = new HashMap<>();
        escaleras = new HashMap<>();
        inicializarSerpientesYEscaleras();
    }

    private void inicializarSerpientesYEscaleras() {
        // Define las posiciones de las serpientes
        serpientes.put(16, 6);
        serpientes.put(47, 26);
        serpientes.put(49, 11);
        serpientes.put(56, 53);
        serpientes.put(62, 19);
        serpientes.put(64, 60);
        serpientes.put(87, 24);
        serpientes.put(93, 73);
        serpientes.put(95, 75);
        serpientes.put(98, 78);

        
        escaleras.put(1, 38);
        escaleras.put(4, 14);
        escaleras.put(9, 31);
        escaleras.put(21, 42);
        escaleras.put(28, 84);
        escaleras.put(36, 44);
        escaleras.put(51, 67);
        escaleras.put(71, 91);
        escaleras.put(80, 100);
    }

    public int lanzarDado() {
        if (juegoTerminado) return -1;

        int resultado = dado.nextInt(6) + 1;
        Jugador jugador = jugadores[jugadorActual];
        int nuevaPosicion = jugador.getPosicion() + resultado;

        
        if (nuevaPosicion > 100) {
            nuevaPosicion = 100;
        }

        
        if (serpientes.containsKey(nuevaPosicion)) {
            nuevaPosicion = serpientes.get(nuevaPosicion);
        } else if (escaleras.containsKey(nuevaPosicion)) {
            nuevaPosicion = escaleras.get(nuevaPosicion);
        }

        jugador.setPosicion(nuevaPosicion);

        if (nuevaPosicion == 100) {
            juegoTerminado = true;
            return -2; // Indica que hay un ganador
        }

        
        jugadorActual = (jugadorActual + 1) % jugadores.length;
        return resultado;
    }

    public Jugador getJugadorActual() {
        return jugadores[jugadorActual];
    }

    public Jugador getJugadorPorNombre(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    public void reiniciarJuego() {
        for (Jugador jugador : jugadores) {
            jugador.setPosicion(1);
        }
        jugadorActual = 0;
        juegoTerminado = false;
    }
}

