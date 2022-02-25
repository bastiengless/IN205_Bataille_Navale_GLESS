package ensta.model;

import java.lang.Math;

public class Coords {
    private int x;
    private int y;
    
    public Coords() {x=0;y=0;}
    public Coords(Coords c) {x=c.getX();y=c.getY();}
    public Coords(int nx, int ny) {x=nx;y=ny;}
    public int getX() {return x;}
    public int getY() {return y;}

    public void setX(int nx) {x=nx;}
    public void setY(int ny) {y=ny;}
    public void setCoords(Coords c) {x=c.getX();y=c.getY();}

    public boolean isInBoard(int n) {
        return (x>=0 && y>=0 && x<n && y<n);
    }
    public static Coords randomCoords(int n) {
        Coords c = new Coords();
        c.setX((int)(Math.random()*n));
        c.setY((int)(Math.random()*n));
        return c;
    }


}