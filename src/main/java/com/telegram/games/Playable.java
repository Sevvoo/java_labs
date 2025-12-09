package com.telegram.games;

/**
 * ІНТЕРФЕЙС Playable
 * 
 * ЩО ТАКЕ ІНТЕРФЕЙС:
 * - Описує "ЩО об'єкт МОЖЕ РОБИТИ", а не "що він Є"
 * - НЕ МОЖЕ мати звичайних полів (тільки public static final константи)
 * - НЕ МОЖЕ мати конструкторів
 * - Всі методи БЕЗ РЕАЛІЗАЦІЇ (тільки сигнатури)
 * - Всі методи автоматично public abstract
 * - Використовується: class CardGame implements Playable
 * 
 * РІЗНИЦЯ З АБСТРАКТНИМ КЛАСОМ:
 * - Інтерфейс: описує ПОВЕДІНКУ (що можна робити)
 * - Абстрактний клас: описує СУТНІСТЬ (що це таке)
 * 
 * КРАТНЕ УСПАДКУВАННЯ:
 * - Клас може implements БАГАТО інтерфейсів:
 *   class CardGame extends Game implements Playable, Scorable, Saveable
 * - Але extends тільки ОДИН клас!
 */
public interface Playable {
    // Всі методи БЕЗ РЕАЛІЗАЦІЇ (автоматично public abstract)
    // ОБОВ'ЯЗКОВО реалізувати в класі, який implements Playable
    void startGame();              // почати гру
    void endGame();                // закінчити гру
    boolean canPlay(int playerCount);  // чи можна грати з N гравцями
}
