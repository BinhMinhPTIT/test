package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    Game game;
    public KeyInput(Handler handler, Game game){
        this.handler = handler;

        this.game = game;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;

    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i=0;i<handler.ObjectList.size();i++){
            GameObject tempObject = handler.ObjectList.get(i);
            if(tempObject.getID()==ID.Player){
                if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; }
                if(key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[2] = true; }
                if(key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[3] = true; }
            }
        }
        if (key == KeyEvent.VK_P ){
            if (game.gameState == Game.STATE.Game){
                if(Game.paused) Game.paused = false;
                else Game.paused = true;
            }
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i=0;i<handler.ObjectList.size();i++){
            GameObject tempObject = handler.ObjectList.get(i);
            if(tempObject.getID()==ID.Player){
                if(key == KeyEvent.VK_W) keyDown[0]=false;
                if(key == KeyEvent.VK_S) keyDown[1]=false;
                if(key == KeyEvent.VK_A) keyDown[2]=false;
                if(key == KeyEvent.VK_D) keyDown[3]=false;

                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
}
