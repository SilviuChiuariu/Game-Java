package entity;

import main.GamePanel;

import java.util.Random;

public class npc_Nea_Vasile extends Entity {
    public npc_Nea_Vasile(GamePanel gp){
        super(gp);
        direction="down";
        speed=1;
        getNeaVasileImages();
        setDialouge();
    }

    public void getNeaVasileImages(){
        //UP
        up1 = setUp("/npc/nea_vasileM1",gp.tileSize,gp.tileSize);
        up2 = setUp("/npc/nea_vasileM2",gp.tileSize,gp.tileSize);
        up3 = setUp("/npc/nea_vasileM3",gp.tileSize,gp.tileSize);
        up4 = setUp("/npc/nea_vasileM4",gp.tileSize,gp.tileSize);
        up5 = setUp("/npc/nea_vasileM5",gp.tileSize,gp.tileSize);
        up6 = setUp("/npc/nea_vasileM6",gp.tileSize,gp.tileSize);
        up7 = setUp("/npc/nea_vasileM7",gp.tileSize,gp.tileSize);
        up8 = setUp("/npc/nea_vasileM8",gp.tileSize,gp.tileSize);
        //RIGHT
        right1 = setUp("/npc/nea_vasileM1",gp.tileSize,gp.tileSize);
        right2 = setUp("/npc/nea_vasileM2",gp.tileSize,gp.tileSize);
        right3 = setUp("/npc/nea_vasileM3",gp.tileSize,gp.tileSize);
        right4 = setUp("/npc/nea_vasileM4",gp.tileSize,gp.tileSize);
        right5 = setUp("/npc/nea_vasileM5",gp.tileSize,gp.tileSize);
        right6 = setUp("/npc/nea_vasileM6",gp.tileSize,gp.tileSize);
        right7 = setUp("/npc/nea_vasileM7",gp.tileSize,gp.tileSize);
        right8 = setUp("/npc/nea_vasileM8",gp.tileSize,gp.tileSize);
        //DOWN
        down1 = setUp("/npc/nea_vasileM1st",gp.tileSize,gp.tileSize);
        down2 = setUp("/npc/nea_vasileM2st",gp.tileSize,gp.tileSize);
        down3 = setUp("/npc/nea_vasileM3st",gp.tileSize,gp.tileSize);
        down4 = setUp("/npc/nea_vasileM4st",gp.tileSize,gp.tileSize);
        down5 = setUp("/npc/nea_vasileM5st",gp.tileSize,gp.tileSize);
        down6 = setUp("/npc/nea_vasileM6st",gp.tileSize,gp.tileSize);
        down7 = setUp("/npc/nea_vasileM7st",gp.tileSize,gp.tileSize);
        down8 = setUp("/npc/nea_vasileM8st",gp.tileSize,gp.tileSize);
        //LEFT
        left1 = setUp("/npc/nea_vasileM1st",gp.tileSize,gp.tileSize);
        left2 = setUp("/npc/nea_vasileM2st",gp.tileSize,gp.tileSize);
        left3 = setUp("/npc/nea_vasileM3st",gp.tileSize,gp.tileSize);
        left4 = setUp("/npc/nea_vasileM4st",gp.tileSize,gp.tileSize);
        left5 = setUp("/npc/nea_vasileM5st",gp.tileSize,gp.tileSize);
        left6 = setUp("/npc/nea_vasileM6st",gp.tileSize,gp.tileSize);
        left7 = setUp("/npc/nea_vasileM7st",gp.tileSize,gp.tileSize);
        left8 = setUp("/npc/nea_vasileM8st",gp.tileSize,gp.tileSize);
    }
    //COMPORTAMENTUL LUI NEA VASILE
    public void setAction() {
        if(onPath == true){       //o sa caute calea
            int goalCol = 39;
            int goalRow = 5;
            //int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            //int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);
        }else {
            actionLockCounter++;
            if (actionLockCounter == 120) {

                Random random = new Random();
                int i = random.nextInt(100) + 1;      //alegem un nr random de la 1 la 100
                if (i < 25)
                    direction = "up";
                if (i >= 25 && i < 50)
                    direction = "right";
                if (i >= 50 && i < 75)
                    direction = "left";
                if (i >= 75 && i < 100)
                    direction = "down";
                actionLockCounter = 0;
            }
        }
    }
    public void setDialouge(){                 //aici setam dialogul lui nea Vasile
        dialog[0]="Am fost invadati de treparasi";
        dialog[1]="Biserica ma tinere Biserica iti da viata";
        dialog[2]="Nici rachita nu-i ca pomul, nici treparasul \nnu-i ca omu";
        dialog[3]="Neam de neamul meu nu a baut apa.";
        dialog[4]="Te duc sa gasesti portalul spre Gura Vaii";
    }
    public void speak(){
        //pt a specifica
        super.speak();
        onPath = true; //cand vb ne urm
    }

}
