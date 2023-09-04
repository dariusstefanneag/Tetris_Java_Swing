package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private AreaOfTheGame gamearea;

    public GameThread(AreaOfTheGame gamearea) {
        this.gamearea = gamearea;
    }

    @Override
    public void run() {
        while(true){
            gamearea.spawnShape();
            while(gamearea.moveShapeDown()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
