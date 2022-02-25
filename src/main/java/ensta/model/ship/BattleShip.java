package ensta.model.ship;
import ensta.util.Orientation;

public class BattleShip extends AbstractShip {
    /* Constructors */
    public BattleShip(Orientation or) {
        super("Croiseur", 'B', 4, or);
    }
    public BattleShip() {
        this(Orientation.EAST);
    }
    
}