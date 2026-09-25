package ru.nsu.etronin.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, где реализована рука игрока.
 */

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    /**
     * Метод проходится по всем картам в руке и складыввает их значения.
     * @return Возвращает сумму.
     */

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

    /**
     * Создает строку из всех карт, что сейчас есть в руке.
     * @param hideFirstCard Прячет первую карту дилера от человека-игрока.
     * @return Возвращает всю строку имеющихся карт игрока.
     */

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

    /**
     * Показывает скрытую карту дилера.
     * @return Возвращает карту в виде строки.
     */

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
