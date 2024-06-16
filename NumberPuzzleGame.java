

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberPuzzleGame extends JFrame {

    private JPanel puzzlePanel;
    private JButton[][] tiles;
    private int emptyRow, emptyCol;

    public NumberPuzzleGame() {
        setTitle("Number Puzzle Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        puzzlePanel = new JPanel(new GridLayout(3, 3));
        tiles = new JButton[3][3];

        initializePuzzle();
        add(puzzlePanel);

        setVisible(true);
    }

    private void initializePuzzle() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new JButton(String.valueOf(i * 3 + j + 1));
                tiles[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                puzzlePanel.add(tiles[i][j]);

                final int row = i;
                final int col = j;

                tiles[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveTile(row, col);
                        if (checkIfSolved()) {
                            JOptionPane.showMessageDialog(null, "Congratulations! Puzzle solved.");
                        }
                    }
                });
            }
        }
        tiles[2][2].setText("");
        emptyRow = 2;
        emptyCol = 2;
    }

    private void moveTile(int row, int col) {
        if ((row == emptyRow && Math.abs(col - emptyCol) == 1) ||
                (col == emptyCol && Math.abs(row - emptyRow) == 1)) {
            // Swap text of button and empty button
            tiles[emptyRow][emptyCol].setText(tiles[row][col].getText());
            tiles[row][col].setText("");
            emptyRow = row;
            emptyCol = col;
        }
    }

    private boolean checkIfSolved() {
        int count = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (!tiles[i][j].getText().equals("") && Integer.parseInt(tiles[i][j].getText()) != count) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberPuzzleGame();
            }
        });
    }
}
