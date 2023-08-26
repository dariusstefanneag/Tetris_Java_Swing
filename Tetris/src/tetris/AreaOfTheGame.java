package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class AreaOfTheGame extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    public AreaOfTheGame(JPanel placeholder, int columns){
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        gridColumns=columns;
        gridCellSize=this.getBounds().width / gridColumns;
        gridRows=this.getBounds().height / gridCellSize;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int y =0;y<gridRows;y++){
            for (int x=0;x<gridColumns;x++){
                g.drawRect(x*gridCellSize,y*gridCellSize,gridCellSize,gridCellSize);
            }
        }
    }
}
