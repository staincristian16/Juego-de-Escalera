
package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SerpientesYEscalerasGUI extends JFrame {
    private Tablero tablero;
    private Juego juego;
    private JButton btnLanzarDado;
    private JLabel lblEstado;
    private JLabel lblDado;
    private Map<Jugador, JLabel> jugadorPosiciones;

    public SerpientesYEscalerasGUI() {
        setTitle("Serpientes y Escaleras");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tablero = new Tablero();
        String[] nombresParticipantes = {"primer Jugador", "segundo Jugador", "tercer Jugador", "cuarto Jugador"};
        juego = new Juego(nombresParticipantes);

        btnLanzarDado = new JButton("Lanzar Dado");
        lblEstado = new JLabel("Bienvenidos a Serpientes y Escaleras");
        lblDado = new JLabel("Resultado del dado: ");
        jugadorPosiciones = new HashMap<>();

       
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new GridLayout(3, 1));
        panelControl.add(lblEstado);
        panelControl.add(lblDado);
        panelControl.add(btnLanzarDado);

       
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(nombresParticipantes.length, 1));
        for (String nombre : nombresParticipantes) {
            JLabel lblJugador = new JLabel(nombre + ": Posición 1");
            panelJugadores.add(lblJugador);
            jugadorPosiciones.put(juego.getJugadorPorNombre(nombre), lblJugador);
        }

        btnLanzarDado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado = juego.lanzarDado();
                if (resultado == -1) {
                    lblEstado.setText("El juego ya ha terminado.");
                    return;
                } else if (resultado == -2) {
                    Jugador ganador = juego.getJugadorActual();
                    lblEstado.setText("¡" + ganador.getNombre() + " ha ganado!");
                    int respuesta = JOptionPane.showConfirmDialog(null, ganador.getNombre() + " ha sido el ganador. ¿Quieres jugar de nuevo?", "el juego terminoo", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        juego.reiniciarJuego();
                        actualizarEstadoJugadores();
                        lblEstado.setText("Nuevo juego diviertete. Turno de " + juego.getJugadorActual().getNombre());
                    } else {
                        System.exit(0);
                    }
                    return;
                }

                Jugador jugadorActual = juego.getJugadorActual();
                lblDado.setText("el dado marco: " + resultado);
                lblEstado.setText("Turno de " + jugadorActual.getNombre() + ". Posición: " + jugadorActual.getPosicion());
                jugadorPosiciones.get(jugadorActual).setText(jugadorActual.getNombre() + ": Posición " + jugadorActual.getPosicion());
                tablero.repaint(); 
            }
        });

        add(tablero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);
        add(panelJugadores, BorderLayout.EAST);
    }

    private void actualizarEstadoJugadores() {
        for (Map.Entry<Jugador, JLabel> entry : jugadorPosiciones.entrySet()) {
            Jugador jugador = entry.getKey();
            JLabel lblJugador = entry.getValue();
            lblJugador.setText(jugador.getNombre() + ": Posición " + jugador.getPosicion());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SerpientesYEscalerasGUI().setVisible(true);
            }
        });
    }
}
