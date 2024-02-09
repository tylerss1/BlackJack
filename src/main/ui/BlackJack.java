package ui;

import model.Deck;
import model.Hand;
import persistence.Save;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Scanner;

//user interface for BlackJack

public class BlackJack {

    private JFrame frame = new JFrame("BlackJack");
    private JFrame frame2 = new JFrame("BlackJack");
    private JTextArea textarea = new JTextArea();
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private JButton hitButton = new JButton("Hit");
    private JButton standButton = new JButton("Stand");
    private JButton doubledownButton = new JButton("Double Down");
    private JButton nextButton = new JButton("Don't Load");
    private JButton nextButton2 = new JButton("Don't Save");
    private double balance = 500;
    Scanner userInput = new Scanner(System.in);
    Save save = new Save();
    Hand myHand = new Hand();
    Hand theirHand = new Hand();
    Deck playingDeck = new Deck();
    double bet;
    boolean roundEnd;

    public BlackJack() {
        makeGame();
    }

    //EFFECTS: runs the game
    public void makeGame() {
        makeFrame();
        makeFrame2();
        loadOption();
        bet = initializeBet();
        myHand.draw(playingDeck);
        myHand.draw(playingDeck);
        theirHand.draw(playingDeck);
        theirHand.draw(playingDeck);
        roundEnd = false;
        choose();
    }

