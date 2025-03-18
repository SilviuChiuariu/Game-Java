package monstrii;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Gherla extends Entity {
    public MON_Gherla(GamePanel gp) {
        super(gp);
        type = 2;
        name="Gherla";
        speed=4;
        maxLife=4;
        life=maxLife;
        solidArea.x = 2;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage(){
        //UP
        up1 = setUp("/monstrii/Gherla/run1 dr",gp.tileSize,gp.tileSize*5);
        up2 = setUp("/monstrii/Gherla/run2 dr",gp.tileSize,gp.tileSize+24);
        up3 = setUp("/monstrii/Gherla/run3 dr",gp.tileSize,gp.tileSize+24);
        up4 = setUp("/monstrii/Gherla/run4 dr",gp.tileSize,gp.tileSize+24);
        up5 = setUp("/monstrii/Gherla/run5 dr",gp.tileSize,gp.tileSize+24);
        up6 = setUp("/monstrii/Gherla/run6 dr",gp.tileSize,gp.tileSize+24);
        up7 = setUp("/monstrii/Gherla/run7 dr",gp.tileSize,gp.tileSize+24);
        up8 = setUp("/monstrii/Gherla/run8 dr",gp.tileSize,gp.tileSize+24);
        //RIGHT
        right1 = setUp("/monstrii/Gherla/run1 dr",gp.tileSize,gp.tileSize+24);
        right2 = setUp("/monstrii/Gherla/run2 dr",gp.tileSize,gp.tileSize+24);
        right3 = setUp("/monstrii/Gherla/run3 dr",gp.tileSize,gp.tileSize+24);
        right4 = setUp("/monstrii/Gherla/run4 dr",gp.tileSize,gp.tileSize+24);
        right5 = setUp("/monstrii/Gherla/run5 dr",gp.tileSize,gp.tileSize+24);
        right6 = setUp("/monstrii/Gherla/run6 dr",gp.tileSize,gp.tileSize+24);
        right7 = setUp("/monstrii/Gherla/run7 dr",gp.tileSize,gp.tileSize+24);
        right8 = setUp("/monstrii/Gherla/run8 dr",gp.tileSize,gp.tileSize+24);
        //DOWN
        down1 = setUp("/monstrii/Gherla/run1 st",gp.tileSize,gp.tileSize+24);
        down2 = setUp("/monstrii/Gherla/run2 st",gp.tileSize,gp.tileSize+24);
        down3 = setUp("/monstrii/Gherla/run3 st",gp.tileSize,gp.tileSize+24);
        down4 = setUp("/monstrii/Gherla/run4 st",gp.tileSize,gp.tileSize+24);
        down5 = setUp("/monstrii/Gherla/run5 st",gp.tileSize,gp.tileSize+24);
        down6 = setUp("/monstrii/Gherla/run6 st",gp.tileSize,gp.tileSize+24);
        down7 = setUp("/monstrii/Gherla/run7 st",gp.tileSize,gp.tileSize+24);
        down8 = setUp("/monstrii/Gherla/run8 st",gp.tileSize,gp.tileSize+24);
        //LEFT
        left1 = setUp("/monstrii/Gherla/run1 st",gp.tileSize,gp.tileSize+24);
        left2 = setUp("/monstrii/Gherla/run2 st",gp.tileSize,gp.tileSize+24);
        left3 = setUp("/monstrii/Gherla/run3 st",gp.tileSize,gp.tileSize+24);
        left4 = setUp("/monstrii/Gherla/run4 st",gp.tileSize,gp.tileSize+24);
        left5 = setUp("/monstrii/Gherla/run5 st",gp.tileSize,gp.tileSize+24);
        left6 = setUp("/monstrii/Gherla/run6 st",gp.tileSize,gp.tileSize+24);
        left7 = setUp("/monstrii/Gherla/run7 st",gp.tileSize,gp.tileSize+24);
        left8 = setUp("/monstrii/Gherla/run8 st",gp.tileSize,gp.tileSize+24);
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
        if(life <= 2) {
            speed++;
        }
    }
}
