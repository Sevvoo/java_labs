package com.telegram;

import com.telegram.games.BoardGame;
import com.telegram.games.GameCollection;
import com.telegram.games.Player;
import com.telegram.games.StrategyGame;

public class LR6_1 {
    public static void main(String[] args) {
        System.out.println("=== Board Game Classes Demo ===\n");
        
        System.out.println("--- Player class ---");
        Player player1 = new Player();
        player1.showInfo();
        
        Player player2 = new Player("Alex", 25);
        player2.showInfo();
        player2.showInfo("Active");
        
        System.out.println("\n--- BoardGame class ---");
        BoardGame game1 = new BoardGame();
        game1.showInfo();
        
        BoardGame game2 = new BoardGame("Catan", 3, 4, 60);
        game2.showInfo(false);
        game2.showInfo(true);
        game2.play();
        
        System.out.println("\n--- StrategyGame class (inheritance) ---");
        StrategyGame game3 = new StrategyGame();
        game3.showInfo();
        
        StrategyGame game4 = new StrategyGame("Chess", 2, 2, 45, 8, "Abstract Strategy");
        game4.showInfo();
        game4.showInfo(true);
        game4.play();
        game4.play(player2);
        
        System.out.println("\n--- GameCollection class (composition) ---");
        GameCollection collection1 = new GameCollection();
        collection1.showInfo();
        
        GameCollection collection2 = new GameCollection("John");
        collection2.addGame(game2);
        collection2.addGame(game4);
        collection2.addPlayer(player1);
        collection2.addPlayer(player2);
        
        System.out.println();
        collection2.showInfo();
        collection2.listGames();
        
        System.out.println();
        collection2.showInfo(true);
    }
}
