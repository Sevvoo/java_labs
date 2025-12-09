/*
 * ЛР6 - Демонстрація наслідування (Inheritance) та композиції (Composition)
 * 
 * СТРУКТУРА КЛАСІВ:
 * 
 * 1. НАСЛІДУВАННЯ (extends):
 *    BoardGame (батьківський клас)
 *        ↑
 *        | extends
 *        |
 *    StrategyGame (дочірній клас)
 *    
 *    StrategyGame НАСЛІДУЄ всі поля та методи від BoardGame
 *    + додає свої власні: complexity, category
 * 
 * 2. КОМПОЗИЦІЯ (contains):
 *    GameCollection (контейнер)
 *        ├── contains List<BoardGame> games
 *        └── contains List<Player> players
 *    
 *    GameCollection НЕ наслідується, а МІСТИТЬ об'єкти інших класів
 * 
 * 3. Player - незалежний клас (не наслідується від інших)
 */
package com.telegram;

import com.telegram.games.BoardGame;
import com.telegram.games.GameCollection;
import com.telegram.games.Player;
import com.telegram.games.StrategyGame;

public class LR6_1 {
    public static void main(String[] args) {
        System.out.println("=== Board Game Classes Demo ===");
        
        //ДЕМОНСТРАЦІЯ КЛАСУ Player (незалежний клас)
        System.out.println("--- Player class ---");
        // Player - самостійний клас, не наслідується від інших
        Player player1 = new Player();
        player1.showInfo();
        
        Player player2 = new Player("Alex", 25);
        player2.showInfo();
        player2.showInfo("Active");  // перевантажений метод (overloading)  // перевантажений метод (overloading)
        
        // ДЕМОНСТРАЦІЯ КЛАСУ BoardGame (БАЗОВИЙ/БАТЬКІВСЬКИЙ клас)
        System.out.println("\n--- BoardGame class ---");
        // BoardGame - це БАЗОВИЙ клас для StrategyGame
        // Має поля: name, minPlayers, maxPlayers, duration
        BoardGame game1 = new BoardGame();
        game1.showInfo();
        
        BoardGame game2 = new BoardGame("Catan", 3, 4, 60);
        game2.showInfo(false);  // перевантажений метод
        game2.showInfo(true);   // перевантажений метод
        game2.play();           // базовий метод play()           // базовий метод play()
        
        // ========== НАСЛІДУВАННЯ: StrategyGame extends BoardGame ==========
        System.out.println("\n--- StrategyGame class (inheritance) ---");
        // StrategyGame НАСЛІДУЄ від BoardGame (extends)
        // УСПАДКОВУЄ: name, minPlayers, maxPlayers, duration (з BoardGame)
        // ДОДАЄ: complexity, category (свої власні поля)
        StrategyGame game3 = new StrategyGame();
        game3.showInfo();  // ПЕРЕВИЗНАЧЕНИЙ метод (override) - показує ще й complexity, category
        
        StrategyGame game4 = new StrategyGame("Chess", 2, 2, 45, 8, "Abstract Strategy");
        //                                      ↑________________↑  ↑___________________↑
        //                                      параметри BoardGame   параметри StrategyGame
        game4.showInfo();        // викликає ПЕРЕВИЗНАЧЕНИЙ метод з StrategyGame
        game4.showInfo(true);    // перевантажений перевизначений метод
        game4.play();            // успадкований метод з BoardGame
        game4.play(player2);     // перевантажений метод play(Player)     // перевантажений метод play(Player)
        
        // ========== КОМПОЗИЦІЯ: GameCollection contains BoardGame + Player ==========
        System.out.println("\n--- GameCollection class (composition) ---");
        // GameCollection НЕ НАСЛІДУЄТЬСЯ від BoardGame чи Player!
        // GameCollection МІСТИТЬ (has-a) об'єкти BoardGame та Player всередині
        // Має поля: List<BoardGame> games, List<Player> players
        GameCollection collection1 = new GameCollection();
        collection1.showInfo();
        
        GameCollection collection2 = new GameCollection("John");
        // ДОДАЄМО ігри в колекцію (композиція - collection CONTAINS games):
        collection2.addGame(game2);    // додаємо BoardGame
        collection2.addGame(game4);    // додаємо StrategyGame (також BoardGame через наслідування)
        // ДОДАЄМО гравців в колекцію (композиція - collection CONTAINS players):
        collection2.addPlayer(player1);
        collection2.addPlayer(player2);
        
        System.out.println();
        collection2.showInfo();      // показує загальну інформацію
        collection2.listGames();     // показує список ігор з колекції
        
        System.out.println();
        collection2.showInfo(true);  // детальна інформація про всі об'єкти в колекції
    }
}
