package com.telegram.games;

import java.util.ArrayList;
import java.util.List;

public class GameCollection {
    private String ownerName;
    private List<BoardGame> games;
    private List<Player> players;
    
    public GameCollection() {
        this.ownerName = "Anonymous";
        this.games = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    public GameCollection(String ownerName) {
        this.ownerName = ownerName;
        this.games = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public void addGame(BoardGame game) {
        games.add(game);
        System.out.println("Added game: " + game.getName());
    }
    
    public void addPlayer(Player player) {
        players.add(player);
        System.out.println("Added player: " + player.getName());
    }
    
    public void showInfo() {
        System.out.println("Collection owner: " + ownerName);
        System.out.println("Total games: " + games.size());
        System.out.println("Total players: " + players.size());
    }
    
    public void showInfo(boolean detailed) {
        if (detailed) {
            System.out.println("=== Game Collection ===");
            System.out.println("Owner: " + ownerName);
            System.out.println("\nGames (" + games.size() + "):");
            for (int i = 0; i < games.size(); i++) {
                System.out.println("\n" + (i + 1) + ".");
                games.get(i).showInfo();
            }
            System.out.println("\nPlayers (" + players.size() + "):");
            for (int i = 0; i < players.size(); i++) {
                System.out.print((i + 1) + ". ");
                players.get(i).showInfo();
            }
        } else {
            showInfo();
        }
    }
    
    public void listGames() {
        System.out.println("\nGames in collection:");
        for (BoardGame game : games) {
            System.out.println("- " + game.getName());
        }
    }
}
