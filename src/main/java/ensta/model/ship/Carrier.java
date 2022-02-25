package ensta.model.ship;
import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    /* Constructors */
    public Carrier(Orientation or) {
        super("Porte-avion", 'C', 5, or);
    }
    public Carrier() {
        this(Orientation.EAST);
    }
    
}