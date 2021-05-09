import javax.swing.JFrame;

public class GameView {
    private static SpaceGameView spaceGameView;
    public static JFrame f;
    private static MenuView menuView;

    /**
     * A j�t�k bel�p�si pontja
     * @param args
     */
    public static void main(String[] args) {
        f = new JFrame("Asteroid Game");
        menuView = new MenuView(f);
        Display();
    }

    /**
     * A men� megjelen�t�se
     */
    public static void Display() {
        menuView.Display();
    }

    /**
     * A GameView-hoz tartoz� SpaceGameView be�ll�t�sa.
     * @param s a param�terk�nt �tadott SpaceGameView
     */
    public static void setSpaceGameView(SpaceGameView s) {
        spaceGameView = s;
    }
}
