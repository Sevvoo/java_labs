package com.telegram.games;

public class CardGame extends Game implements Playable {
    private int deckSize;
    private String cardType;
    
    public CardGame() {
        super();
        this.deckSize = 52;
        this.cardType = "Standard";
    }
    
    public CardGame(String name, int minPlayers, int maxPlayers, int deckSize, String cardType) {
        super(name, minPlayers, maxPlayers);
        this.deckSize = deckSize;
        this.cardType = cardType;
    }
    
    public int getDeckSize() {
        return deckSize;
    }
    
    public void setDeckSize(int deckSize) {
        this.deckSize = deckSize;
    }
    
    public String getCardType() {
        return cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    @Override
    public void showRules() {
        System.out.println("Rules for " + name + ":");
        System.out.println("  Deck: " + deckSize + " cards (" + cardType + ")");
        System.out.println("  Players: " + minPlayers + "-" + maxPlayers);
    }
    
    @Override
    public int calculateScore(Player player) {
        int baseScore = (int)(Math.random() * 100);
        System.out.println(player.getName() + " scored: " + baseScore);
        return baseScore;
    }
    
    @Override
    public void startGame() {
        isActive = true;
        System.out.println("Starting " + name + "...");
        System.out.println("Shuffling " + deckSize + " cards...");
    }
    
    @Override
    public void endGame() {
        isActive = false;
        System.out.println("Ending " + name + "...");
    }
    
    @Override
    public boolean canPlay(int playerCount) {
        return playerCount >= minPlayers && playerCount <= maxPlayers;
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("  Deck size: " + deckSize);
        System.out.println("  Card type: " + cardType);
    }
}
