package blackjack.domain.player;

import blackjack.domain.card.Deck;

import java.util.List;
import java.util.Objects;

public abstract class Player {

    protected final Name name;
    protected final Hand hand;

    public Player(final Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract boolean isDrawable();

    public abstract boolean isDealer();

    public abstract Result compareHandTo(final Hand hand);

    public void drawInitialCard(final Deck deck) {
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

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
