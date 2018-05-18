package model.logic;

import view.gui.Gui;

public class Main {
	private view.gui.Gui gui;
	private Board board;
	public Main() {
		gui = new Gui();
		board = new Board(4);
		gui.addKeyListener(board);
		board.registerObserver(gui);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
