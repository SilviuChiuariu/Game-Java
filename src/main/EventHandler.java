package main;
public class EventHandler {
    GamePanel gp;
    EventRect [][][]eventRect;
    int  previousEventX, previousEventY;  //pt ca un event sa se intample dupa ce playerul se deplaseaza
    boolean canTouchEvent = true;
    public EventHandler(GamePanel gp){
        this.gp=gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col=0, row=0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();   //SE seteaza trigger point-ul in mijlocul blocului
            eventRect[map][col][row].x=23;
            eventRect[map][col][row].y=23;
            eventRect[map][col][row].width=2;
            eventRect[map][col][row].height=2;
            eventRect[map][col][row].eventRectDefaultX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY=eventRect[map][col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                col=0;
                row++;
                if(row == gp.maxWorldRow){    //pt a putea creea eventHandler in fiecare mapa
                    row = 0;
                    map++;
                }
            }
        }
    }
    public void checkEvent(){
        //Verifica daca playerul este mai departe de o casuta distanta de la ult event
        int xDistanta = Math.abs(gp.player.worldX - previousEventX);
        int yDistanta = Math.abs(gp.player.worldY - previousEventY);
        int distanta = Math.max(xDistanta, yDistanta);
        if(distanta > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true) {  //aici adaugam pt nou event
            if (hit(0,35,32,"up") == true) {damagePit(gp.dialogState);}  //DMG PIT
            else if (hit(0,43,39,"up") == true) {healingChurch(gp.dialogState); }  //HP CHR
            else if (hit(0,39,5,"any") == true) {teleport(1,10,49); }   //EVENT TRANZITIE MAPA 1->2
            else if (hit(1,57,21,"any") == true) {teleport(2,10,39); }   //EVENT TRANZITIE MAPA 2->3..
        }
    }
    //METODA CE DETECTEAZA COLIZIUNEA
    public boolean hit(int map, int col, int row, String regDirection){
        boolean hit=false;
        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    //DEMAGE PIT
    public void damagePit( int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialog="AI BAUT APA SI AI RUGINIT!";
        gp.player.life-=1;
        //eventRect[col][row].eventDone = true;  pt o sing data
        canTouchEvent = false;
    }
    //HEALING
    public void healingChurch( int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialog="AI FOST BINECUVANTAT cu VIATA.\n UA UA UAAAA";
            gp.player.life=gp.player.maxLife;
        }
    }
    public void teleport(int map, int col, int row){
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        //pt a avea delay
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }
}
