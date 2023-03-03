package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffledDeck implements Deck {
    private static final int TOP_CARD_INDEX = 0;
    private static final Deck DECK;

    static {
        final List<Card> cards = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            Arrays.stream(Shape.values()).forEach(shape -> cards.add(new Card(rank, shape)));
        }
        Collections.shuffle(cards);
        DECK = new ShuffledDeck(cards);
    }

    private final List<Card> cards;

    private ShuffledDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static Deck getInstance() {
        return DECK;
    }

    @Override
    public Card draw() {
        return cards.remove(TOP_CARD_INDEX);
    }
}
