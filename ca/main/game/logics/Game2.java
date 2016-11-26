package ca.main.game.logics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game2 extends JFrame {
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu aboutMenu;
	private JButton[][] button;
	private MyButtonListener listener;
	private JMenuItem rulesMenuItem;
	private JMenuItem historyMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem aboutMenuItem;

	public Game2() {
		super("Amoba");
		panel = new JPanel();
		listener = new MyButtonListener();
		button = new JButton[15][15];
		JPanel panel = new JPanel(new GridLayout(15, 15));

		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				button[i][j] = new JButton();
				button[i][j].addActionListener(listener);

				panel.add(button[i][j]);
			}
		}
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(listener);

		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(listener);
		
		historyMenuItem = new JMenuItem("High Score");
		historyMenuItem.addActionListener(listener);
		
		rulesMenuItem = new JMenuItem("Rules");
		rulesMenuItem.addActionListener(listener);

		fileMenu = new JMenu("File");
		fileMenu.add(historyMenuItem);
		fileMenu.add(exitMenuItem);

		aboutMenu = new JMenu("About");
		aboutMenu.add(rulesMenuItem);
		aboutMenu.add(aboutMenuItem);

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);

		setJMenuBar(menuBar);
		add(panel);

		setSize(500, 500);
		setVisible(true);
		setResizable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	String[][] table = new String[15][15];

	private class MyButtonListener implements ActionListener {
		int flag = 0;

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exitMenuItem) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Do you really want to exit the program?", "Exit",
						JOptionPane.YES_NO_OPTION);

				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

			if (e.getSource() == aboutMenuItem) {
				JOptionPane.showMessageDialog(null,
						"Amoba game created by Adam Szekely.", "About",
						JOptionPane.PLAIN_MESSAGE);
			}
			if (e.getSource() == rulesMenuItem) {
				JOptionPane.showMessageDialog(null,
						"The player, who can place 5 similar shapes next to each other horizontally, vertically or diagonally, wins.", "Rules",
						JOptionPane.PLAIN_MESSAGE);
			}
			for (int i = 0; i < button.length; i++) {
				for (int j = 0; j < button[0].length; j++) {
					if (flag == 0) {
						if (e.getSource() == button[i][j]) {
							button[i][j]
									.setText("<html><font color=\"blue\">X</font></html>");
							button[i][j].setFont(button[i][j].getFont()
									.deriveFont(18.0f));
							table[i][j] = "X";
							if (Count.isThereFive(5,table) == true) {
								JOptionPane.showMessageDialog(null,
										"X won the game", "Game Over",
										JOptionPane.PLAIN_MESSAGE);
								Count.setToNull(table);
								for (int k = 0; k < button.length; k++) {
									for (int l = 0; l < button[0].length; l++) {
										button[k][l].setText(null);
									}
								}

							} else if (Count.isThereFive(5,table) == false
									&& Count.isThereNull(table) == false) {
								JOptionPane.showMessageDialog(null,
										"Nobody won the game", "Game Over",
										JOptionPane.PLAIN_MESSAGE);
								Count.setToNull(table);
								for (int k = 0; k < button.length; k++) {
									for (int l = 0; l < button[0].length; l++) {
										button[k][l].setText(null);
									}
								}
							}
							flag = 1;
						}
					} else if (flag == 1) {
						if (e.getSource() == button[i][j]) {
							button[i][j]
									.setText("<html><font color=\"red\">O</font></html>");
							button[i][j].setFont(button[i][j].getFont()
									.deriveFont(16.0f));

							table[i][j] = "O";
							if (Count.isThereFive(5,table) == true) {
								JOptionPane.showMessageDialog(null,
										"O won the game", "Game Over",
										JOptionPane.PLAIN_MESSAGE);
								Count.setToNull(table);
								for (int k = 0; k < button.length; k++) {
									for (int l = 0; l < button[0].length; l++) {
										button[k][l].setText(null);
									}
								}
							} else if (Count.isThereFive(5,table) == false
									&& Count.isThereNull(table) == false) {
								JOptionPane.showMessageDialog(null,
										"Nobody won the game", "Game Over",
										JOptionPane.PLAIN_MESSAGE);
								Count.setToNull(table);
								for (int k = 0; k < button.length; k++) {
									for (int l = 0; l < button[0].length; l++) {
										button[k][l].setText(null);
									}
								}
							}
							flag = 0;
						}
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		Game2 game = new Game2();
	}
}
