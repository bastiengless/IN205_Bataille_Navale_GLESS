package ensta.model.ship;
import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    /* Constructors */
    public Destroyer(Orientation or) {
        super("Destroyer", 'D', 2, or);
    }
    public Destroyer() {
        this(Orientation.EAST);
    }
    
}