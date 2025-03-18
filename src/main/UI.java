package main;

import entity.Entity;
import obiecte.Heart;
import obiecte.Obiect_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage keyImage;       //IMAGINE CHEIE / SABIE
    BufferedImage heart_full, heart_half, heart_empty;
    public String currentDialog= "";
    public int commandNum=0;
    public int subState = 0;


    public void draw(Graphics2D g2) throws SQLException {           //pt a afisa string pe ecran cu obiectele
        if(gp.gameState == gp.playState) {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);   //afisam imaginea pe ecran
            g2.drawString("x" + gp.player.hasKey, 75, 65);    //afisam stringul pe ecran
        }
        //ETAPA A DOUA
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));   //ceva legat de npc
        this.g2=g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        //TITLE STATE
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState==gp.playState){
            drawPlayerLife();
        }
        //PAUSE STATE
        if(gp.gameState==gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOUGE STATE
        if(gp.gameState==gp.dialogState){
            drawPlayerLife();
            drawDialogState();
        }
        //OPTIONS STATE
        if(gp.gameState==gp.optionsState){
            drawOptionsScreen();
        }
        //GAME OVER STATE
        if(gp.gameState==gp.gameOverState){
            drawGameOverScreen();
        }
    }
    //PT OBIECTE
    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40); //pt stringul x0
        Obiect_Key key=new Obiect_Key(gp);
        keyImage=key.image;      //ALEGEM IMAGINEA

        //ETAPA A DOUA:
        //CREEM IMAGINEA PT VIATA PLAYERULUI
        Entity heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }
    //PT IMAGINEA VIETII PLAYERULUI
    public void drawPlayerLife(){
        //gp.player.life=3;
        int x=gp.tileSize*3;
        int y=gp.tileSize/2;
        int i=0;
        //DRAW MAX LIFE
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x+=gp.tileSize;
        }
        //RESET
        x=gp.tileSize*3;
        y=gp.tileSize/2;
        i=0;
        //DRAW CURRENT LIFE
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full,x ,y, null);
            }
            i++;
            x+=gp.tileSize;
        }
    }
    //TOT CE TINE DE DIALOG
    public void drawDialogState(){
        //WINDOW
        int x=gp.tileSize*2, y=gp.tileSize/2, width=gp.ScreenWidth - (gp.tileSize*4), height=gp.tileSize*3;
        drawSubWindow(x, y, width, height);

        //DIMENSIUNEA TEXTULUI SI LOCUL OCUPAT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        x+=gp.tileSize;
        y+=gp.tileSize;

        for(String line : currentDialog.split("\n")){     //pt a se afisa textul rand sus rand
            g2.drawString(line, x, y);
            y+=40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){  //pt dialog
        Color c=new Color(0, 0, 0,212);       //ultima valoare este pt transparenta
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c=new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    //TOT CE TINE DE PAUSE STATE
    public void drawPauseScreen(){
        String text="PAUSE";
        int x=getXforCenterdText(text);

        int y=gp.ScreenHeight/2;

        g2.drawString(text,x ,y);
    }
    //CALCUL PT POZITIONARE TEXT MIJLOC ECRAN
    public int getXforCenterdText(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.ScreenWidth/2 - length/2;
        return x;
    }
    //TOT CE TINE DE TITLE SCREEN
    public void drawTitleScreen() throws SQLException {
        g2.setColor(new Color(110,11,20));
        g2.fillRect(0,0, gp.ScreenWidth, gp.ScreenHeight);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
        String text="Gabur si Elixirul Vietii";
        int x=getXforCenterdText(text);
        int y=gp.tileSize*3;
        //SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text,x+5,y+5);
        //MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        //IMAGINE DE FUNDAL
        x=gp.ScreenWidth/2 - (gp.tileSize*2)/2;
        y+=gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);   //aici modifici pt imag
        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text="NEW SIMULARE";
        x=getXforCenterdText(text);
        y+=gp.tileSize*3.5;
        g2.setColor(Color.BLACK);
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">",x-gp.tileSize, y);
        }

        text="LOAD GAME";
        x=getXforCenterdText(text);
        y+=gp.tileSize;
        g2.setColor(Color.BLACK);
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if(commandNum == 1){      //DATA BASE
            g2.drawString(">",x-gp.tileSize, y);
            if(gp.keyH.enterPressed==true){
                gp.keyH.enterPressed=false;
            }
        }

        text="QUIT";
        x=getXforCenterdText(text);
        y+=gp.tileSize;
        g2.setColor(Color.BLACK);
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">",x-gp.tileSize, y);
        }
    }

    public void drawOptionsScreen(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(25F));

        //SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState){
            case 0: optionsTop(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_EndGameConfirmation(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }

    public void optionsTop(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text="Options";
        textX = getXforCenterdText(text);
        textY = gp.tileSize + frameY;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);

        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
        }

        // SAVE -FOLOSIND DATABASE
        textY += gp.tileSize;
        g2.drawString("SAVE", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                gp.savedb();      //aplam functie de save din gamepanel
            }
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=2;
                commandNum=0;
            }
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState=3;
                commandNum = 0;
            }
        }

        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX-25, textY);
        }

        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2  + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
    }
    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialog = "The change will take \neffect after restarting \nthe game.";

        for(String line: currentDialog.split("\n")){   //pt a face posibil enter in dialog
            g2.drawString(line, textX, textY+2);
            textY += 40;
        }
        //BACK
        textY =frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;
        //TITLE
        String text = "Control";
        textX = getXforCenterdText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Conf/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY);

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);

        //BACK
        textX = frameX +gp.tileSize;
        textY = frameY +gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState=0;
                commandNum = 3;
            }
        }
    }
    public void options_EndGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        currentDialog="Quit the game and \nreturn to the title screen?";
        for(String line: currentDialog.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //Yes
        String text="Yes";
        textX = getXforCenterdText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState=0;
                gp.gameState = gp.titleState;
            }
        }
        //No
        text="No";
        textX = getXforCenterdText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState=0;
                commandNum = 4;
            }
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));  //cand playerul moare ecranul se inegreste
        g2.fillRect(0,0,gp.ScreenWidth,gp.ScreenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90f));

        text="Game Over";
        // Shadow
        g2.setColor(Color.BLACK);
        x = getXforCenterdText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-5, y-5);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenterdText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">",x-40,y);
        }

        //Back to the title screen
        text = "Quit";
        x = getXforCenterdText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">",x-40,y);
        }
    }
}

