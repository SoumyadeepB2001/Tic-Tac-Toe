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
import java.net.URL;
import java.awt.*;

public class PVP extends JFrame implements ActionListener {
    String player1, player2;
    int turn = 0;
    char A[] = new char[9];
    JMenuBar mb;
    JMenu options, help;
    JMenuItem newGame, exit, contact, rules, about;
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
        mb = new JMenuBar();
        options = new JMenu("Options");
        help = new JMenu("Help");
        newGame = new JMenuItem("New Game");
        exit = new JMenuItem("Exit");
        contact = new JMenuItem("Contact");
        rules = new JMenuItem("Rules");
        about = new JMenuItem("About");

        options.add(newGame);
        options.add(exit);
        help.add(contact);
        help.add(rules);
        help.add(about);

        // adding actionListenrs to menu items
        newGame.addActionListener(this);
        exit.addActionListener(this);
        contact.addActionListener(this);
        rules.addActionListener(this);
        about.addActionListener(this);

        mb.add(options);
        mb.add(help);
        add(mb);
        setJMenuBar(mb);

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
        if ((A[0] == A[1] && A[1] == A[2] && A[1] != '\0') || (A[3] == A[4] && A[4] == A[5] && A[4] != '\0')
                || (A[6] == A[7] && A[7] == A[8] && A[7] != '\0') || (A[0] == A[3] && A[3] == A[6] && A[3] != '\0')
                || (A[1] == A[4] && A[4] == A[7] && A[4] != '\0') || (A[2] == A[5] && A[5] == A[8] && A[5] != '\0')
                || (A[0] == A[4] && A[4] == A[8] && A[4] != '\0') || (A[2] == A[4] && A[4] == A[6] && A[4] != '\0')) {
            instruction.setText("");
            disableButtons();

            if (turn % 2 == 1)
                JOptionPane.showMessageDialog(null, player1 + " wins");
            else
                JOptionPane.showMessageDialog(null, player2 + " wins");
        }

        else if (turn == 9) {
            instruction.setText("");
            JOptionPane.showMessageDialog(null, "Draw");
            disableButtons();
        }

        else {
            if (turn % 2 == 0)
                instruction.setText(player1 + "'s turn");
            else
                instruction.setText(player2 + "'s turn");
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

    public void actionPerformed(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "New Game":
                Start ob = new Start();
                ob.setVisible(true);
                dispose();
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Contact":
                try {
                    Desktop.getDesktop().browse(new URL("https://twitter.com/SoumyadeepB2001").toURI());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Browser not found");
                }
                break;

            case "Rules":
                JOptionPane.showMessageDialog(null,
                        "In order to win the game, a player must place three of their marks in a horizontal, vertical or diagonal row.");
                break;

            case "About":
                JOptionPane.showMessageDialog(null,
                        "Tic-Tac-Toe Game\nVersion: 1.0.1\nProgram written by Soumyadeep Banerjee\nBSc. (Hons) Computer Science, 1st Year (2020)");
                break;
        }
    }
}
