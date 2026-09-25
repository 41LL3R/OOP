package ru.nsu.etronin.task112;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public int getScore() {
        int sum = 0;
        int aces = 0;

        for (Card card : cards) {
            sum += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aces++;
            }
        }

        while (sum > 21 && aces > 0) {
            sum -= 10;
            aces--;
        }
        return sum;
    }

    public String getCardsString(boolean hideFirstCard) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0 && hideFirstCard) {
                sb.append("<???>");
            } else {
                Card c = cards.get(i);
                sb.append(c).append(" (").append(c.getValue()).append(")");
            }
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String getClosedCardString() {
        if (cards.isEmpty()) {
            return "<нет карты>";
        }
        return cards.get(0).toString();
    }

    public int size() {
        return cards.size();
    }
}
