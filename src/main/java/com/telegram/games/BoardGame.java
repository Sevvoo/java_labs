package com.telegram.games;

public class BoardGame {
    private String name;
    private int minPlayers;
    private int maxPlayers;
    private int duration;
    
    public BoardGame() {
        this.name = "Unknown Game";
        this.minPlayers = 2;
        this.maxPlayers = 4;
        this.duration = 30;
    }
    
    public BoardGame(String name, int minPlayers, int maxPlayers, int duration) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.duration = duration;
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
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public void showInfo() {
        System.out.println("Game: " + name);
        System.out.println("  Players: " + minPlayers + "-" + maxPlayers);
        System.out.println("  Duration: " + duration + " min");
    }
    
    public void showInfo(boolean detailed) {
        if (detailed) {
            System.out.println("=== Board Game Info ===");
            System.out.println("Name: " + name);
            System.out.println("Min players: " + minPlayers);
            System.out.println("Max players: " + maxPlayers);
            System.out.println("Duration: " + duration + " minutes");
        } else {
            showInfo();
        }
    }
    
    public void play() {
        System.out.println("Playing " + name + "...");
    }
}
