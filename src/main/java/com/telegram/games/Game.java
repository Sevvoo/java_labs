package com.telegram.games;

/**
 * АБСТРАКТНИЙ КЛАС Game
 * 
 * ЩО ТАКЕ АБСТРАКТНИЙ КЛАС:
 * - НЕ МОЖНА створити об'єкт: new Game() - ПОМИЛКА!
 * - Використовується як БАЗОВИЙ клас для CardGame, DiceGame, etc.
 * - Може мати ПОЛЯ (змінні): name, minPlayers, isActive
 * - Може мати КОНСТРУКТОРИ
 * - Може мати ЗВИЧАЙНІ методи з реалізацією: showInfo()
 * - Може мати АБСТРАКТНІ методи БЕЗ реалізації: abstract void showRules();
 * 
 * ЧОМУ АБСТРАКТНИЙ:
 * - Різні ігри мають різні правила → showRules() абстрактний
 * - Різні ігри рахують бали по-різному → calculateScore() абстрактний
 * - Але всі ігри мають назву, гравців → спільні поля та методи
 */
public abstract class Game {
    // ПОЛЯ (можуть бути в абстрактному класі!)
    protected String name;        // назва гри
    protected int minPlayers;     // мінімум гравців
    protected int maxPlayers;     // максимум гравців
    protected boolean isActive;   // чи гра активна   // чи гра активна
    
    // КОНСТРУКТОРИ (можуть бути в абстрактному класі!)
    // Викликаються через super() з дочірніх класів
    public Game() {
        this.name = "Unknown";
        this.minPlayers = 2;
        this.maxPlayers = 4;
        this.isActive = false;
    }
    
    public Game(String name, int minPlayers, int maxPlayers) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.isActive = false;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getMinPlayers() {
        return minPlayers;
    }
    
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }
    
    public int getMaxPlayers() {
        return maxPlayers;
    }
    
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    // ========== АБСТРАКТНІ МЕТОДИ (БЕЗ РЕАЛІЗАЦІЇ!) ==========
    // Кожна гра має свої правила → ОБОВ'ЯЗКОВО реалізувати в дочірніх класах!
    public abstract void showRules();
    
    // Кожна гра рахує бали по-своєму → ОБОВ'ЯЗКОВО реалізувати!
    public abstract int calculateScore(Player player);
    
    // ========== ЗВИЧАЙНИЙ МЕТОД (З РЕАЛІЗАЦІЄЮ) ==========
    // Всі ігри показують інфо однаково → можна реалізувати тут
    // Але дочірні класи можуть ПЕРЕВИЗНАЧИТИ (override)
    public void showInfo() {
        System.out.println("Game: " + name);
        System.out.println("  Players: " + minPlayers + "-" + maxPlayers);
        System.out.println("  Status: " + (isActive ? "Active" : "Inactive"));
    }
}
