package monstrii;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Demon extends Entity {
    public static final String monName = "Demon";
    public MON_Demon(GamePanel gp) {
        super(gp);
        type = 2;
        speed=2;
        name=monName;
        maxLife=30;
        life=maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage(){
        int i=5;
        //UP
        up1 = setUp("/monstrii/demon/run_1",gp.tileSize,gp.tileSize*i);
        up2 = setUp("/monstrii/demon/run_2",gp.tileSize,gp.tileSize*i);
        up3 = setUp("/monstrii/demon/run_3",gp.tileSize,gp.tileSize*i);
        up4 = setUp("/monstrii/demon/run_4",gp.tileSize,gp.tileSize*i);
        up5 = setUp("/monstrii/demon/run_5",gp.tileSize,gp.tileSize*i);
        up6 = setUp("/monstrii/demon/run_6",gp.tileSize,gp.tileSize*i);
        up7 = setUp("/monstrii/demon/run_7",gp.tileSize,gp.tileSize*i);
        up8 = setUp("/monstrii/demon/run_8",gp.tileSize,gp.tileSize*i);
        //RIGHT
        right1 = setUp("/monstrii/demon/run_1",gp.tileSize,gp.tileSize*i);
        right2 = setUp("/monstrii/demon/run_2",gp.tileSize,gp.tileSize*i);
        right3 = setUp("/monstrii/demon/run_3",gp.tileSize,gp.tileSize*i);
        right4 = setUp("/monstrii/demon/run_4",gp.tileSize,gp.tileSize*i);
        right5 = setUp("/monstrii/demon/run_5",gp.tileSize,gp.tileSize*i);
        right6 = setUp("/monstrii/demon/run_6",gp.tileSize,gp.tileSize*i);
        right7 = setUp("/monstrii/demon/run_7",gp.tileSize,gp.tileSize*i);
        right8 = setUp("/monstrii/demon/run_8",gp.tileSize,gp.tileSize*i);
        //DOWN
        down1 = setUp("/monstrii/demon/run_1st",gp.tileSize,gp.tileSize*i);
        down2 = setUp("/monstrii/demon/run_2st",gp.tileSize,gp.tileSize*i);
        down3 = setUp("/monstrii/demon/run_3st",gp.tileSize,gp.tileSize*i);
        down4 = setUp("/monstrii/demon/run_4st",gp.tileSize,gp.tileSize*i);
        down5 = setUp("/monstrii/demon/run_5st",gp.tileSize,gp.tileSize*i);
        down6 = setUp("/monstrii/demon/run_6st",gp.tileSize,gp.tileSize*i);
        down7 = setUp("/monstrii/demon/run_7st",gp.tileSize,gp.tileSize*i);
        down8 = setUp("/monstrii/demon/run_8st",gp.tileSize,gp.tileSize*i);
        //LEFT
        left1 = setUp("/monstrii/demon/run_1st",gp.tileSize,gp.tileSize*i);
        left2 = setUp("/monstrii/demon/run_2st",gp.tileSize,gp.tileSize*i);
        left3 = setUp("/monstrii/demon/run_3st",gp.tileSize,gp.tileSize*i);
        left4 = setUp("/monstrii/demon/run_4st",gp.tileSize,gp.tileSize*i);
        left5 = setUp("/monstrii/demon/run_5st",gp.tileSize,gp.tileSize*i);
        left6 = setUp("/monstrii/demon/run_6st",gp.tileSize,gp.tileSize*i);
        left7 = setUp("/monstrii/demon/run_7st",gp.tileSize,gp.tileSize*i);
        left8 = setUp("/monstrii/demon/run_8st",gp.tileSize,gp.tileSize*i);
    }
    public void update(){
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(onPath == false && tileDistance < 5) {       //te urmareste inamicul in range de 5 tile random

            int i = new Random().nextInt(100)+1;
            if(i > 50){
                onPath=true;
            }
        }
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
        if(life <= 6) {
            speed++;
        }
        if(life <= 5) {
            speed=1;
            gp.player.life=1;
        }
    }
}
