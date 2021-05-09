import javax.swing.JFrame;

public class GameView {
    private static SpaceGameView spaceGameView;
    public static JFrame f;
    private static MenuView menuView;

    /**
     * A játék belépési pontja
     * @param args
     */
    public static void main(String[] args) {
        f = new JFrame("Asteroid Game");
        menuView = new MenuView(f);
        Display();
    }

    /**
     * A menü megjelenítése
     */
    public static void Display() {
        menuView.Display();
    }

    /**
     * A GameView-hoz tartozó SpaceGameView beállítása.
     * @param s a paraméterként átadott SpaceGameView
     */
    public static void setSpaceGameView(SpaceGameView s) {
        spaceGameView = s;
    }
}
