package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupOfCards {
    private final List<Card> cards = new ArrayList<>();

    public void addToBottom(Card c) { if (c != null) cards.add(c); }
    public void addAllToBottom(List<Card> list) { if (list != null && !list.isEmpty()) cards.addAll(list); }
    public Card drawTop() { return cards.isEmpty() ? null : cards.remove(0); }
    public void shuffle() { Collections.shuffle(cards); }
    public boolean isEmpty() { return cards.isEmpty(); }
    public int size() { return cards.size(); }
}
