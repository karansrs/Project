package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    protected final List<Player> players = new ArrayList<>();

    public final void play() {
        initializeGame();
        while (!isGameOver()) {
            playRound();
        }
        declareWinner();
    }

    protected abstract void initializeGame();
    protected abstract void playRound();
    protected abstract boolean isGameOver();
    protected abstract void declareWinner();
}
