package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class AreaOfTheGame extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;

    // declaring coordonate for L shape
    private TetrisShape shape;

    // method for calling a placeholder where i design it in gameForm
    public AreaOfTheGame(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        spawnShape();
    }

    public void spawnShape() {
        shape = new TetrisShape(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.blue);
        shape.tetrisShapeSpawned(gridColumns);
    }

    public void moveShapeDown() {
        shape.moveShapeDown();
        repaint();
    }

    // method to create a shape
    private void drawShape(Graphics g) {
        int shapeHeight = shape.getHeight();
        int shapeWidth = shape.getWidth();
        Color colorShape = shape.getColor();
        int[][] tetrisShape = shape.getShape();
        int xPos = shape.getX();
        int yPos = shape.getY();
        // loop for rows
        for (int row = 0; row < shapeHeight; row++) {
            //loop for columns
            for (int column = 0; column < shapeWidth; column++) {
                if (tetrisShape[row][column] == 1) {
                    int x = (xPos + column) * gridCellSize;
                    int y = (yPos + row) * gridCellSize;

                    // setting the color red to the shape
                    g.setColor(colorShape);
                    // drawing the shape
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    // setting color black for the borders
                    g.setColor(Color.black);
                    // seting some borders to the shape
                    g.drawRect(x, y, gridCellSize, gridCellSize);
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
