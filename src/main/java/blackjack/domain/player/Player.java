package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.game.Result;

import java.util.List;

public abstract class Player {

    protected final Name name;
    protected final Hand hand;

    public Player(final Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract boolean isDrawable();

    public abstract boolean isDealer();

    public abstract Result play(final Hand hand);

    public void initialDraw(final Deck deck) {
        draw(deck);
        draw(deck);
    }

    public void draw(final Deck deck) {
        hand.add(deck.draw());
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public void stay() {
        hand.stay();
    }

    public String getName() {
        return name.getValue();
    }

    public List<String> getCardLetters() {
        return hand.getCardLetters();
    }
}
