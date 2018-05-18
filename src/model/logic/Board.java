package model.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import observer.IObserver;
import observer.ISubject;

public class Board implements KeyListener, ISubject {
	private int board[][], ci, cj, size, zero[];
	private ArrayList<IObserver> observers;

	public Board(int size) {
		this.size = size;
		board = new int[size][size];
		zero = new int[2];
		zero[0] = size - 1;
		zero[1] = size - 1;
		observers = new ArrayList<IObserver>();
		fillBoard();
		// Doesn't work calling in constructor, why?!
		notifyObserver();

	}

	public void move() {
		// zero[0] = i of zero and zero[1] = j of zero
		int a = ci;
		int b = cj;
		if (a == zero[0]) {
			if (b < zero[1]) {
				int c = zero[1];
				while(cj != zero[1]) {
					c--;
					swap(a, b+c, zero[0], zero[1]);
				}
				
			}
		}
	}

	// moves element when zero and element is neighbors
	private void moveElementNextZero() {

		// dosen't work when ci or cj = 0 or 3, why?!
		// because try catch don't let checking the null after exception

		/**
		 * –анее проверки выполн€лись напр€мую в блоках if метода moveElement() и
		 * отслеживались все блоки if одним блоком try catch который не позвол€л двигать
		 * елементы при выпадении ошибки в качестве решени€ проверки выделились в
		 * отдельные методы и уже в них обрабатываютс€ исключени€ что позволило сделать
		 * перемещение возможным в любой ситуации, но данный подход не идеален потому
		 * требуетс€ оптимизаци€ из за слишком большого кол-ва методов проверки
		 */
		if (checkUp()) {
			swap(ci - 1, cj);

		}
		if (checkDown()) {
			swap(ci + 1, cj);

		}
		if (checkLeft()) {
			swap(ci, cj - 1);

		}
		if (checkRight()) {
			swap(ci, cj + 1);

		}
	}

	public boolean checkUp() {
		try {
			if (board[ci - 1][cj] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	public boolean checkDown() {
		try {
			if (board[ci + 1][cj] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	public boolean checkLeft() {
		try {
			if (board[ci][cj - 1] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	public boolean checkRight() {
		try {
			if (board[ci][cj + 1] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	private void swap(int i, int j) {
		int elem = board[ci][cj];
		board[ci][cj] = 0;
		board[i][j] = elem;
		zero[0] = ci;
		zero[1] = cj;
	}

	private void swap(int ci, int cj, int zi, int zj) {
		int elem = board[ci][cj];
		board[ci][cj] = 0;
		board[zi][zj] = elem;
		zero[0] = this.ci;
		zero[1] = this.cj;
	}

	// fill board as default
	/*
	 * valeriia shcherbina begimai dzhumabekova anna kwon aiperi subanova boss asel
	 * omoeva hr altynai malgaraeva
	 */
	private void fillBoard() {
		int elem = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				elem++;
				board[i][j] = elem;
			}
		}
		// set last element to 0
		board[size - 1][size - 1] = 0;
	}

	public void moveLeft() {
		if (cj <= 0) {
			cj = 0;
		} else {
			cj--;
		}

	}

	public void moveRight() {
		if (cj >= size - 1) {
			cj = size - 1;
		} else {
			cj++;
		}
	}

	public void moveDown() {
		if (ci >= size - 1) {
			ci = size - 1;
		} else {
			ci++;
		}
	}

	public void moveUp() {
		if (ci <= 0) {
			ci = 0;
		} else {
			ci--;
		}
	}

	@Override
	public void registerObserver(IObserver o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(IObserver o) {
		observers.remove(o);

	}

	@Override
	public void notifyObserver() {
		// System.out.println("Notifyed");
		for (IObserver o : observers) {
			o.update(ci, cj, board);

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_UP:
			moveUp();

			break;
		case KeyEvent.VK_DOWN:
			moveDown();

			break;
		case KeyEvent.VK_LEFT:
			moveLeft();

			break;
		case KeyEvent.VK_RIGHT:
			moveRight();

			break;
		case KeyEvent.VK_SPACE:
			moveElementNextZero();
			//move();
			break;
		default: // notifyObserver();
			break;
		}
		notifyObserver();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
