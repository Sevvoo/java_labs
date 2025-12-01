/**
 * Пакет collection містить класи для роботи з колекціями об'єктів BoardGame.
 */
package com.telegram.collection;

import com.telegram.games.BoardGame;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Клас для роботи з масивом настільних ігор (ЛР8).
 * 
 * <p>Забезпечує базові операції з масивом об'єктів:</p>
 * <ul>
 *   <li>Ініціалізація масиву стандартними даними</li>
 *   <li>Відображення всіх елементів</li>
 *   <li>Модифікація елементів</li>
 *   <li>Фільтрація за критеріями</li>
 *   <li>Сортування за різними полями</li>
 * </ul>
 * 
 * @author Muliarchuk Serhii
 * @version 1.0
 * @see BoardGame
 */
public class GameArray {
    
    /** Масив об'єктів BoardGame */
    private BoardGame[] games;
    
    /** Поточна кількість елементів у масиві */
    private int size;
    
    /**
     * Конструктор з заданою ємністю масиву.
     * 
     * @param capacity максимальна кількість елементів у масиві
     */
    public GameArray(int capacity) {
        this.games = new BoardGame[capacity];
        this.size = 0;
    }
    
    /**
     * Ініціалізує масив стандартним набором настільних ігор.
     * Додає 10 популярних настільних ігор з різними характеристиками.
     */
    public void initializeWithDefaultGames() {
        games[0] = new BoardGame("Catan", 3, 4, 90);
        games[1] = new BoardGame("Monopoly", 2, 6, 120);
        games[2] = new BoardGame("Chess", 2, 2, 45);
        games[3] = new BoardGame("Scrabble", 2, 4, 60);
        games[4] = new BoardGame("Ticket to Ride", 2, 5, 75);
        games[5] = new BoardGame("Pandemic", 2, 4, 45);
        games[6] = new BoardGame("Carcassonne", 2, 5, 35);
        games[7] = new BoardGame("Risk", 2, 6, 180);
        games[8] = new BoardGame("Uno", 2, 10, 30);
        games[9] = new BoardGame("Codenames", 4, 8, 15);
        size = 10;
    }
    
    /**
     * Виводить інформацію про всі ігри в консоль.
     * Кожна гра виводиться з порядковим номером.
     */
    public void displayAll() {
        System.out.println("=== All Games ===");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ".");
            games[i].showInfo();
            System.out.println();
        }
    }
    
    /**
     * Модифікує гру за вказаним індексом.
     * 
     * @param index      індекс гри в масиві (0-based)
     * @param name       нова назва гри
     * @param minPlayers нова мінімальна кількість гравців
     * @param maxPlayers нова максимальна кількість гравців
     * @param duration   нова тривалість гри в хвилинах
     */
    public void modifyGame(int index, String name, int minPlayers, int maxPlayers, int duration) {
        if (index >= 0 && index < size) {
            games[index].setName(name);
            games[index].setMinPlayers(minPlayers);
            games[index].setMaxPlayers(maxPlayers);
            games[index].setDuration(duration);
            System.out.println("Game " + (index + 1) + " modified successfully");
        } else {
            System.out.println("Invalid index");
        }
    }
    
    /**
     * Фільтрує та виводить ігри з тривалістю, більшою або рівною заданій.
     * 
     * @param minDuration мінімальна тривалість гри в хвилинах
     */
    public void filterByDuration(int minDuration) {
        System.out.println("\n=== Games with duration >= " + minDuration + " minutes ===");
        int count = 0;
        
        for (int i = 0; i < size; i++) {
            if (games[i].getDuration() >= minDuration) {
                System.out.println((count + 1) + ".");
                games[i].showInfo();
                System.out.println();
                count++;
            }
        }
        
        if (count == 0) {
            System.out.println("No games found");
        }
    }
    
    /**
     * Сортує ігри за тривалістю (за зростанням).
     * Використовує {@link Arrays#sort(Object[], int, int, Comparator)}.
     */
    public void sortByDurationAsc() {
        Arrays.sort(games, 0, size, Comparator.comparingInt(BoardGame::getDuration));
        System.out.println("Games sorted by duration (ascending)");
    }
    
    /**
     * Сортує ігри за тривалістю (за спаданням).
     * Використовує лямбда-вираз для порівняння.
     */
    public void sortByDurationDesc() {
        Arrays.sort(games, 0, size, (g1, g2) -> g2.getDuration() - g1.getDuration());
        System.out.println("Games sorted by duration (descending)");
    }
    
    /**
     * Сортує ігри за мінімальною кількістю гравців (за зростанням).
     */
    public void sortByMinPlayersAsc() {
        Arrays.sort(games, 0, size, Comparator.comparingInt(BoardGame::getMinPlayers));
        System.out.println("Games sorted by min players (ascending)");
    }
    
    /**
     * Сортує ігри за мінімальною кількістю гравців (за спаданням).
     */
    public void sortByMinPlayersDesc() {
        Arrays.sort(games, 0, size, (g1, g2) -> g2.getMinPlayers() - g1.getMinPlayers());
        System.out.println("Games sorted by min players (descending)");
    }
    
    /**
     * Повертає поточну кількість ігор у масиві.
     * 
     * @return кількість ігор
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Повертає гру за вказаним індексом.
     * 
     * @param index індекс гри (0-based)
     * @return об'єкт BoardGame або null, якщо індекс некоректний
     */
    public BoardGame getGame(int index) {
        if (index >= 0 && index < size) {
            return games[index];
        }
        return null;
    }
}
