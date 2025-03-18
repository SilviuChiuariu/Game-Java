package main;

import entity.Entity;

public class ColiziuneaFrate {

    GamePanel gp;
    public ColiziuneaFrate(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(Entity entity,int [][][]x) {        //functia de verificare
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;          //variabile cu coordonatele zonei solide a playerului
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;        //ce plm ???
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNr1, tileNr2;   //trb sa vreificam doar doua tile-uri ca sa vedem daca exista colisiuni

        switch (entity.direction){
            case"up":
                entityTopRow=(entityTopWorldY - entity.speed) / gp.tileSize;
                tileNr1=x[gp.currentMap][entityLeftCol][entityTopRow];
                tileNr2=x[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNr1].colision == true || gp.tileM.tile[tileNr2].colision==true){
                    entity.colisionOn=true;
                }
                break;
            case"down":
                entityBottomRow=(entityBottomWorldY + entity.speed) / gp.tileSize;   //ce plm???
                tileNr1=x[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNr2=x[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNr1].colision == true || gp.tileM.tile[tileNr2].colision==true){
                    entity.colisionOn=true;
                }
                break;
            case"left":
                entityLeftCol=(entityLeftWorldX - entity.speed) / gp.tileSize;   //ce plm???
                tileNr1=x[gp.currentMap][entityLeftCol][entityTopRow];
                tileNr2=x[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNr1].colision == true || gp.tileM.tile[tileNr2].colision==true){
                    entity.colisionOn=true;
                }
                break;
            case"right":
                entityRightCol=(entityRightWorldX + entity.speed) / gp.tileSize;   //ce plm???
                tileNr1=x[gp.currentMap][entityRightCol][entityTopRow];
                tileNr2=x[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNr1].colision == true || gp.tileM.tile[tileNr2].colision==true){
                    entity.colisionOn=true;
                }
                break;
        }
    }

    public int checkObiecte(Entity entity,boolean player){        //daca playerul loveste orice obiect returneaza indexul obiectului
        int index=999;
        for(int i=0;i<gp.obj[1].length;i++){

            if(gp.obj[gp.currentMap][i] != null){
                //gaseste solid area a entitatii
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                //gaseste solid area a obiectului
                gp.obj[gp.currentMap][i].solidArea.x=gp.obj[gp.currentMap][i].worldX+ gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y=gp.obj[gp.currentMap][i].worldY+ gp.obj[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;       //pozitia entitatii dupa ce se muta
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                }
                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                    if(gp.obj[gp.currentMap][i].colision==true){
                        entity.colisionOn=true;
                    }
                    if(player==true){
                        index=i;
                    }
                }
                entity.solidArea.x=entity.solidAreaDefaultX;         //resetam solidArea ,daca nu facem asta ea o sa tot creasca
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x=gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y=gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    //NPC sau TREPARASI
    public int checkEntity(Entity entity, Entity[][] target){
        int index=999;
        for(int i=0; i<target[1].length; i++){

            if(target[gp.currentMap][i] != null){
                //gaseste solid area a entitatii
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                //gaseste solid area a entity
                target[gp.currentMap][i].solidArea.x =target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y =target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;        //pozitia entitatii dupa ce se mutabreak;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                }
                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    if(target[gp.currentMap][i] != entity){
                        entity.colisionOn=true;
                        index=i;
                    }
                }
                entity.solidArea.x=entity.solidAreaDefaultX;         //resetam solidArea ,daca nu facem asta ea o sa tot creasca
                entity.solidArea.y=entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x=target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y=target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;
        //gaseste solid area a entitatii
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //gaseste solid area a entity
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;       //pozitia entitatii dupa ce se muta
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.colisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;         //resetam solidArea ,daca nu facem asta ea o sa tot creasca
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
