import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuView {
    private JFrame f;

    /**
     * A men? megjelen?t?s??rt felel?s oszt?ly konstruktora
     * @param f_ az a JFrame, amiben megjelenik a men?
     */
    public MenuView(JFrame f_) {
        this.f = f_;
    }

    /**
     * A men? elemeinek megjelen?t?s??rt felel?s met?dus
     */
    public void Display() {
        JLayeredPane LPane = new JLayeredPane();
        f.getContentPane().add(LPane);

        JLabel background = new JLabel();
        try {
			background.setIcon(new ImageIcon(ImageIO.read(new File("resources/menuBackground.jpg")).getScaledInstance(Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT, Image.SCALE_DEFAULT)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        background.setBounds(0, 0, Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT);


        JButton startButton = new JButton();
        startButton.setBounds((Vars.WINDOW_WIDTH - Vars.BUTTON_WIDTH) / 2, 2*(Vars.WINDOW_HEIGHT - 2 * Vars.BUTTON_HEIGHT) / 5, Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT);
        try {
            Image image = ImageIO.read(new File("resources/buttons/startButton.png")).getScaledInstance(Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT, Image.SCALE_DEFAULT);
            startButton.setIcon(new ImageIcon(image));
            startButton.setBorderPainted(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startButton.addActionListener(e -> startButtonPressed());

        JButton exitButton = new JButton();
        exitButton.setBounds((Vars.WINDOW_WIDTH - Vars.BUTTON_WIDTH) / 2, 4*(Vars.WINDOW_HEIGHT - 2 * Vars.BUTTON_HEIGHT)  / 5, Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT);
        try {
            Image image = ImageIO.read(new File("resources/buttons/exitButton.png")).getScaledInstance(Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT, Image.SCALE_DEFAULT);
            exitButton.setIcon(new ImageIcon(image));
            exitButton.setBorderPainted(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        exitButton.addActionListener(e -> System.exit(0));

        LPane.add(background, Integer.valueOf(1));
        LPane.add(startButton, Integer.valueOf(2));
        LPane.add(exitButton, Integer.valueOf(2));

        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setSize(Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    /**
     * A start gomb lenyom?sakor t?rt?n? funkci? megval?s?t?s?ra h?vatott f?ggv?ny
     */
    private void startButtonPressed() {
        JFrame inp = new JFrame();
        inp.setName("J?t?kosok sz?ma");

        inp.pack();
        String input = JOptionPane.showInputDialog(inp,"Adja meg a j?t?kosok sz?m?t:");
        if(input != null && input.length() > 0){
            try{
            Vars.NUM_OF_PLAYERS = Integer.parseInt(input);
            if(Vars.NUM_OF_PLAYERS != 0){
                System.out.println("A JATEKOSOK SZAMA: " + Vars.NUM_OF_PLAYERS);
                GameView.setSpaceGameView(new SpaceGameView(f));
            }else{
                System.out.println("NEM MEGFELELO SZAM!");
            }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("NEM MEGFELELO SZAM!");
            }
        }
    }
}
