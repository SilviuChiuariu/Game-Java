package obiecte;
import entity.Entity;
import main.GamePanel;


public class Obiect_Key extends Entity {
    public Obiect_Key(GamePanel gp){
        super(gp);
        name = "Key";
        image = setUp("/obiecte/full heart",gp.tileSize,gp.tileSize);
    }
}
