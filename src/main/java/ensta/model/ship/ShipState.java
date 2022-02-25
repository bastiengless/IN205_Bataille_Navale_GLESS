package ensta.model.ship;

import ensta.model.ship.AbstractShip;
import ensta.util.ColorUtil;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState() {this.ship = null;this.struck = false;}

    public ShipState(AbstractShip ship) {
        this.ship = ship;
        this.struck = false;
    }

    public ShipState(AbstractShip ship, boolean struck) {
        this.ship = ship;
        this.struck = struck;
    }

    public void addStrike() {
        if (!this.struck) {
            this.struck = true;
            if (!(ship==null)) ship.addStrike();
        }
    }

    public boolean isStruck() { return struck; }

    // Add coloring in red
    public String toString() {
        if (struck) return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
        else return Character.toString(ship.getLabel());
    }

    public boolean isSunk() { return ship.isSunk(); }

    public AbstractShip getShip() { return ship; }
}