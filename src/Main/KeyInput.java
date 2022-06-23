package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Main.Game.pause;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {

                if(key == KeyEvent.VK_UP) {tempObject.setVelY(-handler.speed); keyDown[0] = true;}
                if(key == KeyEvent.VK_DOWN) {tempObject.setVelY(handler.speed); keyDown[1] = true;}
                if(key == KeyEvent.VK_RIGHT) {tempObject.setVelX(handler.speed); keyDown[2] = true;}
                if(key == KeyEvent.VK_LEFT) {tempObject.setVelX(-handler.speed); keyDown[3] = true;}
            }
        }

        if(key == KeyEvent.VK_P) {

            if(game.gameState == Game.STATE.Game) {
                if(Game.pause) Game.pause = false;
                else Game.pause = true;
            }
        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
        if(key == KeyEvent.VK_SPACE) {
            if(Game.gameState == Game.STATE.Game) Game.gameState = Game.STATE.Shop;
            else if(Game.gameState == Game.STATE.Shop) Game.gameState = Game.STATE.Game;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {
                if(key == KeyEvent.VK_UP) keyDown[0] = false;
                if(key == KeyEvent.VK_DOWN) keyDown[1] = false;
                if(key == KeyEvent.VK_RIGHT) keyDown[2] = false;
                if(key == KeyEvent.VK_LEFT) keyDown[3] = false;

                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);

            }
        }

    }
}
