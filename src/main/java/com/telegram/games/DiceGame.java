package com.telegram.games;

public class DiceGame extends Game implements Playable {
    private int diceCount;
    private int diceSides;
    
    public DiceGame() {
        super();
        this.diceCount = 2;
        this.diceSides = 6;
    }
    
    public DiceGame(String name, int minPlayers, int maxPlayers, int diceCount, int diceSides) {
        super(name, minPlayers, maxPlayers);
        this.diceCount = diceCount;
        this.diceSides = diceSides;
    }
    
    public int getDiceCount() {
        return diceCount;
    }
    
    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }
    
    public int getDiceSides() {
        return diceSides;
    }
    
    public void setDiceSides(int diceSides) {
        this.diceSides = diceSides;
    }
    
    @Override
    public void showRules() {
        System.out.println("Rules for " + name + ":");
        System.out.println("  Dice: " + diceCount + "d" + diceSides);
        System.out.println("  Players: " + minPlayers + "-" + maxPlayers);
    }
    
    @Override
    public int calculateScore(Player player) {
        int score = 0;
        for (int i = 0; i < diceCount; i++) {
            score += (int)(Math.random() * diceSides) + 1;
        }
        System.out.println(player.getName() + " rolled: " + score);
        return score;
    }
    
    @Override
    public void startGame() {
        isActive = true;
        System.out.println("Starting " + name + "...");
        System.out.println("Rolling " + diceCount + " dice...");
    }
    
    @Override
    public void endGame() {
        isActive = false;
        System.out.println("Ending " + name + "...");
    }
    
    @Override
    public boolean canPlay(int playerCount) {
        boolean can = playerCount >= minPlayers && playerCount <= maxPlayers;
        if (can) {
            System.out.println(playerCount + " players can play " + name);
        } else {
            System.out.println(playerCount + " players cannot play " + name);
        }
        return can;
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("  Dice: " + diceCount + "d" + diceSides);
    }
}
