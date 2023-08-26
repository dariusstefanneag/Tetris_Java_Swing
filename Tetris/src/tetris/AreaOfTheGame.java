package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AreaOfTheGame extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;

    // declaring coordonate for L shape
    private int[][] shape = {{1, 0}, {1, 0}, {1, 1}};

    // method for calling a placeholder where i design it in gameForm
    public AreaOfTheGame(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
    }

    // method to create a shape
    private void drawShape(Graphics g) {
        for (int x = 0; x < shape.length; x++) {
            for (int y = 0; y < shape[0].length; y++) {
                if (shape[x][y] == 1) {
                    // setting the color red to the shape
                    g.setColor(Color.red);
                    // drawing the shape
                    g.fillRect(y * gridCellSize, x * gridCellSize, gridCellSize, gridCellSize);
                    // setting color black for the borders
                    g.setColor(Color.black);
                    // seting some borders to the shape
                    g.drawRect(y * gridCellSize, x * gridCellSize, gridCellSize, gridCellSize);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // setting the grid cells for better visualization
        for (int y = 0; y < gridRows; y++) {
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
            }
        }
        drawShape(g);
    }
}
