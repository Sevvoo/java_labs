package com.telegram.games;

public abstract class Game {
    protected String name;
    protected int minPlayers;
    protected int maxPlayers;
    protected boolean isActive;
    
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
    
    public abstract void showRules();
    
    public abstract int calculateScore(Player player);
    
    public void showInfo() {
        System.out.println("Game: " + name);
        System.out.println("  Players: " + minPlayers + "-" + maxPlayers);
        System.out.println("  Status: " + (isActive ? "Active" : "Inactive"));
    }
}
