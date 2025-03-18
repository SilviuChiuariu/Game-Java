package entity;
import main.GamePanel;
import main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {             //clasa abstracta
    public GamePanel gp;
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8;
    public BufferedImage AtUp1,AtUp2,AtUp3,AtUp4,AtUp5,AtUp6,AtUp7,AtUp8;
    public BufferedImage AtDown1,AtDown2,AtDown3,AtDown4,AtDown5,AtDown6,AtDown7,AtDown8;
    public BufferedImage AtRight1,AtRight2,AtRight3,AtRight4,AtRight5,AtRight6,AtRight7,AtRight8;
    public BufferedImage AtLeft1,AtLeft2,AtLeft3,AtLeft4,AtLeft5,AtLeft6,AtLeft7,AtLeft8;
    public Rectangle solidArea=new Rectangle(0,0,48,48);  //setam solid area default la toate entitatile
    public Rectangle attackArea=new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    String []dialog= new String[50];      //DIALOGUL CU NPC-urile
    public BufferedImage image, image2, image3  ;
    public boolean colision=false;

    //STATE:
    public int worldX,worldY;
    public String direction = "down";
    public int spriteNum=1;
    int dialogIndex=0;
    public boolean colisionOn=false;      //pt colisiuni
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;  //pt death efect
    public boolean dying = false;  //pt death efect
    public boolean hpBarOn = false; //pt ca hp bar sa apar o per
    public boolean onPath =false;   //pt a putea urm playerul
    //COUNTER:
    public int invincibleCounter = 0;
    public int spriteCounter=0;
    public  int actionLockCounter=0;      //intervalul de miscare a NPCurilor
    public int dyingCounter=0;            //pt death efect
    public int hpBarCounter=0;            //pt ca hp bar sa apar o per

    //CHARACTER ATRIBUTES:
    public int maxLife;
    public int life;
    public int currentLife;

    public int type; // 0=player 1=nps 2=monstri
    public String name;
    public int speed;
    public int dmg;

    public Entity(GamePanel gp){
        this.gp=gp;
    }

    //METODA DE DRAW PT ENTITATI(FARA PLAYER)
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //pentru eficenta la randare(iti deseneaza casutele doar din screen)
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) { image = up1;}
                    if (spriteNum == 2) { image = up2;}
                    if (spriteNum == 3) { image = up3;}
                    if (spriteNum == 4) { image = up4;}
                    if (spriteNum == 5) { image = up5;}
                    if (spriteNum == 6) { image = up6;}
                    if (spriteNum == 7) { image = up7;}
                    if (spriteNum == 8) { image = up8;}
                    break;
                case "down":
                    if (spriteNum == 1) { image = down1;}
                    if (spriteNum == 2) { image = down2;}
                    if (spriteNum == 3) { image = down3;}
                    if (spriteNum == 4) { image = down4;}
                    if (spriteNum == 5) { image = down5;}
                    if (spriteNum == 6) { image = down6;}
                    if (spriteNum == 7) { image = down7;}
                    if (spriteNum == 8) { image = down8;}
                    break;
                case "left":
                    if (spriteNum == 1) { image = left1;}
                    if (spriteNum == 2) { image = left2;}
                    if (spriteNum == 3) { image = left3;}
                    if (spriteNum == 4) { image = left4;}
                    if (spriteNum == 5) { image = left5;}
                    if (spriteNum == 6) { image = left6;}
                    if (spriteNum == 7) { image = left7;}
                    if (spriteNum == 8) { image = left8;}
                    break;
                case "right":
                    if (spriteNum == 1) { image = right1;}
                    if (spriteNum == 2) { image = right2;}
                    if (spriteNum == 3) { image = right3;}
                    if (spriteNum == 4) { image = right4;}
                    if (spriteNum == 5) { image = right5;}
                    if (spriteNum == 6) { image = right6;}
                    if (spriteNum == 7) { image = right7;}
                    if (spriteNum == 8) { image = right8;}
                    break;
            }
            //MONSTRII HP BAR
            double oneScale = (double)gp.tileSize/maxLife;
            double hpBarValue = oneScale*life;
            if(type == 2 && hpBarOn == true) {

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2,9);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 7);
                hpBarCounter++;
                if(hpBarCounter > 300){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if(invincible==true){
                hpBarOn = true;  //apare hp bar
                hpBarCounter = 0;
                changeAlpha(g2,0.4f);  //PT EFECT DE TRANS
            }
            if(dying == true){       ////pt death efect
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            changeAlpha(g2,1f);
        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=5;
        if(dyingCounter <= i){ changeAlpha(g2,0);}
        if(dyingCounter > i && dyingCounter <=i*2){ changeAlpha(g2,1);}
        if(dyingCounter > i*2 && dyingCounter <=i*3){ changeAlpha(g2,0);}
        if(dyingCounter > i*3 && dyingCounter <=i*4){ changeAlpha(g2,1);}
        if(dyingCounter > i*4 && dyingCounter <=i*5){ changeAlpha(g2,0);}
        if(dyingCounter > i*5 && dyingCounter <=i*6){ changeAlpha(g2,1);}
        if(dyingCounter > i*6 && dyingCounter <=i*7){ changeAlpha(g2,0);}
        if(dyingCounter > i*7 && dyingCounter <=i*8){ changeAlpha(g2,1);}
        if(dyingCounter > i*8){
            dying=false;
            alive=false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue ){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    //NE FOLOSIM DE UTILiTY(FUNCTIE PT IMPLEMENTAREA IMAGINII)
    public BufferedImage setUp(String imagePath, int width, int height){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image=null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
    //PT NPC:
    public void setAction(){}       //au acelasi nume ca metodele din clasele derivate =>le contine propietatile
    public void dmgReaction(){}   //overwrite in clasele cu monstri
    public void speak(){
        if(dialog[dialogIndex] == null){
            dialogIndex=0;
        }
        gp.ui.currentDialog=dialog[dialogIndex];
        dialogIndex++;
        //se intoarce cu fata cand vb
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
           }
    }
    public void checkCollision(){
        colisionOn=false;
        gp.cf.checkTile(this,gp.tileM.mapTileNr);
        gp.cf.checkTile(this,gp.tileM.mapTileNr3);
        gp.cf.checkTile(this,gp.tileM.mapTileNr5);
        gp.cf.checkTile(this,gp.tileM.mapTileNr6);

        //coliziune cu obiectele
        gp.cf.checkObiecte(this ,false);

        //coliziune cu monstrii si entitati
        gp.cf.checkEntity(this, gp.npc); //cu NPC
        gp.cf.checkEntity(this,gp.monstrii);  //cu MONSTRII

        //coliziine dintre player si entity
        boolean contactPlayer = gp.cf.checkPlayer(this);

        //DACA ENTITY ESTE DE TIP 2 PLAYERUL IA DMG
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.player.life -= 1;
                gp.player.currentLife=life;
                gp.player.invincible = true;
            }
        }
    }

    //SE VA UTILIZA PENTRU TOATE NPC(nu trb scrisa in clasele derivate)
    public void update(){
        setAction();
        checkCollision();
        if(colisionOn==false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;                //pentru a face imaginea sa se miste
        if (spriteCounter > 5) {
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
        //MOBI DEVIN INVINCIBILI DUPA CE PRIM UN HIT
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
    public void searchPath(int goalCol, int goalRow){       //aici apelam metodele din PathFinder

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNode(startCol, startRow, goalCol, goalRow, this);

        if(gp.pFinder.search() == true){
            //Next worldX worldY
            int nextX = gp.pFinder.pathList.getFirst().col * gp.tileSize;
            int nextY = gp.pFinder.pathList.getFirst().row * gp.tileSize;

            //Entity solid area
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            //IMPLEMENTARE CA NPS SA NU RAMANA BLOCAT
            if(enTopY > nextY && enLeftX >= nextX && enRightX < (nextX + gp.tileSize)){
                direction="up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < (nextX + gp.tileSize)){
                direction="down";
            }
            else if(enTopY >= nextY && enBottomY < (nextY + gp.tileSize)){
                //left or right
                if(enLeftX > nextX){
                    direction="left";
                }
                if(enLeftX < nextX){
                    direction="right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //up or left
                direction="up";
                checkCollision();
                if(colisionOn == true){
                    direction="left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //up or right
                direction="up";
                checkCollision();
                if(colisionOn == true){
                    direction="right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                //down or left
                direction="down";
                checkCollision();
                if(colisionOn == true){
                    direction="left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                //down or right
                direction="down";
                checkCollision();
                if(colisionOn == true){
                    direction="right";
                }
            }
            //PT A OPRI ENTITATEA CAND AJUNGE LA PORTAL
           // int nextCol = gp.pFinder.pathList.get(0).col;
            //int nextRow = gp.pFinder.pathList.get(0).row;
            //if(nextCol == goalCol && nextRow == goalRow){
                //onPath = false;
            //}
        }
    }
}
