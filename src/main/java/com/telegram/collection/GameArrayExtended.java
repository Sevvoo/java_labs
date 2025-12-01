package com.telegram.collection;

import com.telegram.games.BoardGame;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Розширений клас для роботи з масивом настільних ігор (ЛР9).
 * 
 * <p>Наслідує функціональність GameArray та додає:</p>
 * <ul>
 *   <li>Сортування за назвою (A-Z, Z-A)</li>
 *   <li>Фільтрацію за регулярними виразами</li>
 *   <li>Статистику по тривалості (мін/макс)</li>
 * </ul>
 * 
 * @author Muliarchuk Serhii
 * @version 1.0
 * @see GameArray
 * @see Pattern
 */
public class GameArrayExtended {
    
    /** Масив об'єктів BoardGame */
    private BoardGame[] games;
    
    /** Поточна кількість елементів */
    private int size;
    
    public GameArrayExtended(int capacity) {
        this.games = new BoardGame[capacity];
        this.size = 0;
    }
    
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
    
    public void displayAll() {
        System.out.println("=== All Games ===");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ".");
            games[i].showInfo();
            System.out.println();
        }
    }
    
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
    
    public void sortByDurationAsc() {
        Arrays.sort(games, 0, size, Comparator.comparingInt(BoardGame::getDuration));
        System.out.println("Games sorted by duration (ascending)");
    }
    
    public void sortByDurationDesc() {
        Arrays.sort(games, 0, size, (g1, g2) -> g2.getDuration() - g1.getDuration());
        System.out.println("Games sorted by duration (descending)");
    }
    
    /**
     * Сортує ігри за назвою в алфавітному порядку (A-Z).
     * Використовує метод порівняння String.compareTo().
     */
    public void sortByNameAsc() {
        Arrays.sort(games, 0, size, Comparator.comparing(BoardGame::getName));
        System.out.println("Games sorted by name (A-Z)");
    }
    
    /**
     * Сортує ігри за назвою у зворотному алфавітному порядку (Z-A).
     */
    public void sortByNameDesc() {
        Arrays.sort(games, 0, size, (g1, g2) -> g2.getName().compareTo(g1.getName()));
        System.out.println("Games sorted by name (Z-A)");
    }
    
    /**
     * Фільтрує ігри за регулярним виразом у назві.
     * 
     * <p>Приклади патернів:</p>
     * <ul>
     *   <li>{@code ^C.*} - назви, що починаються з "C"</li>
     *   <li>{@code .*game$} - назви, що закінчуються на "game"</li>
     *   <li>{@code .*o.*} - назви, що містять "o"</li>
     * </ul>
     * 
     * @param pattern регулярний вираз для пошуку
     */
    public void filterByNamePattern(String pattern) {
        System.out.println("\n=== Games matching pattern: " + pattern + " ===");
        int count = 0;
        
        try {
            Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            
            for (int i = 0; i < size; i++) {
                if (regex.matcher(games[i].getName()).find()) {
                    System.out.println((count + 1) + ".");
                    games[i].showInfo();
                    System.out.println();
                    count++;
                }
            }
            
            if (count == 0) {
                System.out.println("No games found");
            }
        } catch (Exception e) {
            System.out.println("Invalid pattern: " + e.getMessage());
        }
    }
    
    /**
     * Виводить статистику по тривалості ігор.
     * Показує гру з найменшою та найбільшою тривалістю.
     */
    public void showDurationMinMax() {
        if (size == 0) {
            System.out.println("Array is empty");
            return;
        }
        
        BoardGame minGame = games[0];
        BoardGame maxGame = games[0];
        
        for (int i = 1; i < size; i++) {
            if (games[i].getDuration() < minGame.getDuration()) {
                minGame = games[i];
            }
            if (games[i].getDuration() > maxGame.getDuration()) {
                maxGame = games[i];
            }
        }
        
        System.out.println("\n=== Duration Statistics ===");
        System.out.println("Shortest duration: " + minGame.getDuration() + " minutes - " + minGame.getName());
        System.out.println("Longest duration: " + maxGame.getDuration() + " minutes - " + maxGame.getName());
    }
    
    public int getSize() {
        return size;
    }
    
    public BoardGame getGame(int index) {
        if (index >= 0 && index < size) {
            return games[index];
        }
        return null;
    }
}
