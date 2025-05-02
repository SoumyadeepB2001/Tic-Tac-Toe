import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class PVP extends JFrame {
    String player1, player2;
    int turn = 0;
    char A[] = new char[9];
    JLabel instruction;
    JPanel buttonPanel;
    ImageIcon paperBack = new ImageIcon("assets/paperBack.png");
    JButton button[] = new JButton[9];

    PVP(String pl1, String pl2) {
        player1 = pl1;
        player2 = pl2;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(510, 510);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {   
        MenuBarController menuHelper = new MenuBarController(this);
        setJMenuBar(menuHelper.createMenuBar());
        instruction = new JLabel();
        instruction.setFont(new Font("Serif", Font.BOLD, 24));
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        instruction.setText(player1 + "'s turn");
        add(instruction, BorderLayout.NORTH);
        buttonPanel = new JPanel(new GridLayout(3, 3)); // 3 X 3 button grid
        add(buttonPanel, BorderLayout.CENTER);

        for (int i = 0; i < 9; i++) {
            button[i] = new JButton("");
            buttonPanel.add(button[i]);
            button[i].setBackground(Color.WHITE);
            button[i].setIcon(paperBack);
            button[i].setFont(new Font("Arial", Font.BOLD, 40));
            button[i].setBorder(new LineBorder(Color.BLACK, 2));
            addButtonActionListeners(i);
        }
    }

    private void addButtonActionListeners(int buttonIndex) {
        button[buttonIndex].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turn++;
                playSound("assets/writing.wav");
                if (turn % 2 == 0) {
                    A[buttonIndex] = 'O';
                    button[buttonIndex].setIcon(paperBack);
                    button[buttonIndex].setForeground(Color.RED);
                    button[buttonIndex].setText("O");
                    button[buttonIndex].setHorizontalTextPosition(SwingConstants.CENTER);
                    button[buttonIndex].setVerticalTextPosition(SwingConstants.CENTER);
                }

                else {
                    A[buttonIndex] = 'X';
                    button[buttonIndex].setIcon(paperBack);
                    button[buttonIndex].setForeground(Color.GREEN);
                    button[buttonIndex].setText("X");
                    button[buttonIndex].setHorizontalTextPosition(SwingConstants.CENTER);
                    button[buttonIndex].setVerticalTextPosition(SwingConstants.CENTER);
                }

                for (ActionListener listener : button[buttonIndex].getActionListeners())
                    button[buttonIndex].removeActionListener(listener);

                check();
            }
        });
    }

    public void check() {
        int[][] winningCombinations = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // rows
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // columns
                { 0, 4, 8 }, { 2, 4, 6 } // diagonals
        };

        for (int[] combo : winningCombinations) {
            if (A[combo[0]] != '\0' && A[combo[0]] == A[combo[1]] && A[combo[1]] == A[combo[2]]) {
                instruction.setText("");
                disableButtons();

                String winner = (turn % 2 == 1) ? player1 : player2;
                JOptionPane.showMessageDialog(null, winner + " wins");
                return;
            }
        }

        if (turn == 9) {
            instruction.setText("");
            JOptionPane.showMessageDialog(null, "Draw");
            disableButtons();
        } else {
            instruction.setText((turn % 2 == 0 ? player1 : player2) + "'s turn");
        }
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            button[i].setEnabled(false);
            button[i].setBackground(Color.GRAY);
        }
    }

    public void playSound(String soundFileName) {
        try {
            // Load the sound file
            File soundFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a clip resource
            Clip clip = AudioSystem.getClip();

            // Open the audio stream and start playing it
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
