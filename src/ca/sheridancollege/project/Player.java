package ca.sheridancollege.project;

import java.util.List;

public class Player {
    private final String name;
    private final GroupOfCards hand = new GroupOfCards();

    public Player(String name) { this.name = name; }

    public String getName() { return name; }
    public GroupOfCards getHand() { return hand; }

    public boolean hasCards() { return !hand.isEmpty(); }
    public Card playTop() { return hand.drawTop(); }
    public void collect(List<Card> pile) { hand.addAllToBottom(pile); }
    public int totalCardCount() { return hand.size(); }
}
