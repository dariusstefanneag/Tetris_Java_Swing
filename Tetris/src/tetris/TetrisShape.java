package tetris;

import java.awt.Color;

public class TetrisShape {

    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] storedRotation;
    private int newShapeRotation;

    public TetrisShape(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        rotationShape();
    }

    private void rotationShape() {
        storedRotation = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int row = shape[0].length;
            int col = shape.length;

            storedRotation[i] = new int[col][row];
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    storedRotation[i][x][y] = shape[col - x - 1][y];
                }
            }
            shape = storedRotation[i];
        }
    }

    public void tetrisShapeSpawned(int gridWidth) {
        newShapeRotation = 0;
        shape = storedRotation[newShapeRotation];
        y = -getHeight();
        x = (gridWidth - getWidth()) / 2;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveShapeDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotate() {
        newShapeRotation++;
        if (newShapeRotation > 3) {
            newShapeRotation = 0;
        }
        shape = storedRotation[newShapeRotation];
    }

    public int getFloor() {
        return y + getHeight();
    }

    public int getRightFloor() {
        return x+getWidth();
    }

    public int getLeftFloor() {
        return x;
    }
}
