package model.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import observer.IObserver;
import observer.ISubject;

public class Board implements KeyListener, ISubject {
	private int board[][], ci, cj, size, zero[];
	private ArrayList<IObserver> observers;

	public Board(int size) {
		this.size = size;
		board = new int[size][size];
		// this array was added for save zero coordinates if he was changed. Writing
		// execute
		// for each movement
		zero = new int[2];
		zero[0] = size - 1;
		zero[1] = size - 1;
		observers = new ArrayList<IObserver>();
		fillBoard();
		// Doesn't work calling in constructor, why?!
		notifyObserver();
		//mix();

	}



	private void swap(int i, int j) {
		int elem = board[ci][cj];
		board[ci][cj] = board[i][j];
		board[i][j] = elem;
		zero[0] = ci;
		zero[1] = cj;
		
	}

	private void findZero() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(board[i][j] == 0) {
					zero[0] = i;
					zero[1] = j;
				}
			}
		}
	}
	
	private void mix() {
		int a = 0;
		int b = 0;
		for (int i = 0; i < 50; i++) {
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			a = (int) (Math.random()*size);
			b = (int) (Math.random()*size);
			swap(a, b);
			
		}
		findZero();
		//notifyObserver();
	}
	
	private void move() {
		int ei = ci, ej = cj;
		// check line
		// check horizontal line (I)
		if (ci == zero[0]) {
			// chek direction, if j of element < j of zero then move on left else on right
			if (cj < zero[1]) {
				while (zero[1] != ej) {
					//устанавливаем курсор на позицию первого от нуля элемента слева
					//и так далее пока ноль не встанет на правильную позицию
					cj = zero[1] - 1;
					swap(zero[0], zero[1]);
				}
			} else {
				while (zero[1] != ej) {
					cj = zero[1] + 1;
					swap(zero[0], zero[1]);
				}
			}

		}
		// check vertical line (J)
		if (cj == zero[1]) {
			// chek direction, if i of element < i of zero then move up else down
			if (ci < zero[0]) {
				// need to create movement (swap)
				while (zero[0] != ei) {
					ci = zero[0] - 1;
					swap(zero[0], zero[1]);
				}
			} else {
				while (zero[0] != ei) {
					ci = zero[0] + 1;
					swap(zero[0], zero[1]);
				}
			}
		}

	}

	// moves element when zero and element is neighbors
	private void moveElementNextZero() {

		// dosen't work when ci or cj = 0 or 3, why?!
		// because try catch don't let checking the null after exception

		/**
		 * Ранее проверки выполнялись напрямую в блоках if метода moveElement() и
		 * отслеживались все блоки if одним блоком try catch который не позволял двигать
		 * елементы при выпадении ошибки в качестве решения проверки выделились в
		 * отдельные методы и уже в них обрабатываются исключения что позволило сделать
		 * перемещение возможным в любой ситуации, но данный подход не идеален потому
		 * требуется оптимизация из за слишком большого кол-ва методов проверки
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

	// check next element is 0
	private boolean checkUp() {
		try {
			if (board[ci - 1][cj] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	private boolean checkDown() {
		try {
			if (board[ci + 1][cj] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	private boolean checkLeft() {
		try {
			if (board[ci][cj - 1] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
	}

	private boolean checkRight() {
		try {
			if (board[ci][cj + 1] == 0) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return false;
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

	private void moveLeft() {
		if (cj <= 0) {
			cj = 0;
		} else {
			cj--;
		}

	}

	private void moveRight() {
		if (cj >= size - 1) {
			cj = size - 1;
		} else {
			cj++;
		}
	}

	private void moveDown() {
		if (ci >= size - 1) {
			ci = size - 1;
		} else {
			ci++;
		}
	}

	private void moveUp() {
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
			// moveElementNextZero();
			move();

			break;
		case KeyEvent.VK_M:
			mix();

			break;
		case KeyEvent.VK_S:
			fillBoard();

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
