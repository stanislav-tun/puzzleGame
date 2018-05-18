package observer;

public interface IObserver {
	//will update the data in gui
	public void update();
	public void update(int board[][]);
	public void update(int i, int j, int board[][]);
}
