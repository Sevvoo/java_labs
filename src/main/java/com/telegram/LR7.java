/*
 * ЛР7 - Абстрактні класи та інтерфейси
 * 
 * ВІДПОВІДІ НА ПИТАННЯ:
 * 
 * 1. ЯКА РІЗНИЦЯ МІЖ ЗВИЧАЙНИМ ТА АБСТРАКТНИМ КЛАСОМ?
 *    
 *    ЗВИЧАЙНИЙ КЛАС (наприклад, BoardGame, Player):
 *    - Можна створити об'єкт: new BoardGame()
 *    - Всі методи мають реалізацію (тіло методу)
 *    - Використовується для повноцінних об'єктів
 *    
 *    АБСТРАКТНИЙ КЛАС (наприклад, Game):
 *    - НЕ МОЖНА створити об'єкт: new Game() - помилка!
 *    - Може мати абстрактні методи (БЕЗ реалізації): abstract void showRules();
 *    - Може мати звичайні методи (З реалізацією): void showInfo() { ... }
 *    - Може мати поля (змінні): name, minPlayers, maxPlayers
 *    - Оголошується з ключовим словом: abstract class Game
 *    - Використовується як базовий клас для спільної функціональності
 * 
 * 2. ЯКА РІЗНИЦЯ МІЖ АБСТРАКТНИМ КЛАСОМ ТА ІНТЕРФЕЙСОМ?
 *    
 *    АБСТРАКТНИЙ КЛАС (Game):
 *    - Може мати ПОЛЯ (змінні стану): name, minPlayers, isActive
 *    - Може мати КОНСТРУКТОРИ: Game(String name, int min, int max)
 *    - Може мати методи З РЕАЛІЗАЦІЄЮ: showInfo() { ... }
 *    - Може мати абстрактні методи: abstract void showRules();
 *    - Наслідування: extends Game (ТІЛЬКИ ОДИН клас!)
 *    - Модифікатори доступу: public, protected, private
 *    
 *    ІНТЕРФЕЙС (Playable):
 *    - НЕ МОЖЕ мати звичайних полів (тільки public static final константи)
 *    - НЕ МОЖЕ мати конструкторів
 *    - Всі методи БЕЗ РЕАЛІЗАЦІЇ (abstract за замовчуванням): void startGame();
 *    - Реалізація: implements Playable (можна БАГАТО інтерфейсів!)
 *    - Всі методи автоматично public
 *    - Описує "що об'єкт може робити", а не "що він є"
 * 
 * 3. ЯК У JAVA МОЖНА РЕАЛІЗУВАТИ КРАТНЕ УСПАДКУВАННЯ?
 *    
 *    Java НЕ ПІДТРИМУЄ кратне успадкування класів (extends тільки один клас),
 *    але ПІДТРИМУЄ кратне успадкування інтерфейсів:
 *    
 *    НЕМОЖЛИВО: class CardGame extends Game, BoardGame  // помилка!
 *    
 *    МОЖЛИВО: class CardGame extends Game implements Playable, Scorable, Saveable
 *                                        ↑                ↑___________________________↑
 *                                   один клас           багато інтерфейсів
 *    
 *    ПРИКЛАД У ЦЬОМУ КОДІ:
 *    - CardGame extends Game (один батьківський клас)
 *    - CardGame implements Playable (один або більше інтерфейсів)
 *    - Це дає "кратне успадкування" через інтерфейси!
 */
package com.telegram;

import com.telegram.games.CardGame;
import com.telegram.games.DiceGame;
import com.telegram.games.Game;
import com.telegram.games.Playable;
import com.telegram.games.Player;

public class LR7 {
    public static void main(String[] args) {
        System.out.println("=== Abstract class and Interface demo ===\n");
        
        // ========== ДЕМОНСТРАЦІЯ КРАТНОГО УСПАДКУВАННЯ ==========
        System.out.println("--- CardGame (abstract Game + Playable interface) ---");
        // CardGame extends Game (абстрактний клас) + implements Playable (інтерфейс)
        // Це КРАТНЕ УСПАДКУВАННЯ через інтерфейси!
        CardGame poker = new CardGame("Poker", 2, 8, 52, "Standard");
        poker.showInfo();       // метод з абстрактного класу Game (перевизначений)
        System.out.println();
        poker.showRules();      // АБСТРАКТНИЙ метод з Game (ОБОВ'ЯЗКОВО реалізувати!)      // АБСТРАКТНИЙ метод з Game (ОБОВ'ЯЗКОВО реалізувати!)
        System.out.println();
        
        Player p1 = new Player("Alice", 25);
        Player p2 = new Player("Bob", 30);
        
        // Методи з ІНТЕРФЕЙСУ Playable (ОБОВ'ЯЗКОВО реалізувати!):
        poker.startGame();           // з інтерфейсу Playable
        poker.calculateScore(p1);    // АБСТРАКТНИЙ метод з Game
        poker.calculateScore(p2);    // АБСТРАКТНИЙ метод з Game
        poker.endGame();             // з інтерфейсу Playable             // з інтерфейсу Playable
        
        System.out.println("\n--- DiceGame (abstract Game + Playable interface) ---");
        // DiceGame ТАКОЖ extends Game + implements Playable (кратне успадкування!)
        DiceGame yahtzee = new DiceGame("Yahtzee", 2, 6, 5, 6);
        yahtzee.showInfo();     // успадкований метод з Game
        System.out.println();
        yahtzee.showRules();    // реалізація АБСТРАКТНОГО методу з Game
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
        
        // ========== ПОЛІМОРФІЗМ З АБСТРАКТНИМ КЛАСОМ ==========
        System.out.println("\n--- Polymorphism with abstract class ---");
        // НЕ МОЖНА: new Game() - помилка компіляції!
        // АЛЕ МОЖНА використати Game як ТИП змінної:
        Game[] games = new Game[2];  // масив типу Game (абстрактний клас)
        games[0] = new CardGame("Blackjack", 1, 7, 52, "Standard");  // CardGame extends Game
        games[1] = new DiceGame("Craps", 1, 20, 2, 6);               // DiceGame extends Game
        
        // Поліморфізм: викликаємо методи різних класів через одну змінну типу Game
        for (Game game : games) {
            System.out.println();
            game.showInfo();     // викличе showInfo() з CardGame або DiceGame
            game.showRules();    // викличе showRules() з CardGame або DiceGame
        }
        
        // ========== ПОЛІМОРФІЗМ З ІНТЕРФЕЙСОМ ==========
        System.out.println("\n--- Polymorphism with interface ---");
        // НЕ МОЖНА: new Playable() - інтерфейс не можна інстанціювати!
        // АЛЕ МОЖНА використати Playable як ТИП змінної:
        Playable[] playables = new Playable[2];  // масив типу Playable (інтерфейс)
        playables[0] = new CardGame("Bridge", 4, 4, 52, "Standard");  // CardGame implements Playable
        playables[1] = new DiceGame("Liar's Dice", 2, 6, 5, 6);       // DiceGame implements Playable
        
        // Поліморфізм: викликаємо тільки методи з інтерфейсу Playable
        for (Playable playable : playables) {
            System.out.println();
            playable.startGame();   // метод з інтерфейсу Playable
            playable.canPlay(4);    // метод з інтерфейсу Playable
            playable.endGame();     // метод з інтерфейсу Playable
            // playable.showRules(); // ПОМИЛКА! showRules() немає в інтерфейсі Playable
        }
    }
}
