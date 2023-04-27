import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    int counter = 0;
    String winner = "none";

    private JButton[][] boardButtons; // [][] two dimensional array

    public TicTacToe() {
        setTitle("Game of Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        boardButtons = new JButton[4][4];
        JPanel boardPanel = new JPanel(new GridLayout(4, 4));

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                boardButtons[row][col] = new JButton(Integer.toString(row * 4 + col));
                boardPanel.add(boardButtons[row][col]);
                boardButtons[row][col].addActionListener(this);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println(e.getActionCommand() + " Selected " + counter);
        int x = Integer.parseInt(e.getActionCommand()) / 4;
        int y = Integer.parseInt(e.getActionCommand()) % 4;

        if (counter % 2 == 0) {
            boardButtons[x][y].setText("X");
            boardButtons[x][y].setEnabled(false);
        } else {
            boardButtons[x][y].setText("O");
            boardButtons[x][y].setEnabled(false);
        }
        counter++;
        check();
        if (winner != "none") {
            this.dispose();
            endScreen();
        } else if (counter == 16) {
            this.dispose();
            endScreen();
        }
    }

    public void check() {
        for (int i = 0; i < 4; i++) {
            if (boardButtons[i][0].getText() == boardButtons[i][1].getText()
                    && boardButtons[i][2].getText() == boardButtons[i][1].getText()
                    && boardButtons[i][3].getText() == boardButtons[i][1].getText()) {
                winner = boardButtons[i][0].getText();
                break;
            } else if (boardButtons[0][i].getText() == boardButtons[1][i].getText()
                    && boardButtons[2][i].getText() == boardButtons[1][i].getText()
                    && boardButtons[3][i].getText() == boardButtons[1][i].getText()) {
                winner = boardButtons[i][0].getText();
                break;
            }
        }
        if (boardButtons[0][0].getText() == boardButtons[1][1].getText()
                && boardButtons[2][2].getText() == boardButtons[1][1].getText()
                && boardButtons[3][3].getText() == boardButtons[1][1].getText()) {
            winner = boardButtons[0][0].getText();
        } else if (boardButtons[3][0].getText() == boardButtons[3][0].getText()
                && boardButtons[1][2].getText() == boardButtons[3][0].getText()
                && boardButtons[2][1].getText() == boardButtons[3][0].getText()) {
            winner = boardButtons[0][0].getText();
        }
    }

    public void endScreen() {
        JFrame frame = new JFrame("End Screen");
        frame.setSize(400, 400);
        JPanel panel = new JPanel();
        JLabel label;
        if (winner == "none")
            label = new JLabel("It was a draw");
        else
            label = new JLabel(winner + " won the game");

        JButton playAgain = new JButton("Play Again");
        JButton quit = new JButton("Quit");

        panel.add(label);
        panel.add(playAgain);
        panel.add(quit);

        playAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                winner = "none";
                counter = 0;
                new TicTacToe();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
