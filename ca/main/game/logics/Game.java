package ca.main.game.logics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Game extends JFrame {

	private JPanel mainPanel;
	private MyButtonListener listener;
	private JButton panel0;
	private JButton panel1;
	private JButton panel2;
	private JButton panel3;
	private JButton panel4;
	private JButton panel5;
	private JButton panel6;
	private JButton panel7;
	private JButton panel8;
	

	public Game() {
		super("Amoba");
		listener = new MyButtonListener();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,3));

		panel0 = new JButton(" ");
		panel0.addActionListener(listener);
		panel1 = new JButton(" ");
		panel1.addActionListener(listener);
		panel2 = new JButton(" ");
		panel2.addActionListener(listener);
		panel3 = new JButton(" ");
		panel3.addActionListener(listener);
		panel4 = new JButton(" ");
		panel4.addActionListener(listener);
		panel5 = new JButton(" ");
		panel5.addActionListener(listener);
		panel6 = new JButton(" ");
		panel6.addActionListener(listener);
		panel7 = new JButton(" ");
		panel7.addActionListener(listener);
		panel8 = new JButton(" ");
		panel8.addActionListener(listener);
		

		mainPanel.add(panel0);
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		mainPanel.add(panel6);
		mainPanel.add(panel7);
		mainPanel.add(panel8);
		
		

		add(mainPanel);
		setSize(160,160);
		setVisible(true);
		setResizable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	String[][] table = new String[3][3];

	private class MyButtonListener implements ActionListener {
		int flag = 0;

		public void actionPerformed(ActionEvent e) {

			if (flag == 0) {
				for (int i = 0; i < table.length; i++) {
					System.out.println();
					for (int j = 0; j < table[0].length; j++)
						System.out.print(table[i][j] + ",");
				}
				if (Count.isThereThree(table) == true) {
					JOptionPane.showMessageDialog(null, "X won the game",
							"Game Over", JOptionPane.PLAIN_MESSAGE);
					Count.setToNull(table);
					panel0.setText("");
					panel1.setText("");
					panel2.setText("");
					panel3.setText("");
					panel4.setText("");
					panel5.setText("");
					panel6.setText("");
					panel7.setText("");
					panel8.setText("");
				} else if (Count.isThereThree(table) == false
						&& Count.isThereNull(table) == false) {
					JOptionPane.showMessageDialog(null, "Nobody won the game",
							"Game Over", JOptionPane.PLAIN_MESSAGE);
					Count.setToNull(table);
					panel0.setText("");
					panel1.setText("");
					panel2.setText("");
					panel3.setText("");
					panel4.setText("");
					panel5.setText("");
					panel6.setText("");
					panel7.setText("");
					panel8.setText("");
				}

				if (e.getSource() == panel0) {
					table[0][0] = "X";
					flag = 1;
					panel0.setText("X");
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
				}
				if (e.getSource() == panel1) {
					table[0][1] = "X";
					flag = 1;
					panel1.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel2) {
					table[0][2] = "X";
					flag = 1;
					panel2.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel3) {
					table[1][0] = "X";
					flag = 1;
					panel3.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel4) {
					table[1][1] = "X";
					flag = 1;
					panel4.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel5) {
					table[1][2] = "X";
					flag = 1;
					panel5.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel6) {
					table[2][0] = "X";
					flag = 1;
					panel6.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel7) {
					table[2][1] = "X";
					flag = 1;
					panel7.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel8) {
					table[2][2] = "X";
					flag = 1;
					panel8.setText("X");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "X won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
			} else if (flag == 1) {

				if (e.getSource() == panel0) {
					table[0][0] = "O";
					flag = 0;
					panel0.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				} else if (e.getSource() == panel1) {
					table[0][1] = "O";
					flag = 0;
					panel1.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel2) {
					table[0][2] = "O";
					flag = 0;
					panel2.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel3) {
					table[1][0] = "O";
					flag = 0;
					panel3.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel4) {
					table[1][1] = "O";
					flag = 0;
					panel4.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel5) {
					table[1][2] = "O";
					flag = 0;
					panel5.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel6) {
					table[2][0] = "O";
					flag = 0;
					panel6.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel7) {
					table[2][1] = "O";
					flag = 0;
					panel7.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					} else if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
				if (e.getSource() == panel8) {
					table[2][2] = "O";
					flag = 0;
					panel8.setText("O");
					if (Count.isThereThree(table) == true) {
						JOptionPane.showMessageDialog(null, "O won the game",
								"Game Over", JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}

					if (Count.isThereThree(table) == false
							&& Count.isThereNull(table) == false) {
						JOptionPane.showMessageDialog(null,
								"Nobody won the game", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						Count.setToNull(table);
						panel0.setText("");
						panel1.setText("");
						panel2.setText("");
						panel3.setText("");
						panel4.setText("");
						panel5.setText("");
						panel6.setText("");
						panel7.setText("");
						panel8.setText("");
					}
					for (int i = 0; i < table.length; i++) {
						System.out.println();
						for (int j = 0; j < table[0].length; j++)
							System.out.print(table[i][j] + ",");
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		Game game = new Game();
	}

}
