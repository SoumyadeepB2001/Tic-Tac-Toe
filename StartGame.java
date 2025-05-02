import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class StartGame extends JFrame implements ActionListener {
    JButton playerVsPlayerButton, playerVsAiButton, aiVsPlayerButton;
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

        int frameWidth = 480;
        int buttonWidth = 200;
        int buttonHeight = 40;
        int centerX = (frameWidth - buttonWidth) / 2;

        JLabel heading = new JLabel("TIC-TAC-TOE");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(0, 30, frameWidth, 30);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // Adjusted vertical spacing
        playerVsPlayerButton = new JButton("Player vs Player");
        playerVsPlayerButton.setBounds(centerX, 80, buttonWidth, buttonHeight);
        add(playerVsPlayerButton);
        playerVsPlayerButton.addActionListener(this);

        playerVsAiButton = new JButton("Player vs AI");
        playerVsAiButton.setBounds(centerX, 130, buttonWidth, buttonHeight);
        add(playerVsAiButton);
        playerVsAiButton.addActionListener(this);

        aiVsPlayerButton = new JButton("AI vs Player");
        aiVsPlayerButton.setBounds(centerX, 180, buttonWidth, buttonHeight);
        add(aiVsPlayerButton);
        aiVsPlayerButton.addActionListener(this);

        // Use original frame size
        setBounds(550, 200, frameWidth, 280);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Player vs Player":
                new StartPVP().setVisible(true);
                dispose();
                break;
            case "Player vs AI":
                new AI(true).setVisible(true);
                dispose();
                break;
            case "AI vs Player":
                new AI(false).setVisible(true);
                dispose();
                break;
        }
    }
}