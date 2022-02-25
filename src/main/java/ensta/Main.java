package ensta;

import ensta.controller.Game;
import ensta.model.Board;
import ensta.util.Orientation;
import ensta.model.ship.*;
import ensta.model.Coords;
import java.util.List;
import java.util.ArrayList;
import ensta.model.Player;
import ensta.model.Hit;
import ensta.ai.BattleShipsAI;

public class Main {

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void TestBoard() {
        Board b = new Board("My Board", 10);
        Board ob = new Board("Opponent Board", 10);
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new Carrier());
        Player moi = new Player(b, ob, ships);
        moi.putShips();
        Hit result1 = b.sendHit(new Coords(0, 0));
        b.print();
        Hit result2 = b.sendHit(new Coords(0, 1));
        b.print();
        System.out.println(result1.toString() + result2.toString() + ships.get(0).isSunk());
    }

    public static void TestGame() {
        Board board = new Board("Test Board");
        board.print();

        // Initialise la liste des bateaux
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new Carrier());

        // Initialise l'IA
        BattleShipsAI ai = new BattleShipsAI(board, board);
        AbstractShip[] shipsArr = new AbstractShip[5];
        for (int i = 0; i < 5; i++)
            shipsArr[i] = ships.get(i);
        ai.putShips(shipsArr);
        board.print();

        // Variables utilitaires
        int detruits = 0;
        Coords c  = new Coords();
        Hit h = Hit.MISS;
        boolean isDestroyed;
        int i = 0;

        while (detruits != 5) {
            System.out.println("Tour n°"+ (++i));
            
            h = ai.sendHit(c);
            board.print();

            isDestroyed = (h!=Hit.MISS && h!=Hit.STRIKE);
            if (isDestroyed) detruits += 1;
            System.out.println(h.toString() + ( isDestroyed ? " coulé !" : "." ) );
            sleep(450);
        }

        System.out.println("Fin du jeu !");
    }

    public static void TestFinal() {
        Game game = new Game();
        game.init();
        game.run();
    }

	public static void main(String args[]) {
        TestFinal();
    }

}
