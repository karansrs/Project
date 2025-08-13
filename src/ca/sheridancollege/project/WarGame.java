package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public class WarGame extends Game {
    private GroupOfCards deck;
    private final List<Card> table = new ArrayList<>();
    private int rounds = 0;

    public WarGame(String a, String b) {
        players.add(new Player(a));
        players.add(new Player(b));
    }

    @Override
    protected void initializeGame() {
        deck = new GroupOfCards();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                deck.addToBottom(new Card(r, s));
            }
        }
        deck.shuffle();

        boolean left = true;
        Card c;
        while ((c = deck.drawTop()) != null) {
            if (left) players.get(0).getHand().addToBottom(c);
            else      players.get(1).getHand().addToBottom(c);
            left = !left;
        }

        System.out.println("WAR: " + players.get(0).getName() + " vs " + players.get(1).getName());
    }

    @Override
    protected void playRound() {
        rounds++;
        Player a = players.get(0), b = players.get(1);
        if (!a.hasCards() || !b.hasCards()) return;

        table.clear();
        Card ac = a.playTop();
        Card bc = b.playTop();
        table.add(ac);
        table.add(bc);

        if (ac.getValue() > bc.getValue()) {
            a.collect(table);
        } else if (bc.getValue() > ac.getValue()) {
            b.collect(table);
        } else {
            resolveWar();
        }
    }

    private void resolveWar() {
        Player a = players.get(0), b = players.get(1);

        // up to 3 face-down each
        for (int i = 0; i < 3; i++) {
            if (a.hasCards()) table.add(a.playTop());
            if (b.hasCards()) table.add(b.playTop());
        }

        // one face-up each if possible
        Card aUp = a.hasCards() ? a.playTop() : null;
        Card bUp = b.hasCards() ? b.playTop() : null;

        if (aUp == null && bUp == null) return;
        if (aUp == null) { if (bUp != null) table.add(bUp); b.collect(table); return; }
        if (bUp == null) { table.add(aUp); a.collect(table); return; }

        table.add(aUp);
        table.add(bUp);

        if (aUp.getValue() > bUp.getValue()) a.collect(table);
        else if (bUp.getValue() > aUp.getValue()) b.collect(table);
        else resolveWar(); // tie again → repeat
    }

    @Override
    protected boolean isGameOver() {
        int ca = players.get(0).totalCardCount();
        int cb = players.get(1).totalCardCount();
        return ca == 0 || cb == 0 || ca == 52 || cb == 52 || rounds > 5000;
    }

    @Override
    protected void declareWinner() {
        int ca = players.get(0).totalCardCount();
        int cb = players.get(1).totalCardCount();
        if (ca > cb) System.out.println("Winner: " + players.get(0).getName());
        else if (cb > ca) System.out.println("Winner: " + players.get(1).getName());
        else System.out.println("Draw");
    }
}
