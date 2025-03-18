package main;

import entity.npc_Nea_Vasile;
import monstrii.MON_Ciuperca;
import monstrii.MON_Demon;
import monstrii.MON_Gherla;
import monstrii.MON_Treparas_Temp;
import obiecte.Obiect_Key;
import obiecte.Obiect_Sword;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObiect(){                  //implementa obiectele pe mapa
                                              //aici modificam cand vrem sa mai adaugam obiecte
        //MAPA1
        int mapNr = 0;
        int i=0;
        gp.obj[mapNr][i]=new Obiect_Key(gp);
        gp.obj[mapNr][i].worldX=45*gp.tileSize;
        gp.obj[mapNr][i].worldY=38*gp.tileSize;
        i++;
        gp.obj[mapNr][i]=new Obiect_Sword(gp);
        gp.obj[mapNr][i].worldX=45*gp.tileSize;
        gp.obj[mapNr][i].worldY=38*gp.tileSize;
        i++;
        //MAPA2
    }
    public void setNpc(){
        int mapNr = 0;
        int i=0;
        gp.npc[mapNr][i]=new npc_Nea_Vasile(gp);
        //SETAM POZITIILE PE MAPA A NPC
        gp.npc[mapNr][i].worldX=gp.tileSize*40;
        gp.npc[mapNr][i].worldY=gp.tileSize*35;
        i++;
    }

    public void setMonstrii(){
        //MAPA 1
        int mapNr = 0;
        int i=0;
        gp.monstrii[mapNr][i] = new MON_Treparas_Temp(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*39;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*35;
        i++;
        gp.monstrii[mapNr][i] = new MON_Treparas_Temp(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*41;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*35;
        i++;
        //MAPA2
        mapNr=1;
        gp.monstrii[mapNr][i] = new MON_Treparas_Temp(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*45;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*10;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*47;
        i++;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*47;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        gp.monstrii[mapNr][i] = new MON_Ciuperca(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*8;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*48;
        i++;
        gp.monstrii[mapNr][i] = new MON_Ciuperca(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*49;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        gp.monstrii[mapNr][i] = new MON_Ciuperca(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*50;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        gp.monstrii[mapNr][i] = new MON_Ciuperca(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*51;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        //MAPA3
        mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Demon(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*35;    //centrul
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*36;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*34;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*33;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*37;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*30;
        i++;
        mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*37;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*29;
        i++;
        mapNr=2;
        gp.monstrii[mapNr][i] = new MON_Gherla(gp);
        gp.monstrii[mapNr][i].worldX=gp.tileSize*37;
        gp.monstrii[mapNr][i].worldY=gp.tileSize*28;
        i++;
    }
}
