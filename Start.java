import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Start extends JFrame implements ActionListener {
	JButton start, exit;
	JPanel contentPane;
	public JLabel heading, player1, player2;
	public JTextField txtPlayer1, txtPlayer2;

	public static void main(String[] args) {
		new Start();
	}

	Start() {
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

		heading = new JLabel("TIC-TAC-TOE");
		heading.setFont(new Font("Serif", Font.BOLD, 14));
		heading.setBounds(0, 10, 480, 30);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		add(heading);

		player1 = new JLabel("Enter the name of Player 1 [X]");
		player1.setFont(new Font("Serif", Font.BOLD, 14));
		player1.setBounds(10, 70, 200, 30);
		add(player1);
		txtPlayer1 = new JTextField();
		txtPlayer1.setText("Player 1");
		txtPlayer1.setBounds(220, 70, 210, 30);
		add(txtPlayer1);

		player2 = new JLabel("Enter the name of Player 2 [O]");
		player2.setFont(new Font("Serif", Font.BOLD, 14));
		player2.setBounds(10, 120, 200, 30);
		add(player2);
		txtPlayer2 = new JTextField();
		txtPlayer2.setText("Player 2");
		txtPlayer2.setBounds(220, 120, 210, 30);
		add(txtPlayer2);

		start = new JButton("Start");
		start.setBounds(100, 190, 100, 30);
		add(start);
		start.addActionListener(this);

		exit = new JButton("Exit");
		exit.setBounds(250, 190, 100, 30);
		add(exit);
		exit.addActionListener(this);

		setBounds(550, 200, 480, 280);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			case "Start":

				if (txtPlayer1.getText().length() == 0 || txtPlayer2.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "Please enter the names of both players");
				else {
					String pl1 = String.valueOf(txtPlayer1.getText());
					String pl2 = String.valueOf(txtPlayer2.getText());
					TicTacToe obj = new TicTacToe(pl1, pl2);
					obj.setVisible(true);
					dispose();
				}
				break;

			case "Exit":
				System.exit(0);
				break;
		}
	}
}