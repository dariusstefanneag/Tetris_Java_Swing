package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private AreaOfTheGame gamearea;
    private GameForm gameform;
    private int score;

    public GameThread(AreaOfTheGame gamearea,GameForm gameform) {
        this.gamearea = gamearea;
        this.gameform=gameform;
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
            if(gamearea.gameIsFinished()){
                System.out.println("GAME OVER");
                break;
            }
            gamearea.setShapeToBackground();
            score=gamearea.clearLines();
            gameform.displayScore(score);
        }
    }

}
