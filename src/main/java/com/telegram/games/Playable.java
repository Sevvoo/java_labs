package com.telegram.games;

public interface Playable {
    void startGame();
    void endGame();
    boolean canPlay(int playerCount);
}
