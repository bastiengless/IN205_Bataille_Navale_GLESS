package ensta.model.ship;
import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    /* Constructors */
    public Submarine(Orientation or) {
        super("Sous-marin", 'S', 3, or);
    }
    public Submarine() {
        this(Orientation.EAST);
    }
    
}