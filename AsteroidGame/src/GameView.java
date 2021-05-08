import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameView {
	private boolean menuViewShow;
	private static SpaceGameView spaceGameView;
	public static JFrame f;
	private static MenuView menuView;
	public static void main(String[] args) {
		f = new JFrame("Asteroid Game");
		menuView = new MenuView(f);
		Display();
	}
	public static void Display() {
		menuView.Display();
	}
	
	public MenuView getMenuView() {
		return menuView;
	}
	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}
	public boolean isMenuViewShow() {
		return menuViewShow;
	}
	public void setMenuViewShow(boolean menuViewShow) {
		this.menuViewShow = menuViewShow;
	}
}
