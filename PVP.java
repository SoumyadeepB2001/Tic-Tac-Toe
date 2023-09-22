import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.*;

public class PVP extends JFrame implements ActionListener {

    String player1, player2;
    int i = 0;
    char A[] = new char[9];
    JMenuBar mb;
    JMenu options, help;
    JMenuItem newGame, exit, contact, rules, about;
    JLabel instruction;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;

    public static void main(String[] args) {
        
    }

    PVP(String pl1, String pl2) {
        player1 = pl1;
        player2 = pl2;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Tic-Tac-Toe");
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
        instruction.setBounds(0, 10, 465, 30);
        add(instruction);
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        instruction.setText(player1 + "'s turn");

        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();
        b7 = new JButton();
        b8 = new JButton();
        b9 = new JButton();

        b1.setFont(new Font("Arial", Font.BOLD, 40));
        b1.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 40));
        b2.setForeground(Color.WHITE);
        b3.setFont(new Font("Arial", Font.BOLD, 40));
        b3.setForeground(Color.WHITE);
        b4.setFont(new Font("Arial", Font.BOLD, 40));
        b4.setForeground(Color.WHITE);
        b5.setFont(new Font("Arial", Font.BOLD, 40));
        b5.setForeground(Color.WHITE);
        b6.setFont(new Font("Arial", Font.BOLD, 40));
        b6.setForeground(Color.WHITE);
        b7.setFont(new Font("Arial", Font.BOLD, 40));
        b7.setForeground(Color.WHITE);
        b8.setFont(new Font("Arial", Font.BOLD, 40));
        b8.setForeground(Color.WHITE);
        b9.setFont(new Font("Arial", Font.BOLD, 40));
        b9.setForeground(Color.WHITE);

        b1.setBounds(10, 45, 140, 140);
        b2.setBounds(155, 45, 140, 140);
        b3.setBounds(300, 45, 140, 140);
        b4.setBounds(10, 190, 140, 140);
        b5.setBounds(155, 190, 140, 140);
        b6.setBounds(300, 190, 140, 140);
        b7.setBounds(10, 335, 140, 140);
        b8.setBounds(155, 335, 140, 140);
        b9.setBounds(300, 335, 140, 140);

        addButtonActionListeners();

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        setLayout(null);
        setBounds(500, 50, 465, 547);
    }

    private void addButtonActionListeners() {
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[0] = 'O';
                    b1.setText("O");
                    b1.setBackground(Color.RED);
                } else {
                    A[0] = 'X';
                    b1.setText("X");
                    b1.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b1.getActionListeners())
                    b1.removeActionListener(listener);
                check();
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[1] = 'O';
                    b2.setText("O");
                    b2.setBackground(Color.RED);
                } else {
                    A[1] = 'X';
                    b2.setText("X");
                    b2.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b2.getActionListeners())
                    b2.removeActionListener(listener);
                check();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[2] = 'O';
                    b3.setText("O");
                    b3.setBackground(Color.RED);
                } else {
                    A[2] = 'X';
                    b3.setText("X");
                    b3.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b3.getActionListeners())
                    b3.removeActionListener(listener);
                check();
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[3] = 'O';
                    b4.setText("O");
                    b4.setBackground(Color.RED);
                } else {
                    A[3] = 'X';
                    b4.setText("X");
                    b4.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b4.getActionListeners())
                    b4.removeActionListener(listener);
                check();
            }
        });

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[4] = 'O';
                    b5.setText("O");
                    b5.setBackground(Color.RED);
                } else {
                    A[4] = 'X';
                    b5.setText("X");
                    b5.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b5.getActionListeners())
                    b5.removeActionListener(listener);
                check();
            }
        });

        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[5] = 'O';
                    b6.setText("O");
                    b6.setBackground(Color.RED);
                } else {
                    A[5] = 'X';
                    b6.setText("X");
                    b6.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b6.getActionListeners())
                    b6.removeActionListener(listener);
                check();
            }
        });

        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[6] = 'O';
                    b7.setText("O");
                    b7.setBackground(Color.RED);
                } else {
                    A[6] = 'X';
                    b7.setText("X");
                    b7.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b7.getActionListeners())
                    b7.removeActionListener(listener);
                check();
            }
        });

        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[7] = 'O';
                    b8.setText("O");
                    b8.setBackground(Color.RED);
                } else {
                    A[7] = 'X';
                    b8.setText("X");
                    b8.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b8.getActionListeners())
                    b8.removeActionListener(listener);
                check();
            }
        });

        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i % 2 == 0) {
                    A[8] = 'O';
                    b9.setText("O");
                    b9.setBackground(Color.RED);
                } else {
                    A[8] = 'X';
                    b9.setText("X");
                    b9.setBackground(Color.GREEN);
                }
                for (ActionListener listener : b9.getActionListeners())
                    b9.removeActionListener(listener);
                check();
            }
        });
    }

    private void disableButtons() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);

        b1.setBackground(Color.GRAY);
        b2.setBackground(Color.GRAY);
        b3.setBackground(Color.GRAY);
        b4.setBackground(Color.GRAY);
        b5.setBackground(Color.GRAY);
        b6.setBackground(Color.GRAY);
        b7.setBackground(Color.GRAY);
        b8.setBackground(Color.GRAY);
        b9.setBackground(Color.GRAY);

        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        b4.setForeground(Color.WHITE);
        b5.setForeground(Color.WHITE);
        b6.setForeground(Color.WHITE);
        b7.setForeground(Color.WHITE);
        b8.setForeground(Color.WHITE);
        b9.setForeground(Color.WHITE);
    }

    void check() {
        if ((A[0] == 'X' && A[1] == 'X' && A[2] == 'X') || (A[3] == 'X' && A[4] == 'X' && A[5] == 'X')
                || (A[6] == 'X' && A[7] == 'X' && A[8] == 'X') || (A[0] == 'X' && A[3] == 'X' && A[6] == 'X')
                || (A[1] == 'X' && A[4] == 'X' && A[7] == 'X') || (A[2] == 'X' && A[5] == 'X' && A[8] == 'X')
                || (A[0] == 'X' && A[4] == 'X' && A[8] == 'X') || (A[2] == 'X' && A[4] == 'X' && A[6] == 'X')) {
            instruction.setText("");
            JOptionPane.showMessageDialog(null, player1 + " wins");
            disableButtons();

        } else if ((A[0] == 'O' && A[1] == 'O' && A[2] == 'O') || (A[3] == 'O' && A[4] == 'O' && A[5] == 'O')
                || (A[6] == 'O' && A[7] == 'O' && A[8] == 'O') || (A[0] == 'O' && A[3] == 'O' && A[6] == 'O')
                || (A[1] == 'O' && A[4] == 'O' && A[7] == 'O') || (A[2] == 'O' && A[5] == 'O' && A[8] == 'O')
                || (A[0] == 'O' && A[4] == 'O' && A[8] == 'O') || (A[2] == 'O' && A[4] == 'O' && A[6] == 'O')) {
            instruction.setText("");
            JOptionPane.showMessageDialog(null, player2 + " wins");
            disableButtons();

        } else if (i == 9) {
            instruction.setText("");
            JOptionPane.showMessageDialog(null, "Draw");
            disableButtons();

        } else {
            if (i % 2 == 0)
                instruction.setText(player1 + "'s turn");
            else
                instruction.setText(player2 + "'s turn");
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
