/*
 * ЛР6_2 - Демонстрація перевантаження та перевизначення методів
 * 
 * КЛЮЧОВІ КОНЦЕПЦІЇ:
 * 
 * 1. ПЕРЕВАНТАЖЕННЯ МЕТОДІВ (Overloading):
 *    - Методи з ОДНАКОВОЮ НАЗВОЮ, але РІЗНИМИ ПАРАМЕТРАМИ
 *    - Приклади: showInfo() та showInfo(boolean)
 *              play() та play(Player)
 * 
 * 2. ПЕРЕВИЗНАЧЕННЯ МЕТОДІВ (Overriding):
 *    - StrategyGame ПЕРЕВИЗНАЧАЄ метод showInfo() з BoardGame
 *    - Викликається метод ДОЧІРНЬОГО класу замість батьківського
 *    - Використовується @Override анотація
 * 
 * 3. ПОЛІМОРФІЗМ:
 *    - BoardGame ref = new StrategyGame()
 *    - ref.showInfo() викличе метод StrategyGame (не BoardGame!)
 */
package com.telegram;

import com.telegram.games.BoardGame;
import com.telegram.games.GameCollection;
import com.telegram.games.Player;
import com.telegram.games.StrategyGame;

public class LR6_2 {
    public static void main(String[] args) {
        System.out.println("=== Demonstrating overloaded and overridden methods ===");
        
        // ========== ПЕРЕВАНТАЖЕННЯ в класі Player ==========
        System.out.println("--- Method overloading in Player ---");
        Player p1 = new Player("Mike", 30);
        p1.showInfo();         // showInfo() - без параметрів
        p1.showInfo("Winner"); // showInfo(String) - з параметром String
        // ↑ ДВА методи з ОДНАКОВОЮ НАЗВОЮ, але РІЗНИМИ ПАРАМЕТРАМИ = OVERLOADING // showInfo(String) - з параметром String
        // ↑ ДВА методи з ОДНАКОВОЮ НАЗВОЮ, але РІЗНИМИ ПАРАМЕТРАМИ = OVERLOADING
        
        // ========== ПЕРЕВАНТАЖЕННЯ в класі BoardGame ==========
        System.out.println("\n--- Method overloading in BoardGame ---");
        BoardGame bg = new BoardGame("Monopoly", 2, 6, 90);
        bg.showInfo();      // showInfo() - без параметрів
        System.out.println();
        bg.showInfo(true);  // showInfo(boolean) - з параметром boolean
        // ↑ ПЕРЕВАНТАЖЕННЯ: 2 методи showInfo() з різними параметрами  // showInfo(boolean) - з параметром boolean
        // ↑ ПЕРЕВАНТАЖЕННЯ: 2 методи showInfo() з різними параметрами
        
        // ========== ПЕРЕВИЗНАЧЕННЯ: StrategyGame overrides BoardGame ==========
        System.out.println("\n--- Method overriding in StrategyGame ---");
        StrategyGame sg = new StrategyGame("Risk", 2, 5, 120, 7, "War Strategy");
        
        System.out.println("Base class method:");
        ((BoardGame)sg).showInfo();  // приведення типу до BoardGame - викличе метод BoardGame
        
        System.out.println("\nOverridden method:");
        sg.showInfo();  // викличе ПЕРЕВИЗНАЧЕНИЙ метод з StrategyGame (не BoardGame!)
        // ↑ OVERRIDE: StrategyGame перевизначає showInfo() і додає complexity та category
        
        System.out.println("\nOverloaded overridden method:");
        sg.showInfo(true);  // ПЕРЕВАНТАЖЕНИЙ + ПЕРЕВИЗНАЧЕНИЙ метод
        // ↑ Це showInfo(boolean) з StrategyGame - перевизначає і перевантажує одночасно  // ПЕРЕВАНТАЖЕНИЙ + ПЕРЕВИЗНАЧЕНИЙ метод
        // ↑ Це showInfo(boolean) з StrategyGame - перевизначає і перевантажує одночасно
        
        // ========== ПЕРЕВАНТАЖЕННЯ методу play() ==========
        System.out.println("\n--- Method overloading play() ---");
        System.out.println("No parameters:");
        sg.play();  // play() - без параметрів (успадкований з BoardGame)
        
        System.out.println("\nWith Player parameter:");
        Player p2 = new Player("Sarah", 28);
        sg.play(p2);  // play(Player) - з параметром Player
        // ↑ ПЕРЕВАНТАЖЕННЯ: 2 методи play() з різними параметрами  // play(Player) - з параметром Player
        // ↑ ПЕРЕВАНТАЖЕННЯ: 2 методи play() з різними параметрами
        
        // ========== ПЕРЕВАНТАЖЕННЯ в GameCollection ==========
        System.out.println("\n--- Method overloading in GameCollection ---");
        GameCollection col = new GameCollection("Anna");
        col.addGame(bg);  // додаємо BoardGame
        col.addGame(sg);  // додаємо StrategyGame (це також BoardGame через наслідування!)
        col.addPlayer(p1);
        col.addPlayer(p2);
        
        System.out.println("\nBasic info:");
        col.showInfo();      // showInfo() - без параметрів
        
        System.out.println("\nDetailed info:");
        col.showInfo(true);  // showInfo(boolean) - з параметром
        // ↑ ПЕРЕВАНТАЖЕННЯ: 2 версії showInfo()  // showInfo(boolean) - з параметром
        // ↑ ПЕРЕВАНТАЖЕННЯ: 2 версії showInfo()
        
        // ========== ПОЛІМОРФІЗМ: змінна BoardGame, об'єкт StrategyGame ==========
        System.out.println("\n=== Polymorphism demo ===");
        // ПОЛІМОРФІЗМ: тип змінної BoardGame, але об'єкт StrategyGame!
        BoardGame polymorphicGame = new StrategyGame("Civilization", 2, 4, 180, 9, "4X Strategy");
        //   ↑ тип BoardGame          ↑ створюємо StrategyGame
        
        polymorphicGame.showInfo();  // викличе showInfo() з StrategyGame (не BoardGame!)
        // ↑ Хоча тип змінної BoardGame, викликається метод з StrategyGame - це ПОЛІМОРФІЗМ!
        
        polymorphicGame.play();  // викличе метод з BoardGame (не перевизначений)
    }
}
