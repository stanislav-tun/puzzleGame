package view.gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;

import observer.IObserver;
import observer.ISubject;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class Gui extends JFrame implements IObserver {
	private JTextField textField;
	private JTextPane console;
	private GroupLayout groupLayout;
	private String array, ij;

	public Gui() throws HeadlessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Tahoma", Font.BOLD, 16));
		console.setForeground(UIManager.getColor("Button.light"));
		console.setBackground(Color.DARK_GRAY);
		console.setFocusable(false);
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setForeground(UIManager.getColor("Button.background"));
		textField.setBackground(Color.DARK_GRAY);
		textField.setEditable(false);
		textField.setFocusable(false);
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(console, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
				.addComponent(textField, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(console, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		// TODO Auto-generated constructor stub
		init();
	}

	public void init() {
		setSize(310, 310);
		setResizable(false);
		
		setVisible(true);
	}

	public Gui(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public Gui(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public Gui(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int board[][]) {
		System.out.println("Updated");
		array = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] < 10) {
					array+="   "+board[i][j];
				} else {
					array+=" "+board[i][j];
				}
			}
			array+="\n";
		}
		console.setText(array);
	}

	@Override
	public void update(int ci, int cj, int board[][]) {
		// TODO Auto-generated method stub
		array = "";
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] < 10) {
					array+="   "+board[i][j];
				} else {
					array+=" "+board[i][j];
				}
			}
			array+="\n";
		}
		array += "ci = "+ci+" cj = "+cj+" current elem = "+board[ci][cj];
		console.setText(array);
		
	}
}
