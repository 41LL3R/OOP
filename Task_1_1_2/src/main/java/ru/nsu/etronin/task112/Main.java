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
        human.resetHand();
        dealer.resetHand();

        human.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        human.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());

        System.out.println("Дилер раздал карты");
        printState(true);

        if (human.getHand().getScore() == 21) {
            System.out.println("У вас Блэкджек!");
            if (dealer.getHand().getScore() == 21) {
                System.out.println("У дилера тоже Блэкджек. Ничья!");
                System.out.println();
            } else {
                playerWins++;
                printScore();
            }
            return;
        }

        if (dealer.getHand().getScore() == 21) {
            System.out.println("У дилера Блэкджек!");
            System.out.println("Дилер выиграл раунд.");
            dealerWins++;
            printScore();
            return;
        }

        boolean playerBusted = false;

        while (true) {
            System.out.println("Ваш ход");
            System.out.println("-------");
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");

            int choice = scanner.nextInt();

            if (choice == 1) {
                Card newCard = deck.drawCard();
                human.takeCard(newCard);
                System.out.println("Вы открыли карту " + newCard.getFullCard() + " (" + newCard.getValue() + ")");
                printState(true);

                if (human.getHand().getScore() > 21) {
                    System.out.println("Перебор! Вы проиграли.");
                    playerBusted = true;
                    break;
                }
            } else {
                break;
            }
        }

        if (playerBusted) {
            dealerWins++;
            printScore();
            return;
        }

        System.out.println("Ход дилера");
        System.out.println("-------");
        System.out.println("Дилер открывает закрытую карту " + dealer.getHand().getCards().get(0).getFullCard());
        printState(false);

        while (dealer.getHand().getScore() < 17) {
            Card newCard = deck.drawCard();
            dealer.takeCard(newCard);
            System.out.println("Дилер открывает карту " + newCard.getFullCard());
            printState(false);
        }

        int playerScore = human.getHand().getScore();
        int dealerScore = dealer.getHand().getScore();

        System.out.println("Ваши карты: " + human.getHand().getCardsString(false) + " == " + playerScore);
        System.out.println("Карты дилера: " + dealer.getHand().getCardsString(false) + " == " + dealerScore);

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

        printScore();
    }

    private void printState(boolean hideDealerCard) {
        System.out.println("\tВаши карты: " + human.getHand().getCardsString(false) + " == " + human.getHand().getScore());
        System.out.println("\tКарты дилера: " + dealer.getHand().getCardsString(hideDealerCard));
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