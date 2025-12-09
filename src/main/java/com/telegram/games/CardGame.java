package com.telegram.games;

/**
 * КРАТНЕ УСПАДКУВАННЯ В JAVA!
 * 
 * CardGame extends Game            → наслідує від ОДНОГО абстрактного класу
 * CardGame implements Playable     → реалізує ОДИН або БІЛЬШЕ інтерфейсів
 * 
 * ЩО ОТРИМУЄ CardGame:
 * 1. З Game (абстрактний клас):
 *    - Поля: name, minPlayers, maxPlayers, isActive
 *    - Метод з реалізацією: showInfo() (можна перевизначити)
 *    - ОБОВ'ЯЗКОВО реалізувати: showRules(), calculateScore()
 * 
 * 2. З Playable (інтерфейс):
 *    - ОБОВ'ЯЗКОВО реалізувати: startGame(), endGame(), canPlay()
 * 
 * 3. Свої власні:
 *    - Поля: deckSize, cardType
 *    - Методи: getDeckSize(), setDeckSize(), etc.
 */
public class CardGame extends Game implements Playable {
    // Власні поля CardGame (додаткові до полів з Game)
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
    
    // ========== РЕАЛІЗАЦІЯ АБСТРАКТНИХ МЕТОДІВ З Game ==========
    @Override  // перевизначаємо АБСТРАКТНИЙ метод з Game
    public void showRules() {
        System.out.println("Rules for " + name + ":");
        System.out.println("  Deck: " + deckSize + " cards (" + cardType + ")");
        System.out.println("  Players: " + minPlayers + "-" + maxPlayers);
    }
    
    @Override  // перевизначаємо АБСТРАКТНИЙ метод з Game
    public int calculateScore(Player player) {
        int baseScore = (int)(Math.random() * 100);
        System.out.println(player.getName() + " scored: " + baseScore);
        return baseScore;
    }
    
    // ========== РЕАЛІЗАЦІЯ МЕТОДІВ З ІНТЕРФЕЙСУ Playable ==========
    @Override  // реалізуємо метод з інтерфейсу Playable
    public void startGame() {
        isActive = true;  // використовуємо поле з абстрактного класу Game!
        System.out.println("Starting " + name + "...");
        System.out.println("Shuffling " + deckSize + " cards...");
    }
    
    @Override  // реалізуємо метод з інтерфейсу Playable
    public void endGame() {
        isActive = false;  // використовуємо поле з абстрактного класу Game!
        System.out.println("Ending " + name + "...");
    }
    
    @Override  // реалізуємо метод з інтерфейсу Playable
    public boolean canPlay(int playerCount) {
        return playerCount >= minPlayers && playerCount <= maxPlayers;
    }
    
    // ========== ПЕРЕВИЗНАЧЕННЯ ЗВИЧАЙНОГО МЕТОДУ З Game ==========
    @Override  // перевизначаємо ЗВИЧАЙНИЙ метод з Game (не абстрактний!)
    public void showInfo() {
        super.showInfo();  // викликаємо метод з батьківського класу Game
        System.out.println("  Deck size: " + deckSize);  // додаємо свою інформацію
        System.out.println("  Card type: " + cardType);
    }
}
