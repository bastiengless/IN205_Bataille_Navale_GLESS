package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.util.ColorUtil;
import ensta.model.ship.ShipState;

public class Board implements IBoard {
	private String name;
	private int size;
	private ShipState[] ships;
	private Boolean[] hits;

	private static final int DEFAULT_SIZE = 10;
	
	public Board() {
	}

	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		this.ships = new ShipState[size*size];
		for (int i=0; i<size*size; i++) {this.ships[i] = new ShipState(null);}
		this.hits = new Boolean[size*size];
	}

	public Board(String name) {
		this(name, DEFAULT_SIZE);
	}

	public void print() {
		System.out.println("Navires :                  Frappes :");
		
		/* Print the letters for the columns */
		String firstLine = "  ";
		for (int i = 0; i<this.size; i++) { firstLine += " " + (char)(65+i); }
		firstLine += "       ";
		for (int i = 0; i<this.size; i++) { firstLine += " " + (char)(65+i); }
		System.out.println(firstLine);

		/* Print every line */
		for (int i = 1; i<this.size+1; i++) {
			/* Print the Navires board, . if no ship and the label (ie S, D...) if there is a ship */
			System.out.print(Integer.toString(i));
			if (i<10) System.out.print(" ");
			for (int j = 0; j<this.size; j++) {
				if (this.ships[(i-1)*this.size + j]==null || this.ships[(i-1)*this.size + j].getShip()==null) System.out.print(" .");
				else System.out.print(" " + this.ships[(i-1)*this.size + j].toString());
			}

			/* Print the Frappes board, . if no info, X if wrong hit, red X if hit */
			System.out.print("     ");
			System.out.print(Integer.toString(i));
			if (i<10) System.out.print(" ");
			for (int j = 0; j<this.size; j++) {
				if (this.hits[(i-1)*this.size + j]==null) System.out.print(" .");
				else System.out.print(" " + (this.hits[(i-1)*this.size + j].booleanValue() ? ColorUtil.colorize("X", ColorUtil.Color.RED) : "X"));
			}

			System.out.print("\n");
		}
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	public boolean hasShip(Coords c) {
		return (!(ships[c.getX() + c.getY()*size].getShip() == null));
	}

	@Override
	public Hit sendHit(Coords c) {
		ShipState tile = ships[c.getX() + c.getY()*size];
		tile.addStrike();
		if (tile.getShip()==null) return Hit.MISS;
		else if (tile.getShip().isSunk()) {
			return Hit.fromInt(tile.getShip().getLength());
		}
		return Hit.STRIKE;
	}

	@Override
	public boolean getHit(Coords c) {
		return (!(hits[c.getX() + c.getY()*this.size]==null));
	}

	@Override
	public void setHit(boolean hit, Coords c) { hits[c.getX() + c.getY()*size] = hit; }

	@Override
	public boolean putShip(AbstractShip ship, Coords coords) {
		if (canPutShip(ship, coords)) {
			int direction = (ship.getOrientation()== Orientation.EAST || ship.getOrientation()== Orientation.WEST) ? 1 : size;
			int increment = ship.getOrientation().getIncrement();
			int deplacement = increment * direction;
			for (int i=0; i<ship.getLength(); i++) {
				ships[coords.getX()+coords.getY()*size  +  deplacement*i] = new ShipState(ship);
			}
			return true;
		}
		return false;
	}

	@Override
	public int getSize() {return size;}
}
