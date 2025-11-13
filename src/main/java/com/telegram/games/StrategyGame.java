package com.telegram.games;

public class StrategyGame extends BoardGame {
    private int complexity;
    private String category;
    
    public StrategyGame() {
        super();
        this.complexity = 5;
        this.category = "Strategy";
    }
    
    public StrategyGame(String name, int minPlayers, int maxPlayers, int duration, int complexity, String category) {
        super(name, minPlayers, maxPlayers, duration);
        this.complexity = complexity;
        this.category = category;
    }
    
    public int getComplexity() {
        return complexity;
    }
    
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("  Complexity: " + complexity + "/10");
        System.out.println("  Category: " + category);
    }
    
    @Override
    public void showInfo(boolean detailed) {
        if (detailed) {
            System.out.println("=== Strategy Game Info ===");
            System.out.println("Name: " + getName());
            System.out.println("Min players: " + getMinPlayers());
            System.out.println("Max players: " + getMaxPlayers());
            System.out.println("Duration: " + getDuration() + " minutes");
            System.out.println("Complexity: " + complexity + "/10");
            System.out.println("Category: " + category);
        } else {
            showInfo();
        }
    }
    
    @Override
    public void play() {
        System.out.println("Starting strategy game: " + getName());
        System.out.println("Complexity level: " + complexity);
    }
    
    public void play(Player player) {
        System.out.println(player.getName() + " is playing " + getName());
    }
}
