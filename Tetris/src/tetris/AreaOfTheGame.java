package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import tetris.tetrisShapes.*;

public class AreaOfTheGame extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;
    // declaring coordonate for L shape
    private TetrisShape shape;
    private TetrisShape[] shapes;

    // method for calling a placeholder where i design it in gameForm
    public AreaOfTheGame(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        background = new Color[gridRows][gridColumns];
        shapes=new TetrisShape[]{new IShape(),new CubeShape(),new LShape(),new TShape(),new ZShape()};

    }

    public void setShapeToBackground() {
        int[][] tetrisShape = shape.getShape();
        Color colorShape = shape.getColor();
        int shapeHeight = shape.getHeight();
        int shapeWidth = shape.getWidth();
        int xPos = shape.getX();
        int yPos = shape.getY();

        for (int row = 0; row < shapeHeight; row++) {
            for (int col = 0; col < shapeWidth; col++) {
                if (tetrisShape[row][col] == 1) {
                    background[row + yPos][col + xPos] = colorShape;
                }
            }
        }

    }

    public void spawnShape() {
        Random randomShapes=new Random();
        shape =shapes[randomShapes.nextInt(shapes.length)];
        shape.tetrisShapeSpawned(gridColumns);
    }

    public void moveShapeLeft() {
        if(shape==null)return;
        if (!leftFloor()) {
            return;
        }
        shape.moveLeft();
        repaint();
    }

    public void moveShapeRight() {
          if(shape==null)return;
        if (!rightFloor()) {
            return;
        }
        shape.moveRight();
        repaint();
    }

    public void moveShapeFastDown() {
          if(shape==null)return;
        shape.moveShapeDown();
        repaint();
    }

    public void rotateShape() {
        shape.rotate();
        repaint();
    }

    public boolean moveShapeDown() {

        if (endFloor() == false) {
          
            return false;
        }
        shape.moveShapeDown();
        repaint();
        return true;
    }

    private boolean endFloor() {
        if (shape.getFloor() == gridRows) {
            return false;
        }
        int[][] tetrisShape = shape.getShape();
        int shapeWidth = shape.getWidth();
        int shapeHeight = shape.getHeight();
        for (int col = 0; col < shapeWidth; col++) {
            for (int row = shapeHeight - 1; row >= 0; row--) {

                if (tetrisShape[row][col] != 0) {
                    int xPos = col + shape.getX();
                    int yPos = row + shape.getY() + 1;
                    if (yPos < 0) {
                        break;
                    }
                    if (background[yPos][xPos] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean leftFloor() {
        if (shape.getLeftFloor() == 0) {
            return false;
        }
        int[][] tetrisShape = shape.getShape();
        int shapeWidth = shape.getWidth();
        int shapeHeight = shape.getHeight();
        for (int row = 0; row < shapeHeight; row++) {
            for (int col = 0; col < shapeWidth; col++) {

                if (tetrisShape[row][col] != 0) {
                    int xPos = col + shape.getX() - 1;
                    int yPos = row + shape.getY();
                    if (yPos < 0) {
                        break;
                    }
                    if (background[yPos][xPos] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean rightFloor() {
        if (shape.getRightFloor() == gridColumns) {
            return false;
        }
        int[][] tetrisShape = shape.getShape();
        int shapeWidth = shape.getWidth();
        int shapeHeight = shape.getHeight();
        for (int row = 0; row < shapeHeight; row++) {
            for (int col = shapeWidth - 1; col >= 0; col--) {

                if (tetrisShape[row][col] != 0) {
                    int xPos = col + shape.getX() + 1;
                    int yPos = row + shape.getY();
                    if (yPos < 0) {
                        break;
                    }
                    if (background[yPos][xPos] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
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

    private void drawShapeBackground(Graphics g) {
        Color color;
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridColumns; col++) {
                color = background[row][col];
                if (color != null) {
                    int x = col * gridCellSize;
                    int y = row * gridCellSize;
                    // setting the color red to the shape
                    g.setColor(color);
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
    public int clearLines(){
        boolean completedLine;
        int linesCompleted=0;
    for(int row=gridRows-1;row>=0;row--){
        completedLine=true;
            for(int col=0;col<gridColumns;col++){
                if(background[row][col]==null){
                    completedLine=false;
                    break;
                }
            }
            if(completedLine){
                linesCompleted++;
                clearLine(row);
                shiftDown(row);
                clearLine(0);
                row++;
                repaint();
            }
        }
    return linesCompleted;
    }
        private void clearLine(int row){
            
                for(int i=0;i<gridColumns;i++){
                    background[row][i]=null;
                }
        }
        private void shiftDown (int row){
            for(int i=row;i>0;i--){
                for(int x=0;x<gridColumns;x++){
                    background[i][x]=background[i-1][x];
                }
            }
        }
        
        public boolean gameIsFinished(){
            if(shape.getY()<0){
                shape=null;
                return true; 
            }
            return false;
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
        drawShapeBackground(g);
        drawShape(g);
    }
}
