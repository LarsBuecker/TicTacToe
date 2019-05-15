package com.carvin.tictactoe;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Basic TicTacToe game using Swing
 * @author Lars Bücker
 * @version 1.0
 */

public class TicTacToe extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Container pane;
	private String currentPlayer;
	private JButton[][] board;
	private boolean hasWinner;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem quit;
	private JMenuItem newGame;
	
	public TicTacToe() {
		super();
		pane = getContentPane();
		pane.setLayout(new GridLayout(3, 3));
		setTitle("Tic Tac Toe");
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		currentPlayer = "x";
		board = new JButton[3][3];
		hasWinner = false;
		
		initBoard();
		initMenuBar();
	}
	
	private void initMenuBar() {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();		
			}
		});
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menu.add(newGame);
		menu.add(quit);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
	
	private void resetBoard() {
		currentPlayer = "x";
		hasWinner = false;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j].setText("");
			}
		}
	}
	
	private void initBoard() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				JButton btn = new JButton();
				btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
				board[i][j] = btn;
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (((JButton) e.getSource()).getText().equals("") && !hasWinner) {
							btn.setText(currentPlayer);
							hasWinner();
							togglePlayer();
						}
						
					}
				});
				pane.add(btn);
			}
		}
	}
	
	private void togglePlayer() {
		if(currentPlayer == "x") {
			currentPlayer = "o";
		} else {
			currentPlayer = "x";
		}
	}
	
	private void hasWinner() {
		for(int i = 0; i < 3; i++) {
			if(checkRow(i)) {
				JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
				hasWinner = true;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(checkCol(i)) {
				JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
				hasWinner = true;
			}
		}
	}
	
	private boolean checkRow(int r) {
		return(board[0][r].getText().equals(currentPlayer) && board[1][r].getText().equals(currentPlayer) && board[2][r].getText().equals(currentPlayer));
	}
	
	private boolean checkCol(int c) {
		return(board[c][0].getText().equals(currentPlayer) && board[c][1].getText().equals(currentPlayer) && board[c][2].getText().equals(currentPlayer));
	}
}
