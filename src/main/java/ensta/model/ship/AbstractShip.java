package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {
    /* Attributes */
    private char label;
    private String name = "Abstract Ship";
    private int size;
    private Orientation orientation;
    private int strikeCount=0;

    /* Getters */
    public char getLabel() {return label;}
    public String getName() {return name;}
    public int getLength() {return size;}
    public Orientation getOrientation() {return orientation;}
    public int getStrikeCount() {return strikeCount;}

    /* Setters */
    public void setLabel(char newLabel) {label = newLabel;}
    public void setName(String newName) {name = newName;}
    public void setSize(int newSize) {size = newSize;}
    public void setOrientation(Orientation newOrientation) {orientation = newOrientation;}

    /* Constructors */
    public AbstractShip(String nom, char lbl, int taille, Orientation or) {
        name = nom;
        label = lbl;
        size = taille;
        orientation = or;
    }

    public void addStrike() { strikeCount += 1;}

    public boolean isSunk() { return strikeCount==size; }
    public int getX() {return 0;}
    public int getY() {return 0;}    
}