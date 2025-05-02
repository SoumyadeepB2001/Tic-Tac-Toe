import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class StartGame extends JFrame implements ActionListener {
    JButton pvpButton, aiButton;
    JPanel contentPane;

    public static void main(String[] args) {
        new StartGame();
    }

    StartGame() {
        this.setTitle("Start Game");
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel heading = new JLabel("TIC-TAC-TOE");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(0, 30, 480, 30);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        pvpButton = new JButton("Player vs Player");
        pvpButton.setBounds(140, 90, 200, 40);
        add(pvpButton);
        pvpButton.addActionListener(this);

        aiButton = new JButton("Player vs AI");
        aiButton.setBounds(140, 150, 200, 40);
        add(aiButton);
        aiButton.addActionListener(this);

        setBounds(550, 200, 480, 280);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Player vs Player":
                new StartPVP().setVisible(true);
                dispose();
                break;

            case "Player vs AI":
                JOptionPane.showMessageDialog(null, "Launching Player vs AI...");
                break;
        }
    }
}