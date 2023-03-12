package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

import static blackjack.domain.player.Result.*;

public class Hand {
    private final Cards cards;
    private State state;

    public Hand() {
        this.cards = new Cards();
        this.state = State.HIT;
    }

    public void add(final Card card) {
        cards.add(card);
        state = State.calculateState(cards);
    }

    public Result compare(final Hand other) {
        if (state.isBlackjack()) {
            return compareWithBlackjack(other);
        }
        if (state.isBust()) {
            return compareWithBust(other);
        }
        return compareByScore(other);
    }

    private Result compareWithBlackjack(final Hand other) {
        if (other.state.isBlackjack()) {
            return PUSH;
        }
        return BLACKJACK;
    }

    private Result compareWithBust(final Hand other) {
        if (other.state.isBust()) {
            return PUSH;
        }
        return LOSE;
    }

    private Result compareByScore(final Hand other) {
        if (other.state.isBust() || cards.calculateTotalScore() > other.cards.calculateTotalScore()) {
            return WIN;
        }
        if (other.state.isNotBust() && cards.calculateTotalScore() < other.cards.calculateTotalScore()) {
            return LOSE;
        }
        return PUSH;
    }


    public boolean isPlayable() {
        return state.isPlayable();
    }

    public int calculateScore() {
        return cards.calculateTotalScore();
    }

    public void stay() {
        state = State.STAY;
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public List<String> getCardLetters() {
        return cards.getCardLetters();
    }
}
