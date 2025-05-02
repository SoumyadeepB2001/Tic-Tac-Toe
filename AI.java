import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AI extends JFrame {
    boolean isHumanTurn;
    int turn = 0;
    char[] board = new char[9]; // '\0' for empty
    JLabel instruction;
    JPanel buttonPanel;
    ImageIcon paperBack = new ImageIcon("assets/paperBack.png");
    JButton[] buttons = new JButton[9];

    char humanSymbol;
    char aiSymbol;

    public AI(boolean isHumanFirst) {
        this.isHumanTurn = isHumanFirst;
        this.humanSymbol = isHumanFirst ? 'X' : 'O';
        this.aiSymbol = isHumanFirst ? 'O' : 'X';

        initComponents();
        setLocationRelativeTo(null);
        setTitle("Tic-Tac-Toe: AI vs Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 510);
        setResizable(false);

        // AI plays immediately if it goes first
        if (!isHumanTurn) {
            SwingUtilities.invokeLater(this::makeAIMove);
        }
    }

    private void initComponents() {
        MenuBarController menuHelper = new MenuBarController(this);
        setJMenuBar(menuHelper.createMenuBar());

        instruction = new JLabel();
        instruction.setFont(new Font("Serif", Font.BOLD, 24));
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        instruction.setText(isHumanTurn ? "Your turn" : "AI's turn");
        add(instruction, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridLayout(3, 3));
        add(buttonPanel, BorderLayout.CENTER);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttonPanel.add(buttons[i]);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setIcon(paperBack);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].setBorder(new LineBorder(Color.BLACK, 2));
            final int idx = i;
            buttons[i].addActionListener(e -> handlePlayerMove(idx));
        }
    }

    private void handlePlayerMove(int index) {
        if (!isHumanTurn || board[index] != '\0')
            return;

        playSound("assets/writing.wav");

        board[index] = humanSymbol;
        updateButton(index, humanSymbol, Color.GREEN);
        turn++;

        if (checkGameOver())
            return;

        isHumanTurn = false;
        instruction.setText("AI's turn");

        SwingUtilities.invokeLater(this::makeAIMove);
    }

    private void makeAIMove() {
        int aiMove = TicTacToeAI.getBestMove(board, aiSymbol);
        if (aiMove < 0 || board[aiMove] != '\0')
            return;

        // Add a little delay
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        playSound("assets/writing.wav");

        board[aiMove] = aiSymbol;
        updateButton(aiMove, aiSymbol, Color.RED);
        turn++;

        if (checkGameOver())
            return;

        isHumanTurn = true;
        instruction.setText("Your turn");
    }

    private void updateButton(int index, char symbol, Color color) {
        buttons[index].setIcon(paperBack);
        buttons[index].setText(String.valueOf(symbol));
        buttons[index].setForeground(color);
        buttons[index].setHorizontalTextPosition(SwingConstants.CENTER);
        buttons[index].setVerticalTextPosition(SwingConstants.CENTER);

        // Remove all action listeners from the button
        for (ActionListener listener : buttons[index].getActionListeners()) {
            buttons[index].removeActionListener(listener);
        }
    }

    private boolean checkGameOver() {
        int[][] winCombos = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 }
        };

        for (int[] combo : winCombos) {
            char a = board[combo[0]], b = board[combo[1]], c = board[combo[2]];
            if (a != '\0' && a == b && b == c) {
                instruction.setText("");
                disableButtons();
                String winner = (a == humanSymbol) ? "You win!" : "AI wins!";
                JOptionPane.showMessageDialog(this, winner);
                return true;
            }
        }

        if (turn == 9) {
            instruction.setText("");
            JOptionPane.showMessageDialog(this, "It's a draw!");
            disableButtons();
            return true;
        }

        return false;
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
            buttons[i].setBackground(Color.GRAY);
        }
    }

    private void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}