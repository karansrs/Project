package ca.sheridancollege.project;

public final class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) { this.rank = rank; this.suit = suit; }
    public Rank getRank() { return rank; }
    public Suit getSuit() { return suit; }
    public int getValue() { return rank.getValue(); }

    @Override
    public String toString() {
        String s;
        switch (suit) {
            case CLUBS: s = "♣"; break;
            case DIAMONDS: s = "♦"; break;
            case HEARTS: s = "♥"; break;
            case SPADES: s = "♠"; break;
            default: s = "?";
        }
        return rank.name() + s;
    }
}
