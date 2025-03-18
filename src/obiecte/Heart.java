package obiecte;
import entity.Entity;
import main.GamePanel;

public class Heart extends Entity {
    public Heart(GamePanel gp){
        super(gp);
        name="heart";
        image = setUp("/obiecte/full heart",gp.tileSize,gp.tileSize);
        image2 = setUp("/obiecte/half heart",gp.tileSize,gp.tileSize);
        image3 = setUp("/obiecte/empty heart",gp.tileSize,gp.tileSize);
    }
}
