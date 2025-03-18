package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, leftPressed, downPressed, rightPressed, enterPressed;
    //DEBUG TIME
    public boolean checkDrawTime=false;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            try {
                titleState(code);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        //PLAY STATE
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //DIALOGUE STATE
        else if(gp.gameState==gp.dialogState){
            dialogueState(code);
        }
        //OPTIONS STATE
        else if(gp.gameState==gp.optionsState){
            optionsState(code);
        }
        //GAME OVER STATE
        else if(gp.gameState==gp.gameOverState){
            gameOverState(code);
        }
    }

    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }

    public void pauseState(int code){
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void titleState(int code) throws SQLException {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0)
                gp.ui.commandNum = 2;
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2)
                gp.ui.commandNum = 0;
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
            }
            if(gp.ui.commandNum == 1){             //PT DATA BASE
                gp.loaddb();   //apelam functia de load din gamepanel
                gp.gameState=gp.playState;
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }

    public void playState(int code){
        //WASD
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        //PAUSE
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed=true;
        }
        //OPTIUNI
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
        //DEBUG PT OPTIMIZARE
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }

    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;

        }

        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            //gp.playSE(9);
            if(gp.ui.commandNum < 0)
                gp.ui.commandNum = maxCommandNum;
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            //gp.playSE(9);
            if(gp.ui.commandNum > maxCommandNum)
                gp.ui.commandNum = 0;
        }
        //pt musica in viitor
    }

    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();

        if(code==KeyEvent.VK_W){
            upPressed=false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
    }
}
