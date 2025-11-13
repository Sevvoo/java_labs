package com.telegram;

import com.telegram.games.CardGame;
import com.telegram.games.DiceGame;
import com.telegram.games.Game;
import com.telegram.games.Playable;
import com.telegram.games.Player;

public class LR7 {
    public static void main(String[] args) {
        System.out.println("=== Abstract class and Interface demo ===\n");
        
        System.out.println("--- CardGame (abstract Game + Playable interface) ---");
        CardGame poker = new CardGame("Poker", 2, 8, 52, "Standard");
        poker.showInfo();
        System.out.println();
        poker.showRules();
        System.out.println();
        
        Player p1 = new Player("Alice", 25);
        Player p2 = new Player("Bob", 30);
        
        poker.startGame();
        poker.calculateScore(p1);
        poker.calculateScore(p2);
        poker.endGame();
        
        System.out.println("\n--- DiceGame (abstract Game + Playable interface) ---");
        DiceGame yahtzee = new DiceGame("Yahtzee", 2, 6, 5, 6);
        yahtzee.showInfo();
        System.out.println();
        yahtzee.showRules();
        System.out.println();
        
        yahtzee.startGame();
        yahtzee.calculateScore(p1);
        yahtzee.calculateScore(p2);
        yahtzee.endGame();
        
        System.out.println("\n--- Testing Playable interface methods ---");
        System.out.println("Can 4 players play poker?");
        poker.canPlay(4);
        
        System.out.println("\nCan 10 players play poker?");
        poker.canPlay(10);
        
        System.out.println();
        yahtzee.canPlay(3);
        yahtzee.canPlay(7);
        
        System.out.println("\n--- Polymorphism with abstract class ---");
        Game[] games = new Game[2];
        games[0] = new CardGame("Blackjack", 1, 7, 52, "Standard");
        games[1] = new DiceGame("Craps", 1, 20, 2, 6);
        
        for (Game game : games) {
            System.out.println();
            game.showInfo();
            game.showRules();
        }
        
        System.out.println("\n--- Polymorphism with interface ---");
        Playable[] playables = new Playable[2];
        playables[0] = new CardGame("Bridge", 4, 4, 52, "Standard");
        playables[1] = new DiceGame("Liar's Dice", 2, 6, 5, 6);
        
        for (Playable playable : playables) {
            System.out.println();
            playable.startGame();
            playable.canPlay(4);
            playable.endGame();
        }
    }
}
