package com.telegram.collection;

import com.telegram.games.BoardGame;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Клас для роботи з масивом ігор з підтримкою файлових операцій (ЛР10).
 * 
 * <p>Підтримує збереження та завантаження даних з:</p>
 * <ul>
 *   <li>Текстових файлів (формат: Name; MinPlayers; MaxPlayers; Duration)</li>
 *   <li>Бінарних файлів (DataInputStream/DataOutputStream)</li>
 * </ul>
 * 
 * @author Muliarchuk Serhii
 * @version 1.0
 * @see DataInputStream
 * @see DataOutputStream
 * @see BufferedReader
 */
public class GameArrayWithFiles {
    
    /** Масив об'єктів BoardGame */
    private BoardGame[] games;
    
    /** Поточна кількість елементів */
    private int size;
    
    public GameArrayWithFiles(int capacity) {
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
    
    /**
     * Завантажує дані з текстового файлу.
     * 
     * <p>Формат файлу: кожен рядок містить дані гри, розділені крапкою з комою:</p>
     * <pre>Name; MinPlayers; MaxPlayers; Duration</pre>
     * 
     * @param filename шлях до текстового файлу
     * @throws FileNotFoundException якщо файл не знайдено
     */
    public void loadFromTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            size = 0;
            String line;
            
            while ((line = reader.readLine()) != null && size < games.length) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    int minPlayers = Integer.parseInt(parts[1].trim());
                    int maxPlayers = Integer.parseInt(parts[2].trim());
                    int duration = Integer.parseInt(parts[3].trim());
                    
                    games[size] = new BoardGame(name, minPlayers, maxPlayers, duration);
                    size++;
                }
            }
            
            System.out.println("Loaded " + size + " games from " + filename);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in file");
        }
    }
    
    /**
     * Зберігає дані у бінарний файл.
     * 
     * <p>Структура бінарного файлу:</p>
     * <ol>
     *   <li>int - кількість записів</li>
     *   <li>Для кожного запису:
     *     <ul>
     *       <li>UTF String - назва гри</li>
     *       <li>int - мінімальна кількість гравців</li>
     *       <li>int - максимальна кількість гравців</li>
     *       <li>int - тривалість</li>
     *     </ul>
     *   </li>
     * </ol>
     * 
     * @param filename шлях до бінарного файлу
     */
    public void saveToBinaryFile(String filename) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            dos.writeInt(size);
            
            for (int i = 0; i < size; i++) {
                dos.writeUTF(games[i].getName());
                dos.writeInt(games[i].getMinPlayers());
                dos.writeInt(games[i].getMaxPlayers());
                dos.writeInt(games[i].getDuration());
            }
            
            System.out.println("Saved " + size + " games to " + filename);
            
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
    
    /**
     * Завантажує дані з бінарного файлу.
     * Зчитує структуру, збережену методом {@link #saveToBinaryFile(String)}.
     * 
     * @param filename шлях до бінарного файлу
     * @see #saveToBinaryFile(String)
     */
    public void loadFromBinaryFile(String filename) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            size = dis.readInt();
            
            for (int i = 0; i < size; i++) {
                String name = dis.readUTF();
                int minPlayers = dis.readInt();
                int maxPlayers = dis.readInt();
                int duration = dis.readInt();
                
                games[i] = new BoardGame(name, minPlayers, maxPlayers, duration);
            }
            
            System.out.println("Loaded " + size + " games from " + filename);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
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
    
    public void sortByNameAsc() {
        Arrays.sort(games, 0, size, Comparator.comparing(BoardGame::getName));
        System.out.println("Games sorted by name (A-Z)");
    }
    
    public void sortByNameDesc() {
        Arrays.sort(games, 0, size, (g1, g2) -> g2.getName().compareTo(g1.getName()));
        System.out.println("Games sorted by name (Z-A)");
    }
    
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
