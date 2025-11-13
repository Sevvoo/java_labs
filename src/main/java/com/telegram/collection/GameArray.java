package com.telegram.collection;

import com.telegram.games.BoardGame;
import java.util.Arrays;
import java.util.Comparator;

public class GameArray {
    private BoardGame[] games;
    private int size;
    
    public GameArray(int capacity) {
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
    
    public void sortByMinPlayersAsc() {
        Arrays.sort(games, 0, size, Comparator.comparingInt(BoardGame::getMinPlayers));
        System.out.println("Games sorted by min players (ascending)");
    }
    
    public void sortByMinPlayersDesc() {
        Arrays.sort(games, 0, size, (g1, g2) -> g2.getMinPlayers() - g1.getMinPlayers());
        System.out.println("Games sorted by min players (descending)");
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
