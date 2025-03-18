package monstrii;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Treparas_Temp extends Entity {
    public MON_Treparas_Temp(GamePanel gp) {
        super(gp);

        type=2;
        name="Treparas Templier";
        speed=2;
        maxLife=6;
        life=maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage() {
        up1 = setUp("/monstrii/treparasi/Run1",gp.tileSize,gp.tileSize);
        up2 = setUp("/monstrii/treparasi/Run2",gp.tileSize,gp.tileSize);
        up3 = setUp("/monstrii/treparasi/Run3",gp.tileSize,gp.tileSize);
        up4 = setUp("/monstrii/treparasi/Run4",gp.tileSize,gp.tileSize);
        up5 = setUp("/monstrii/treparasi/Run5",gp.tileSize,gp.tileSize);
        up6 = setUp("/monstrii/treparasi/Run6",gp.tileSize,gp.tileSize);
        up7 = setUp("/monstrii/treparasi/Run5",gp.tileSize,gp.tileSize);
        up8 = setUp("/monstrii/treparasi/Run6",gp.tileSize,gp.tileSize);
        //RIGHT
        right1 = setUp("/monstrii/treparasi/Run1",gp.tileSize,gp.tileSize);
        right2 = setUp("/monstrii/treparasi/Run2",gp.tileSize,gp.tileSize);
        right3 = setUp("/monstrii/treparasi/Run3",gp.tileSize,gp.tileSize);
        right4 = setUp("/monstrii/treparasi/Run4",gp.tileSize,gp.tileSize);
        right5 = setUp("/monstrii/treparasi/Run5",gp.tileSize,gp.tileSize);
        right6 = setUp("/monstrii/treparasi/Run6",gp.tileSize,gp.tileSize);
        right7 = setUp("/monstrii/treparasi/Run5",gp.tileSize,gp.tileSize);
        right8 = setUp("/monstrii/treparasi/Run6",gp.tileSize,gp.tileSize);
        //DOWN
        down1 = setUp("/monstrii/treparasi/Run1st",gp.tileSize,gp.tileSize);
        down2 = setUp("/monstrii/treparasi/Run2st",gp.tileSize,gp.tileSize);
        down3 = setUp("/monstrii/treparasi/Run3st",gp.tileSize,gp.tileSize);
        down4 = setUp("/monstrii/treparasi/Run4st",gp.tileSize,gp.tileSize);
        down5 = setUp("/monstrii/treparasi/Run5st",gp.tileSize,gp.tileSize);
        down6 = setUp("/monstrii/treparasi/Run6st",gp.tileSize,gp.tileSize);
        down7 = setUp("/monstrii/treparasi/Run5st",gp.tileSize,gp.tileSize);
        down8 = setUp("/monstrii/treparasi/Run6st",gp.tileSize,gp.tileSize);
        //LEFT
        left1 = setUp("/monstrii/treparasi/Run1st",gp.tileSize,gp.tileSize);
        left2 = setUp("/monstrii/treparasi/Run2st",gp.tileSize,gp.tileSize);
        left3 = setUp("/monstrii/treparasi/Run3st",gp.tileSize,gp.tileSize);
        left4 = setUp("/monstrii/treparasi/Run4st",gp.tileSize,gp.tileSize);
        left5 = setUp("/monstrii/treparasi/Run5st",gp.tileSize,gp.tileSize);
        left6 = setUp("/monstrii/treparasi/Run6st",gp.tileSize,gp.tileSize);
        left7 = setUp("/monstrii/treparasi/Run5st",gp.tileSize,gp.tileSize);
        left8 = setUp("/monstrii/treparasi/Run6st",gp.tileSize,gp.tileSize);
    }
    public void setAction(){
        if(onPath == true){       //o sa caute playerul
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;
            if (actionLockCounter == 120) {

                Random random = new Random();
                int i = random.nextInt(100) + 1;      //alegem un nr random de la 1 la 100
                if (i < 25)
                    direction = "up";
                if (i >= 25 && i < 50)
                    direction = "down";
                if (i >= 50 && i < 75)
                    direction = "left";
                if (i >= 75 && i < 100)
                    direction = "right";
                actionLockCounter = 0;
            }
        }
    }
    public void dmgReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;   //fuge de tine
        onPath = true;
        if(life<=2){
            onPath=false;
            speed+=2;
            direction = gp.player.direction;
        }
    }
}
