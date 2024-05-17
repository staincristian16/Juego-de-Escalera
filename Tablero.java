
package GUI;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Tablero extends JPanel {
    private Map<Integer, Integer> serpientes;
    private Map<Integer, Integer> escaleras;

    public Tablero() {
        serpientes = new HashMap<>();
        escaleras = new HashMap<>();
        inicializarSerpientesYEscaleras();
    }

    private void inicializarSerpientesYEscaleras() {
        
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int x = i * 50;
                int y = (9 - j) * 50; 
                g.drawRect(x, y, 50, 50);
                g.drawString(String.valueOf(i * 10 + j + 1), x + 20, y + 30);
            }
        }

        
        g.setColor(Color.GREEN);
        for (Map.Entry<Integer, Integer> entry : escaleras.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();
            Point startPoint = getPosicion(start);
            Point endPoint = getPosicion(end);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }

        
        g.setColor(Color.YELLOW);
        for (Map.Entry<Integer, Integer> entry : serpientes.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();
            Point startPoint = getPosicion(start);
            Point endPoint = getPosicion(end);
            drawSerpiente(g, startPoint, endPoint);
        }
    }

    private Point getPosicion(int numero) {
        int row = (numero - 1) / 10;
        int col = (numero - 1) % 10;
        int x = col * 50 + 25;
        int y = (9 - row) * 50 + 25; 
        return new Point(x, y);
    }

    private void drawSerpiente(Graphics g, Point start, Point end) {
        int[] xPoints = {start.x, (start.x + end.x) / 2, end.x};
        int[] yPoints = {start.y, (start.y + end.y) / 2, end.y};
        g.drawPolyline(xPoints, yPoints, 3);
    }
}

