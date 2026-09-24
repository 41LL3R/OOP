package ru.nsu.etronin.task112;

import java.util.Scanner;

public class Main {
    private final Deck deck;
    private final Player human;
    private final Player dealer;
    private int playerWins = 0;
    private int dealerWins = 0;
    private final Scanner scanner = new Scanner(System.in);

    public Main() {
        this.deck = new Deck(1);
        this.human = new Player("Игрок");
        this.dealer = new Player("Дилер");
    }

    public void start() {
        System.out.println("Добро пожаловать в Блэкджек!");
        int round = 1;
        while (true) {
            System.out.println("Раунд " + round + ":");
            playRound();
            round++;
        }
    }

    private void playRound() {
        resetHands();
        dealInitialCards();
        printState(true);

        if (handleBlackjack()) {
            return;
        }

        if (playerTurn()) {
            dealerWins++;
            printScore();
            return;
        }

        dealerTurn();
        resolveRound();
        printScore();
    }

    private void resetHands() {
        human.resetHand();
        dealer.resetHand();
    }

    private void dealInitialCards() {
        human.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        human.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        System.out.println("Дилер раздал карты");
    }

    private boolean handleBlackjack() {
        if (human.getScore() == 21) {
            System.out.println("У вас Блэкджек!");
            if (dealer.getScore() == 21) {
                System.out.println("У дилера тоже Блэкджек. Ничья!");
            } else {
                playerWins++;
                printScore();
            }
            System.out.println();
            return true;
        }
        if (dealer.getScore() == 21) {
            System.out.println("У дилера Блэкджек!");
            System.out.println("Дилер выиграл раунд.");
            dealerWins++;
            printScore();
            return true;
        }
        return false;
    }

    private boolean playerTurn() {
        while (true) {
            System.out.println("Ваш ход");
            System.out.println("-------");
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");

            int choice = scanner.nextInt();
            if (choice != 1) break;

            Card newCard = deck.drawCard();
            human.takeCard(newCard);
            System.out.println("Вы открыли карту " + newCard.getFullCard() + " (" + newCard.getValue() + ")");
            printState(true);

            if (human.getScore() > 21) {
                System.out.println("Перебор! Вы проиграли.");
                return true;
            }
        }
        return false;
    }

    private void dealerTurn() {
        System.out.println("Ход дилера");
        System.out.println("-------");
        System.out.println("Дилер открывает закрытую карту " + dealer.getClosedCardString());
        printState(false);

        while (dealer.getScore() < 17) {
            Card newCard = deck.drawCard();
            dealer.takeCard(newCard);
            System.out.println("Дилер открывает карту " + newCard.getFullCard());
            printState(false);
        }
    }

    private void resolveRound() {
        int playerScore = human.getScore();
        int dealerScore = dealer.getScore();
        printState(false);

        if (dealerScore > 21) {
            System.out.println("У дилера перебор! Вы выиграли раунд!");
            playerWins++;
        } else if (playerScore > dealerScore) {
            System.out.println("Вы выиграли раунд!");
            playerWins++;
        } else if (playerScore < dealerScore) {
            System.out.println("Дилер выиграл раунд.");
            dealerWins++;
        } else {
            System.out.println("Ничья!");
        }
    }



    private void printState(boolean hideDealerCard) {
        System.out.println("\tВаши карты: " + human.getHandString(false) + " == " + human.getScore());
        if (hideDealerCard) {
            System.out.println("\tКарты дилера: " + dealer.getHandString(true));
        } else {
            System.out.println("\tКарты дилера: " + dealer.getHandString(false) + " == " + dealer.getScore());
        }
        System.out.println();
    }

    private void printScore() {
        System.out.println("Счет " + playerWins + ":" + dealerWins);
        System.out.println();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}