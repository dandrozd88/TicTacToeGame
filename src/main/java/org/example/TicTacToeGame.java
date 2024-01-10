package org.example;

import java.awt.*;
import java.awt.event.*;

class TicTacToeGame extends Frame implements ActionListener {

    Button button[] = new Button[9];

    Button newGameButton;
    Label resultLabel;
    boolean playerTurn = true;

    int key = 0, x = 8, y = 28;

    TicTacToeGame() {
        setLayout(null);
        setVisible(true);
        setSize(800, 600);
        setLocation(400, 100);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setTitle("Tic Tac Toe Game by Drozdu");

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {

                button[key] = new Button();
                button[key].setSize(100, 100);
                button[key].setLocation(x, y);
                button[key].setFont(new Font("", Font.BOLD, 40));

                add(button[key]);
                button[key].addActionListener(this);
                button[key].setBackground(Color.BLUE);
                key++;
                x = x + 100;
            }
            x = 8;
            y = y + 100;
        }

        newGameButton = new Button("New Game");
        newGameButton.setSize(150, 40);
        newGameButton.setLocation(500, 300);
        newGameButton.setFont(new Font("", Font.BOLD, 20));
        newGameButton.setForeground(Color.white);

        add(newGameButton);
        newGameButton.addActionListener(this);


        resultLabel = new Label("");
        resultLabel.setAlignment(Label.CENTER);
        resultLabel.setSize(400, 50);
        resultLabel.setLocation(200, 550);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.WHITE);
        add(resultLabel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            resetGame();
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i] && button[i].getLabel().equals("")) {
                button[i].setLabel(playerTurn ? "O" : "X");
                playerTurn = !playerTurn;
                checkWinner();
                break;
            }
        }

    }

    private void resetGame() {
        for (Button btn : button) {
            btn.setLabel("");
        }
        playerTurn = true;
        resultLabel.setText("");
    }

    private void checkWinner() {

        for (int i = 0; i < 9; i += 3) {
            if (!button[i].getLabel().equals("") &&
                    button[i].getLabel().equals(button[i + 1].getLabel()) &&
                    button[i].getLabel().equals(button[i + 2].getLabel())) {
                resultLabel.setText(button[i].getLabel() + " wins!");
                return;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (!button[i].getLabel().equals("") &&
                    button[i].getLabel().equals(button[i + 3].getLabel()) &&
                    button[i].getLabel().equals(button[i + 6].getLabel())) {
                resultLabel.setText(button[i].getLabel() + " wins!");
                return;
            }
        }

        if (!button[0].getLabel().equals("") &&
                button[0].getLabel().equals(button[4].getLabel()) &&
                button[0].getLabel().equals(button[8].getLabel()) ||
                !button[2].getLabel().equals("") &&
                        button[2].getLabel().equals(button[4].getLabel()) &&
                        button[2].getLabel().equals(button[6].getLabel())) {
            resultLabel.setText(button[4].getLabel() + " wins!");
            return;
        }

        boolean isDraw = true;
        for (int i = 0; i < 9; i++) {
            if (button[i].getLabel().equals("")) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            resultLabel.setText("Draw!");
            return;
        }

        resultLabel.setText("Player " + (playerTurn ? "O" : "X") + "'s turn");
    }
}
