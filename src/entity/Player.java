package entity;
import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;   //undem punem playerul pe ecran
    public final int screenY;
    //CATE OBIECTE ARE PLAYERUL:
    public int hasSword=0;
    public int hasKey=0;
    //CONSTRUCTOR PARAMETRI
    public Player(GamePanel gp ,KeyHandler keyH){
        super(gp);                                                  //chemam constructorul din super class

        this.keyH=keyH;

        screenX=gp.ScreenWidth/2-(gp.tileSize/2);                   //jumatatea ecranului
        screenY=gp.ScreenHeight/2-(gp.tileSize/2);

        solidArea=new Rectangle(8,16,32,32);     //pentru a face colisiunea posibila doar cu o parde a corpului
                                                                    //aici modificam
        solidAreaDefaultX=solidArea.x;                              //copii la variabilele initiale
        solidAreaDefaultY=solidArea.y;

        attackArea.width = 48;  //modificam in functie de rangeul armei
        attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){                 //poz initiale
        if(gp.currentMap==0) {
            worldX = gp.tileSize * 45;  //45
            worldY = gp.tileSize * 38;  //38
            speed = 10;
            direction = "down";
            //PLAYER STATUS
            maxLife = 6;
            life = maxLife;
            currentLife=life;
            dmg = 2;   //setam dmg playerului
        }
        else if(gp.currentMap==1){
            worldX = gp.tileSize * 10;  //45
            worldY = gp.tileSize * 49;  //38
            speed = 10;
            direction = "down";
            //PLAYER STATUS
            maxLife = 6;
            life = currentLife;
            dmg = 2;   //setam dmg playerului
        }else if(gp.currentMap==2){
            worldX = gp.tileSize * 10;  //45
            worldY = gp.tileSize * 39;  //38
            speed = 10;
            direction = "down";
            //PLAYER STATUS
            maxLife = 6;
            life = currentLife;
            dmg = 2;   //setam dmg playerului
        }
    }
    //METODE PENTRU RETRY CAND MORI
    public void setDefaultPositions(){
        worldX=gp.tileSize*45;  //45
        worldY=gp.tileSize*38;  //38
        direction="down";
    }
    public void restoreLife(){
        life=maxLife;
        invincible=false;
    }
    public void getPlayerImage(){
        //UP
        up1 = setUp("/player/Run1",gp.tileSize,gp.tileSize);
        up2 = setUp("/player/Run2",gp.tileSize,gp.tileSize);
        up3 = setUp("/player/Run3",gp.tileSize,gp.tileSize);
        up4 = setUp("/player/Run4",gp.tileSize,gp.tileSize);
        up5 = setUp("/player/Run5",gp.tileSize,gp.tileSize);
        up6 = setUp("/player/Run6",gp.tileSize,gp.tileSize);
        up7 = setUp("/player/Run7",gp.tileSize,gp.tileSize);
        up8 = setUp("/player/Run8",gp.tileSize,gp.tileSize);
        //RIGHT
        right1 = setUp("/player/Run1",gp.tileSize,gp.tileSize);
        right2 = setUp("/player/Run2",gp.tileSize,gp.tileSize);
        right3 = setUp("/player/Run3",gp.tileSize,gp.tileSize);
        right4 = setUp("/player/Run4",gp.tileSize,gp.tileSize);
        right5 = setUp("/player/Run5",gp.tileSize,gp.tileSize);
        right6 = setUp("/player/Run6",gp.tileSize,gp.tileSize);
        right7 = setUp("/player/Run7",gp.tileSize,gp.tileSize);
        right8 = setUp("/player/Run8",gp.tileSize,gp.tileSize);
        //DOWN
        down1 = setUp("/player/right1",gp.tileSize,gp.tileSize);
        down2 = setUp("/player/right2",gp.tileSize,gp.tileSize);
        down3 = setUp("/player/right3",gp.tileSize,gp.tileSize);
        down4 = setUp("/player/right4",gp.tileSize,gp.tileSize);
        down5 = setUp("/player/right5",gp.tileSize,gp.tileSize);
        down6 = setUp("/player/right6",gp.tileSize,gp.tileSize);
        down7 = setUp("/player/right7",gp.tileSize,gp.tileSize);
        down8 = setUp("/player/right8",gp.tileSize,gp.tileSize);
        //LEFT
        left1 = setUp("/player/right1",gp.tileSize,gp.tileSize);
        left2 = setUp("/player/right2",gp.tileSize,gp.tileSize);
        left3 = setUp("/player/right3",gp.tileSize,gp.tileSize);
        left4 = setUp("/player/right4",gp.tileSize,gp.tileSize);
        left5 = setUp("/player/right5",gp.tileSize,gp.tileSize);
        left6 = setUp("/player/right6",gp.tileSize,gp.tileSize);
        left7 = setUp("/player/right7",gp.tileSize,gp.tileSize);
        left8 = setUp("/player/right8",gp.tileSize,gp.tileSize);
    }
    public void getPlayerAttackImage(){
        //UP
        AtUp1=setUp("/player/AtacSus1",gp.tileSize,gp.tileSize*2);
        AtUp2=setUp("/player/AtacSus2",gp.tileSize,gp.tileSize*2);
        AtUp3=setUp("/player/AtacSus3",gp.tileSize,gp.tileSize*2);
        AtUp4=setUp("/player/AtacSus4",gp.tileSize,gp.tileSize*2);
        AtUp5=setUp("/player/AtacSus5",gp.tileSize,gp.tileSize*2);
        AtUp6=setUp("/player/AtacSus6",gp.tileSize,gp.tileSize*2);
        AtUp7=setUp("/player/AtacSus7",gp.tileSize,gp.tileSize*2);
        AtUp8=setUp("/player/AtacSus8",gp.tileSize,gp.tileSize*2);
        //DOWN
        AtDown1=setUp("/player/AtacJos1",gp.tileSize,gp.tileSize*2);
        AtDown2=setUp("/player/AtacJos2",gp.tileSize,gp.tileSize*2);
        AtDown3=setUp("/player/AtacJos3",gp.tileSize,gp.tileSize*2);
        AtDown4=setUp("/player/AtacJos4",gp.tileSize,gp.tileSize*2);
        AtDown5=setUp("/player/AtacJos5",gp.tileSize,gp.tileSize*2);
        AtDown6=setUp("/player/AtacJos6",gp.tileSize,gp.tileSize*2);
        AtDown7=setUp("/player/AtacJos7",gp.tileSize,gp.tileSize*2);
        AtDown8=setUp("/player/AtacJos8",gp.tileSize,gp.tileSize*2);
        //LEFT
        AtLeft1=setUp("/player/AtacSt1",gp.tileSize*2,gp.tileSize);
        AtLeft2=setUp("/player/AtacSt2",gp.tileSize*2,gp.tileSize);
        AtLeft3=setUp("/player/AtacSt3",gp.tileSize*2,gp.tileSize);
        AtLeft4=setUp("/player/AtacSt4",gp.tileSize*2,gp.tileSize);
        AtLeft5=setUp("/player/AtacSt5",gp.tileSize*2,gp.tileSize);
        AtLeft6=setUp("/player/AtacSt6",gp.tileSize*2,gp.tileSize);
        AtLeft7=setUp("/player/AtacSt7",gp.tileSize*2,gp.tileSize);
        AtLeft8=setUp("/player/AtacSt8",gp.tileSize*2,gp.tileSize);
        //RIGHT
        AtRight1=setUp("/player/AtacDr1",gp.tileSize*2,gp.tileSize);
        AtRight2=setUp("/player/AtacDr2",gp.tileSize*2,gp.tileSize);
        AtRight3=setUp("/player/AtacDr3",gp.tileSize*2,gp.tileSize);
        AtRight4=setUp("/player/AtacDr4",gp.tileSize*2,gp.tileSize);
        AtRight5=setUp("/player/AtacDr5",gp.tileSize*2,gp.tileSize);
        AtRight6=setUp("/player/AtacDr6",gp.tileSize*2,gp.tileSize);
        AtRight7=setUp("/player/AtacDr7",gp.tileSize*2,gp.tileSize);
        AtRight8=setUp("/player/AtacDr8",gp.tileSize*2,gp.tileSize);
    }
    public void update(){
        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.leftPressed) {
                direction = "left";
            }

            //Verifica daca exista coliziuni
            colisionOn = false;
            gp.cf.checkTile(this, gp.tileM.mapTileNr);
            gp.cf.checkTile(this, gp.tileM.mapTileNr3);
            gp.cf.checkTile(this, gp.tileM.mapTileNr5);
            gp.cf.checkTile(this, gp.tileM.mapTileNr6);

            //Verifica dac exista coliziuni cu obiecte
            int ObjIndex = gp.cf.checkObiecte(this, true);
            pickUpObject(ObjIndex);   //apel luare obiect

            //NPC COLIZIUNE
            int npcIndex = gp.cf.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            //MONSTRII COLIZIUNE
            int monstriiIndex = gp.cf.checkEntity(this, gp.monstrii);
            contactMonstrii(monstriiIndex);
            //CHECK EVENT
            gp.eHandler.checkEvent();

            //daca coliziunea este pe false ,player-ul se poate misca
            if (colisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;        //conditie pt a merge in sus
                        break;
                    case "down":
                        worldY += speed;        //jos
                        break;
                    case "left":
                        worldX -= speed;       //stanga
                        break;
                    case "right":
                        worldX += speed;       //dreapta
                        break;
                }
            }
            gp.keyH.enterPressed = false;   //PUS IN EP CU PIT DMG

            spriteCounter++;                //pentru a face imaginea sa se miste
            if (spriteCounter > 2) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 5;
                } else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 7;
                } else if (spriteNum == 7) {
                    spriteNum = 8;
                } else if (spriteNum == 8) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        //INVINCIBILITATEA DISPARE COAE
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible=false;
                invincibleCounter=0;
            }
        }
        if(life > maxLife){
            life = maxLife;
        }
        //pt game over ecran
        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum=-1;   //rez bug ,daca spamezi enter sa nu dai retry din greseala
        }
    }
    public void attacking(){
        spriteCounter++;
        int i=3;
        if(spriteCounter <= i)
            spriteNum=1;
        if(spriteCounter > i && spriteCounter <= i*2)
            spriteNum=2;
        //Salvam pozitiile initiale
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        //Ajustam worldxX/Y pt a detecta atacul
        switch (direction){
            case "up": worldY -= attackArea.height; break;
            case "down": worldY += attackArea.height; break;
            case "left": worldX -= attackArea.width; break;
            case "right": worldX += attackArea.width; break;
        }
        //atac area devine solid area
        solidArea.width=attackArea.width;
        solidArea.height=attackArea.height;

        //Verificam monstru coliziunea
        int monstriiIndex = gp.cf.checkEntity(this,gp.monstrii);
        damageMonster(monstriiIndex);

        //restauram vechile pozitii
        worldX=currentWorldX;
        worldY=currentWorldY;
        solidArea.width=solidAreaWidth;
        solidArea.height=solidAreaHeight;

        if(spriteCounter > i*2 && spriteCounter <= i*3)
            spriteNum=3;
        if(spriteCounter > i*3 && spriteCounter <= i*4)
            spriteNum=4;
        if(spriteCounter > i*4 && spriteCounter <= i*5)
            spriteNum=5;
        if(spriteCounter > i*5 && spriteCounter <= i*6)
            spriteNum=6;
        if(spriteCounter > i*6 && spriteCounter <= i*7)
            spriteNum=7;
        if(spriteCounter > i*7 && spriteCounter <= i*8)
            spriteNum=8;
        if(spriteCounter > i*8) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i){                   //aici modifici interactiunea cu obiectele
        if(i!=999){    //s-a gasit cel putin obiect
            String objectName=gp.obj[gp.currentMap][i].name;
            switch (objectName){
                case"Key":
                    hasKey++;
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case"Spintecatorul":
                    hasSword++;
                    gp.obj[gp.currentMap][i]=null;
                    break;
            }
        }
    }
    public void interactNPC(int i){                   //aici modifici interactiunea cu npc player
        if(gp.keyH.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogState;
                gp.npc[gp.currentMap][i].speak();                        //cand playerul interactioneaza cu npc se apeleaza functia speak
            } else {
                attacking = true;
            }
        }
    }
    public void contactMonstrii(int i){     //primeste playerul dmg
        if(i != 999){
            if(invincible == false && gp.monstrii[gp.currentMap][i].dying == false) {
                life -= 1;
                currentLife=life;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i){
        if(i!=999){
            if(gp.monstrii[gp.currentMap][i].invincible==false){      //MOFICAT PT A FACE POSIBILA ANIMATIA
                gp.monstrii[gp.currentMap][i].life-=dmg;                //modificat pt a creste dmg playerului
                gp.monstrii[gp.currentMap][i].invincible=true;
                gp.monstrii[gp.currentMap][i].dmgReaction();

                if(gp.monstrii[gp.currentMap][i].life <= 0){
                    gp.monstrii[gp.currentMap][i].dying = true;
                }
            }
        }
    }
    public void draw(Graphics2D g2){
        int tempScreenX=screenX;
        int tempScreenY=screenY;
        BufferedImage image=null;
        switch (direction){                          //sprite pt mers
            case "up":
                //ATTACK
                if(attacking==true){
                    tempScreenY=screenY - gp.tileSize;
                    if(spriteNum==1){ image=AtUp1; }
                    if(spriteNum==2){ image=AtUp2; }
                    if(spriteNum==3){ image=AtUp3; }
                    if(spriteNum==4){ image=AtUp4; }
                    if(spriteNum==5){ image=AtUp5; }
                    if(spriteNum==6){ image=AtUp6; }
                    if(spriteNum==7){ image=AtUp7; }
                    if(spriteNum==8){ image=AtUp8; }
                }
                //MOVE
                if(attacking==false){
                    if(spriteNum==1){ image=up1; }
                    if(spriteNum==2){ image=up2; }
                    if(spriteNum==3){ image=up3; }
                    if(spriteNum==4){ image=up4; }
                    if(spriteNum==5){ image=up5; }
                    if(spriteNum==6){ image=up6; }
                    if(spriteNum==7){ image=up7; }
                    if(spriteNum==8){ image=up8; }
                }
                break;
            case "down":
                //ATTACK
                if(attacking==true){
                    if(spriteNum==1){ image=AtDown1; }
                    if(spriteNum==2){ image=AtDown2; }
                    if(spriteNum==3){ image=AtDown3; }
                    if(spriteNum==4){ image=AtDown4; }
                    if(spriteNum==5){ image=AtDown5; }
                    if(spriteNum==6){ image=AtDown6; }
                    if(spriteNum==7){ image=AtDown7; }
                    if(spriteNum==8){ image=AtDown8; }
                }
                //MOVE
                if(attacking==false){
                    if(spriteNum==1){ image=down1; }
                    if(spriteNum==2){ image=down2; }
                    if(spriteNum==3){ image=down3; }
                    if(spriteNum==4){ image=down4; }
                    if(spriteNum==5){ image=down5; }
                    if(spriteNum==6){ image=down6; }
                    if(spriteNum==7){ image=down7; }
                    if(spriteNum==8){ image=down8; }
                }
                break;
            case "left":
                //ATTACK
                if(attacking==true){
                    tempScreenX=screenX - gp.tileSize;
                    if(spriteNum==1){ image=AtLeft1; }
                    if(spriteNum==2){ image=AtLeft2; }
                    if(spriteNum==3){ image=AtLeft3; }
                    if(spriteNum==4){ image=AtLeft4; }
                    if(spriteNum==5){ image=AtLeft5; }
                    if(spriteNum==6){ image=AtLeft6; }
                    if(spriteNum==7){ image=AtLeft7; }
                    if(spriteNum==8){ image=AtLeft8; }
                }
                //MOVE
                if(attacking==false){
                    if(spriteNum==1){ image=left1; }
                    if(spriteNum==2){ image=left2; }
                    if(spriteNum==3){ image=left3; }
                    if(spriteNum==4){ image=left4; }
                    if(spriteNum==5){ image=left5; }
                    if(spriteNum==6){ image=left6; }
                    if(spriteNum==7){ image=left7; }
                    if(spriteNum==8){ image=left8; }
                }
                break;
            case "right":
                //ATTACK
                if(attacking==true){
                    if(spriteNum==1){ image=AtRight1; }
                    if(spriteNum==2){ image=AtRight2; }
                    if(spriteNum==3){ image=AtRight3; }
                    if(spriteNum==4){ image=AtRight4; }
                    if(spriteNum==5){ image=AtRight5; }
                    if(spriteNum==6){ image=AtRight6; }
                    if(spriteNum==7){ image=AtRight7; }
                    if(spriteNum==8){ image=AtRight8; }
                }
                //MOVE
                if(attacking==false){
                    if(spriteNum==1){ image=right1; }
                    if(spriteNum==2){ image=right2; }
                    if(spriteNum==3){ image=right3; }
                    if(spriteNum==4){ image=right4; }
                    if(spriteNum==5){ image=right5; }
                    if(spriteNum==6){ image=right6; }
                    if(spriteNum==7){ image=right7; }
                    if(spriteNum==8){ image=right8; }
                }
                break;
        }
        if(invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));  //PT EFECT DE TRANS
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        //RESET EFECT DE TRANSP
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
