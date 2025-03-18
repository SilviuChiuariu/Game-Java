package obiecte;

import entity.Entity;
import main.GamePanel;

public class Obiect_Sword extends Entity {
    public Obiect_Sword(GamePanel gp){
        super(gp);
        name = "Spintecatorul";
        image = setUp("/obiecte/full heart",gp.tileSize,gp.tileSize);
        colision = true;

        //PT A MICSORA ZONA DE COLIZIUNE CU OBIECTELE
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        }
}

