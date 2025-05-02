import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MenuBarController implements ActionListener {
    private JFrame frame;

    public MenuBarController(JFrame frame) {
        this.frame = frame;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu options = new JMenu("Options");
        JMenu help = new JMenu("Help");

        String[] items = { "New Game", "Exit", "Contact", "Rules", "About" };

        for (String itemText : items) {
            JMenuItem item = new JMenuItem(itemText);
            item.addActionListener(this);
            if (itemText.equals("New Game") || itemText.equals("Exit")) {
                options.add(item);
            } else {
                help.add(item);
            }
        }

        menuBar.add(options);
        menuBar.add(help);
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "New Game":
                frame.dispose();
                new Start();
                break;

            case "Exit":
                System.exit(0);
                break;

            case "Contact":
                try {
                    Desktop.getDesktop().browse(new URL("https://twitter.com/SoumyadeepB2001").toURI());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Browser not found");
                }
                break;

            case "Rules":
                JOptionPane.showMessageDialog(frame,
                        "Place three of your marks in a row (horizontal, vertical, or diagonal) to win.");
                break;

            case "About":
                JOptionPane.showMessageDialog(frame,
                        "Tic-Tac-Toe Game\nVersion: 1.0.1\nBy Soumyadeep Banerjee\nBSc (Hons) Computer Science, 1st Year (2020)");
                break;
        }
    }
}