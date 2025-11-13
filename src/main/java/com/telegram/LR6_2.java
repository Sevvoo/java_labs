package com.telegram;

import com.telegram.games.BoardGame;
import com.telegram.games.GameCollection;
import com.telegram.games.Player;
import com.telegram.games.StrategyGame;

public class LR6_2 {
    public static void main(String[] args) {
        System.out.println("=== Demonstrating overloaded and overridden methods ===\n");
        
        System.out.println("--- Method overloading in Player ---");
        Player p1 = new Player("Mike", 30);
        p1.showInfo();
        p1.showInfo("Winner");
        
        System.out.println("\n--- Method overloading in BoardGame ---");
        BoardGame bg = new BoardGame("Monopoly", 2, 6, 90);
        bg.showInfo();
        System.out.println();
        bg.showInfo(true);
        
        System.out.println("\n--- Method overriding in StrategyGame ---");
        StrategyGame sg = new StrategyGame("Risk", 2, 5, 120, 7, "War Strategy");
        System.out.println("Base class method:");
        ((BoardGame)sg).showInfo();
        
        System.out.println("\nOverridden method:");
        sg.showInfo();
        
        System.out.println("\nOverloaded overridden method:");
        sg.showInfo(true);
        
        System.out.println("\n--- Method overloading play() ---");
        System.out.println("No parameters:");
        sg.play();
        
        System.out.println("\nWith Player parameter:");
        Player p2 = new Player("Sarah", 28);
        sg.play(p2);
        
        System.out.println("\n--- Method overloading in GameCollection ---");
        GameCollection col = new GameCollection("Anna");
        col.addGame(bg);
        col.addGame(sg);
        col.addPlayer(p1);
        col.addPlayer(p2);
        
        System.out.println("\nBasic info:");
        col.showInfo();
        
        System.out.println("\nDetailed info:");
        col.showInfo(true);
        
        System.out.println("\n=== Polymorphism demo ===");
        BoardGame polymorphicGame = new StrategyGame("Civilization", 2, 4, 180, 9, "4X Strategy");
        polymorphicGame.showInfo();
        polymorphicGame.play();
    }
}