    //EFFECTS: makes the frame with the buttons
    public void makeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 3));
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(hitButton);
        frame.add(standButton);
        frame.add(doubledownButton);
        frame.add(nextButton);
        frame.add(nextButton2);
        try {
            Image img = ImageIO.read(getClass().getResource("resources/save.png"));
            saveButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        frame.setSize(1000, 1000);
    }

    //EFFECTS: makes the text frame
    public void makeFrame2() {
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
        frame2.add(textarea);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        frame2.setSize(1000, 1000);
    }

    //EFFECTS: prompts the user with the option to load their balance
    public void loadOption() {
        textarea.append("Would you like to load your saved balance?\n Load - Yes\n Don't Load - No\n");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                balance = save.loadBalance("./data/balance.txt");
                textarea.append("Balance loaded.\n");
                playingDeck.constructDeck();
                playingDeck.shuffle();
                textarea.append("Your Balance: " + balance + "\n");
                textarea.append("Type in the amount you would like to bet in the console.\n");
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textarea.append("Starting new game...\n");
                playingDeck.constructDeck();
                playingDeck.shuffle();
                textarea.append("Your Balance: " + balance + "\n");
                textarea.append("Type in the amount you would like to bet in the console.\n");
            }
        });
    }

    //EFFECTS: prompts the user with the option to save their balance
    public void saveOption() {
        textarea.append("Would you like to save your balance?\n Save - Yes\n Don't Save - No\n");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.append(save.saveBalance(balance, "./data/balance.txt") + "\n");
                textarea.append("Please exit the game.\n");
            }
        });
        nextButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.append("Balance not saved.\n");
                textarea.append("Please exit the game.\n");
            }
        });
    }

    //EFFECTS: prompts the user to enter a valid bet
    public double initializeBet() {
        try {
            double bet = userInput.nextDouble();
            double betAgain = 0;
            double finalBet;
            if (bet > balance) {
                while (bet > balance) {
                    textarea.append("Insufficient funds. Please bet a lower amount.");
                    betAgain = userInput.nextDouble();
                    bet = betAgain;
                }
                finalBet = betAgain;
            } else {
                finalBet = bet;
            }
            return finalBet;
        } catch (InputMismatchException e) {
            textarea.append("Please enter a proper number.\n");
            userInput.next();
            return initializeBet();
        }
    }


    //EFFECTS: prompts the user to make a choice in the game; to draw a card, do nothing, or draw a card while
    //         doubling their bet
    public void choose() {
        textarea.append("You bet " + bet + ".\n");
        initializeChoose();
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hit();
                checkIfBust();
            }
        });
        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stand();
                determineResults();
                textarea.append("Dealer Cards: " + theirHand.readHand() + "\n");
                startAgain();
                saveOption();
            }
        });
        doubledownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doubleDown();
                checkIfBust();
            }
        });
    }

    //EFFECTS: end the round
    public void startAgain() {
        myHand.dump();
        theirHand.dump();
        myHand.resetValue();
        theirHand.resetValue();
        playingDeck.resetDeck();
    }

    //EFFECTS: check if user's hand amounts to over 21, if so end the round
    public void checkIfBust() {
        if (myHand.getCollectiveValue() > 21) {
            bustedInChoose();
        }
    }

    //EFFECTS: gives the user directions to make a choice
    public void initializeChoose() {
        textarea.append("Your hand: " + "\n");
        textarea.append(myHand.readHand());
        textarea.append("\n\n" + "Dealer's Hand:" + "\n\n" + theirHand.readFirstCard() + "\n" + "N/A\n");
        myHand.resetValue();
        myHand.addAll();
        textarea.append("Your hand's value adds up to " + myHand.getCollectiveValue());
        myHand.resetValue();
        textarea.append("\nYou can hit, stand, or double down.\n");
    }

    //EFFECTS: takes away the user's bet and ends the round if they go over 21 in the "choosing" phase
    public void bustedInChoose() {
        this.roundEnd = true;
        textarea.append("Busted! Your hand is valued at " + myHand.getCollectiveValue() + "\n");
        balance -= bet;
        textarea.append("Game over. Please press stand to continue.\n");
    }

    //EFFECTS: run this if the user chooses to stand
    public void stand() {
        myHand.resetValue();
        myHand.addAll();
        textarea.append("You stand with a value of " + myHand.getCollectiveValue() + "\n");
    }

    //EFFECTS: run this if the user decides to draw a card
    public void hit() {
        myHand.draw(playingDeck);
        textarea.append("You drew " + myHand.announceNewDraw() + ". ");
        myHand.resetValue();
        myHand.addAll();
        textarea.append("Your hand is now valued at " + myHand.getCollectiveValue() + "." + "\n");
    }

    //EFFECTS: run this if the user wants to draw a card and double their bet
    public void doubleDown() {
        if (bet * 2 > balance) {
            textarea.append("Insufficient Funds.\n");
        } else {
            bet = bet * 2;
            myHand.draw(playingDeck);
            textarea.append("You drew a " + myHand.announceNewDraw() + " .");
            myHand.resetValue();
            myHand.addAll();
            textarea.append("Your hand is now valued at " + myHand.getCollectiveValue() + "." + "\n");
        }
    }

    //EFFECTS: if the user did not go over 21 in the "choosing" phase, determine if the dealer or the user won
    //         using different approaches
    public void determineResults() {
        theirHand.addAll();
        initialDealerHand();
        addDealerCards();
        theirHand.resetValue();
        theirHand.addAll();
        textarea.append("Dealer's Hand is valued at " + theirHand.getCollectiveValue() + "." + "\n");
        checkIfDealerBusts();
        determineTie();
        checkIfDealerWon();
        checkIfPlayerWon();
    }

    //EFFECTS: check to see if the dealer inherently won the round without it drawing anything.
    //         if this is the case, end the round and take away the bet from the user's balance
    public void initialDealerHand() {
        if ((theirHand.getCollectiveValue() > myHand.getCollectiveValue()) && roundEnd == false) {
            textarea.append("Dealer wins.\n");
            balance -= bet;
            roundEnd = true;
        }
    }

    //EFFECTS: dealer draws a card if its hand value is under 17
    public void addDealerCards() {
        while ((theirHand.getCollectiveValue() < 17) && roundEnd == false) {
            theirHand.draw(playingDeck);
            theirHand.announceNewDraw();
            theirHand.resetValue();
            theirHand.addAll();
        }
    }

    //EFFECTS: check to see if the dealer went over 21 after drawing cards.
    //         if this is the case, end the round and add the bet to the user's balance
    public void checkIfDealerBusts() {
        if ((theirHand.getCollectiveValue() > 21) && roundEnd == false) {
            textarea.append("Dealer busts! You win. \n");
            balance += bet;
            roundEnd = true;
        }
    }

    //EFFECTS: check to see if the dealer and the user's hand value is the same.
    //         in this case, end the round and return the bet to the user's balance
    public void determineTie() {
        if ((myHand.getCollectiveValue() == theirHand.getCollectiveValue()) && roundEnd == false) {
            textarea.append("Tie.\n");
            roundEnd = true;
        }
    }

    //EFFECTS: check to see if the dealer has a higher hand value than the user.
    //         in this case, end the round and take away the bet from the user's balance
    public void checkIfDealerWon() {
        if ((myHand.getCollectiveValue() < theirHand.getCollectiveValue()) && roundEnd == false) {
            textarea.append("Dealer Wins.\n");
            balance -= bet;
            roundEnd = true;
        }
    }

    //EFFECTS: check to see if the dealer has a lower hand value than the user.
    //         in this case, end the round and add the bet to the user's balance
    public void checkIfPlayerWon() {
        if ((myHand.getCollectiveValue() > theirHand.getCollectiveValue()) && roundEnd == false) {
            textarea.append("You Win.\n");
            balance += bet;
            roundEnd = true;
        }
    }
}
